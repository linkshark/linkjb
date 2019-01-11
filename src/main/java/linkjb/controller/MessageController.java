package linkjb.controller;

import linkjb.base.BaseResult;
import linkjb.pojo.Message;
import linkjb.pojo.User;

import linkjb.service.serviceInterface.MessageService;

import linkjb.utils.ConstantSrting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping(value = "/leave", produces="application/json;charset=UTF-8")
public class MessageController {
    @Autowired
    MessageService messageService;


    @RequestMapping(value = "/message",method= RequestMethod.POST)
    public BaseResult<Message> insertrMessage(HttpServletRequest request, Integer ownerId, Integer passerId, String message){
        System.out.println(ownerId+"----"+passerId+"-----"+message);
        BaseResult<Message> re = new BaseResult<>();
                try{
                    Message message1 = new Message();
                    message1.setOwnerId(ownerId);
                    message1.setPasserId(passerId);
                    message1.setMessage(message);
                    message1.setCreateTime(new Date());
                    messageService.save(message1);
                    re.setEntity(message1);
                    re.setMessage("留言成功");
                    re.setStatus(ConstantSrting.STATUS_SUCCESS);
                    return re;
                }catch (Exception e){
                    re.setMessage("留言失败");
                    re.setStatus(ConstantSrting.STATUS_FAIL);
                    e.printStackTrace();
                    return re;
                }
    }

    @RequestMapping(value = "/findmessage",method= RequestMethod.POST)
    public BaseResult<List<Map<String,Object>>> findmessage(HttpServletRequest request, Integer ownerId,Integer flag){
        //如果flag=1 查询所有当前用户留言的Message ,如果FLAG=2,查询当前留言当前用户的MESSAGE
        BaseResult<List<Map<String,Object>>> re = new BaseResult<>();
        try{

            List<Map<String,Object>> msg = messageService.findmessageByFlag(ownerId,flag);
            re.setStatus(ConstantSrting.STATUS_SUCCESS);
            re.setMessage("查询成功");
            re.setEntity(msg);
            //System.out.println(msg);
            System.out.println(msg);
            return re;
        }catch (Exception e){
            e.printStackTrace();
            re.setMessage("查询失败");
            re.setStatus(ConstantSrting.STATUS_FAIL);
            return re;
        }


    }









}
