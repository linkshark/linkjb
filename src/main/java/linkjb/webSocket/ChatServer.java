package linkjb.webSocket;

import com.alibaba.fastjson.JSON;
import linkjb.dao.TMgtUserDAO;
import linkjb.pojo.User;
import linkjb.service.lUserService;
import linkjb.service.serviceInterface.UserService;
import linkjb.utils.CommonDate;
import linkjb.utils.StringUtil;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpSession;

//* @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
//        * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
//         */
@ServerEndpoint(value="/chatServer/{userid}", configurator =HttpSessionConfigurator.class )
@Component
public class ChatServer {
    @Resource
    private TMgtUserDAO dao;


    private static Logger logger = Logger.getLogger(ChatServer.class);

    //当前在线人数
    private static int onlineCount = 0; // 记录在线人数
    //用来存放每个客户端对应的WebSocketTest对象，适用于同时与多个客户端通信
    //public static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<WebSocket>();
    //存放每个客户端对应的Websocket对象,适用于服务器向多个用户通讯,key可以作为用户表示
    //private static ConcurrentHashMap<Integer,WebSocket> websocketMap = new ConcurrentHashMap<>();
    private static Map<Integer, OnlineUser> onlineUserMap = new ConcurrentHashMap<Integer, OnlineUser>(); // 在线用户
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    //private Session currentSession;

    /**
     * 连接建立调用的方法
     * @param session 可选参数,session为与某个客户端的连接对话,
     *                需要通过它来给客户端发送数据
     *
     */
    @OnOpen
    public void onOpen( @PathParam("userId") String userId,Session session,
                       EndpointConfig config
                       ){
        logger.info("[ChatServer]connection : userid = " + userId + " , sessionId = " + session.getId());
        // 增加用户数量
       // System.out.println(userId);
        userId = session.getPathParameters().get("userid");
        addOnlineCount();
        // 获取当前用户的session
        //HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        //User user  = (User) httpSession.getAttribute("user"); // 获得当前用户信息
        // 将当前用户存到在线用户列表中
        TMgtUserDAO service = (TMgtUserDAO) ContextLoader.getCurrentWebApplicationContext().getBean("TMgtUserDAO");
        User user = null;
        try {
            user = service.getUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        OnlineUser onlineUser = new OnlineUser(userId,user.getName(),session);
        onlineUserMap.put(user.getId(),onlineUser);

        // 通知所有在线用户，当前用户上线
        String content = "[" + CommonDate.getTime24() + " : " + user.getName() + "登陆本站，当前在线人数为 " + getOnlineCount() + "位" + "]";
        JSONObject msg = new JSONObject();
        msg.put("content",content);
        String messgae = Message.getMessage(msg.toString(),Message.NOTICE,onlineUserMap.values());
        Message.broadcast(messgae,onlineUserMap.values());

    }

    /**
     * 连接关闭方法
     */
    @OnClose
    public void onClose(@PathParam("userid") String userid,Session session,CloseReason closeReason){

        logger.info("[ChatServer] close : userid = " + userid + " , sessionId = " + session.getId() +
                " , closeCode = " + closeReason.getCloseCode().getCode() + " , closeReason = " +closeReason.getReasonPhrase());
        System.out.println(onlineUserMap);
        // 减少当前用户
        subOnlienCount();
        userid = session.getPathParameters().get("userid");
        // 移除的用户信息
        System.out.println(userid);
       OnlineUser removeUser = onlineUserMap.get(Integer.parseInt(userid));
       System.out.println(removeUser);
        onlineUserMap.remove(Integer.parseInt(userid));
        // 通知所有在线用户，当前用户下线
       String content = "["+ CommonDate.getTime24() + " : " + removeUser.getNickname() + " 离开聊天室，当前在线人数为 " + getOnlineCount() + "位" + "]";
       JSONObject msg = new JSONObject();
        msg.put("content",content);
       if(onlineUserMap.size() > 0){
           String message = Message.getMessage(msg.toString(), Message.NOTICE, onlineUserMap.values());
           Message.broadcast(message,onlineUserMap.values());
       }else{
           logger.info("content : ["+ CommonDate.getTime24() + " : " + removeUser.getNickname() + " 离开聊天室，当前在线人数为 " + getOnlineCount() + "位" + "]");
       }


    }
    /**
     * 接收客户端的message，判断是否有接收人而选择进行广播还是指定发送
     * @param data 客户端发来的消息
     */
    @OnMessage
    @SuppressWarnings("unchecked")
    public void onMessage(@PathParam("userid") String userid,String data){
        logger.info("[ChatServer] onMessage : userid = " + userid + " , data = " + data);
        JSONObject messageJson = JSONObject.fromObject(data);
        JSONObject message = messageJson.optJSONObject("message");
        String to = message.optString("to");
        String from = message.optString("from");

        // 将用户id转换为名称
        to = this.userIdCastNickName(to);

        OnlineUser fromUser = onlineUserMap.get(Integer.parseInt(from));
        String sendMessage = Message.getContent(fromUser,to,message.optString("content"),message.optString("time"));
        String returnData = Message.getMessage(sendMessage, messageJson.optString("type"),null);


        if(to == null || to.equals("")){ // 进行广播
            Message.broadcast(returnData.toString(),onlineUserMap.values());
        }else{
            Message.singleSend(returnData.toString(), onlineUserMap.get(Integer.parseInt(from)));   // 发送给自己
            String[] useridList = message.optString("to").split(",");
            for(String id : useridList){
                if(!id.equals(from)){
                    Message.singleSend(returnData.toString(), onlineUserMap.get(Integer.parseInt(id))); // 分别发送给指定的用户
                }
            }
        }

    }

    /**
     * 发生错误
     * @param throwable
     */
    @OnError
    public void onError(@PathParam("userid") String userid,Session session,Throwable throwable){
        logger.info("[ChatServer] close : userid = " + userid + " , sessionId = " + session.getId() +" , throwable = " + throwable.getMessage() );
    }
    public static int getOnlineCount() {
        return onlineCount;
    }

    public synchronized void addOnlineCount(){
        onlineCount++;
    }

    public synchronized void subOnlienCount(){
        onlineCount--;
    }
    /**
     * 将用户id转换为名称
     * @param userIds
     * @return
     */
    private String userIdCastNickName(String userIds){
        String niceNames = "";
        if(userIds != null && !userIds.equals("")){
            String[] useridList = userIds.split(",");
            String toName = "";
            for (String id : useridList){
                toName = toName + onlineUserMap.get(Integer.parseInt(id)).getNickname() + ",";
            }
            niceNames = toName.substring(0,toName.length() - 1);
        }
        return niceNames;
    }
}
