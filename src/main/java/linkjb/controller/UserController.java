package linkjb.controller;

import linkjb.base.BaseResult;
import linkjb.pojo.TMgtUser;
import linkjb.pojo.User;
import linkjb.service.serviceInterface.RegistService;
import linkjb.service.serviceInterface.UserService;
import linkjb.utils.AddressUtils;
import linkjb.utils.ConstantSrting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping(value = "/login")
public class UserController {
    @Autowired
    RegistService registservice;

    @Autowired
    private UserService userService;
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    /**
     * 登录
     *
     * @param userName 用户名
     * @param pw       密码
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login")
    public  BaseResult<User> login(String userName, String pw, HttpServletRequest request, HttpServletResponse response) {
        BaseResult<User> br = new BaseResult<User>();
        if (userName == null || userName.length() == 0 || pw == null || pw.length() == 0) {
            logger.error("用户名和密码不能为空!");
            br.setStatus(ConstantSrting.STATUS_FAIL);
            br.setMessage("用户名和密码不能为空!!!");
            return br;
        }
        try {
            User user = userService.login(userName, pw);
            System.out.println(user);
            if (user == null) {
//                System.out.println(userName+"---"+pw);
//                   user = userService.loginByMobile(userName,pw);
//                   System.out.println("----"+user);
//                   if(user==null){
//                       br.setStatus(ConstantSrting.STATUS_FAIL);
//                       br.setMessage("用户名或密码不正确！");
//                       return br;
//                   }else{
                       br.setStatus(ConstantSrting.STATUS_FAIL);
                       br.setMessage("登陆失败！");
                       return br;
//                }

            }
            br.setEntity(user);
            br.setStatus(ConstantSrting.STATUS_SUCCESS);
        } catch (Exception e) {
            logger.error("登录时发生异常：" + e);
            e.printStackTrace();
            br.setStatus(ConstantSrting.STATUS_FAIL);
            br.setMessage("登录时发生异常!");
        }
        return br;
    }

    @ResponseBody
    @RequestMapping(value = "/update")
    public BaseResult<User> update(User user){
        BaseResult<User> re = new BaseResult<>();
        try {
            //int id = user.getId();
            user.setModifyTime(new Date());
            userService.updateUser(user);
            re.setMessage("更新成功");
            re.setStatus(ConstantSrting.STATUS_SUCCESS);
        }catch (Exception e ){
            re.setStatus(ConstantSrting.STATUS_FAIL);
            re.setMessage(e.getMessage());
        }
        return re;
    }



    //1查询所有数据
    //2更具ID查数据
    //3根据查找性别查数据

    //根据ID查数据
    @ResponseBody
    @RequestMapping(value = "/findUserById")
    public BaseResult<User> findUserById(Integer id){
        BaseResult<User> re = new BaseResult<>();
        try{
            User user = userService.findUserById(id);
            re.setMessage("查询成功");
            re.setStatus(ConstantSrting.STATUS_SUCCESS);
            re.setEntity(user);
            return re;
        }catch (Exception e){
            re.setMessage("查询失败");
            re.setStatus(ConstantSrting.STATUS_FAIL);
            return re;


        }
    };

    //根据查找性别查数据
    @ResponseBody
    @RequestMapping(value = "/findFlag")
    public BaseResult<User> findUserByFlag(String findFlag,String userId){
        BaseResult<User> re =new BaseResult<>();
        try {
            if(findFlag.equals("1")){
                findFlag = "3";
            }
            else if(findFlag.equals("2")){
                findFlag = "2";
            }
            else if(findFlag.equals("3")){
                findFlag = "1";
            }else if(findFlag.equals("4")){
                findFlag = "4";
            }
            List<User> list =userService.findUserByFlag(findFlag,userId);
            //System.out.println(list);
            re.setList(list);
            re.setMessage("查询所有数据成功");
            re.setStatus(ConstantSrting.STATUS_SUCCESS);
            return re;
        }catch (Exception e){
            e.printStackTrace();
            re.setMessage("查询所有数据失败");
            re.setStatus(ConstantSrting.STATUS_FAIL);
            return re;
        }
    };


    @ResponseBody
    @RequestMapping(value = "/findAllUser")
    public BaseResult<User> findAllUser(){
        BaseResult<User> re =new BaseResult<>();
        try {
            List<User> list =userService.findAllUser();
            re.setList(list);
            re.setMessage("查询所有数据成功");
            re.setStatus(ConstantSrting.STATUS_SUCCESS);
            return re;
        }catch (Exception e){
            re.setMessage("查询所有数据失败");
            re.setStatus(ConstantSrting.STATUS_FAIL);
            return re;
        }
    };

    @ResponseBody
    @RequestMapping(value = "/addHeart")
    public BaseResult<User> addHeart(Integer ownerId,Integer likeId){
        BaseResult<User> re =new BaseResult<>();
        try{
            User user = userService.findUserById(likeId);
            Map<String,Object> map = userService.findCountAndTimeById(ownerId,likeId);
            //System.out.println(map.get("modifyTime")); 2018-10-17 00:03:41.0
            Date date = (Date) map.get("modifyTime");
            Long count = (Long) map.get("count");
            if(date!=null&&count!=null&&!count.equals(0)){
                //通过Calendar实现时间相减
                Date newDate = new Date();
                double a = (double) (newDate.getTime()-date.getTime())/(1000*60);
                System.out.println(a);
                if(a<=60*24){
                    List<Integer> likeList = userService.findLikesByOwnerId(ownerId);
                    user.setLikeId(likeList);
                    re.setEntity(user);
                    re.setStatus(ConstantSrting.STATUS_FAIL);
                    int hour = (int)(((60*24)-a)/60);
                    if(hour==0){
                        re.setMessage("点赞失败,还需要"+(int)((60*24)-a)+"分钟才能再次点赞");
                    }else{
                        int min = (int)(((60*24)-a)-(60*hour));
                        re.setMessage("点赞失败,还需要 "+hour+"小时"+min+" 分钟才能再次点赞");
                    }

                    //System.out.println("点赞失败,还需要"+((60*24)-a)+"分钟才能再次点赞");
                    return re;
                }
                userService.addHeart(likeId);
                userService.updateLikeTime(ownerId,likeId,new Date());
                List<Integer> likeList = userService.findLikesByOwnerId(ownerId);
                user.setLikeId(likeList);
                re.setEntity(user);
                re.setStatus(ConstantSrting.STATUS_SUCCESS);
                re.setMessage("点赞成功");
                return re;

            }
            userService.addHeart(likeId);
            userService.insetIntoLikes(ownerId,likeId);
            List<Integer> likeList = userService.findLikesByOwnerId(ownerId);
            user.setLikeId(likeList);
            re.setEntity(user);
            re.setStatus(ConstantSrting.STATUS_SUCCESS);
            re.setMessage("点赞成功");
            return re;
        }catch (Exception e){
               re.setStatus(ConstantSrting.STATUS_FAIL);
               re.setMessage("点赞失败");
               e.printStackTrace();
               return re;
        }

    }

    @ResponseBody
    @RequestMapping(value = "/addBlack")
    public BaseResult addBlack(Integer userId,Integer reportId,String reportResult){
        BaseResult re = new BaseResult();
        try{


            //Integer a = userService.addBlack(userId,reportId,reportResult);
            re.setStatus(ConstantSrting.STATUS_SUCCESS);
            re.setMessage("添加黑名单成功");
            return re;
        }catch (Exception e){

        }
        return null;
    }


    /**
     * 通过IP地址查找所在地区符合要求的对象
     * @param IPAddress
     * @param findFlag
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getUserByIPAddress")
    public BaseResult<User> getUserByIPAddress(String IPAddress,String findFlag,String userId){
        BaseResult<User> re = new BaseResult();
        try{
            if(findFlag.equals("1")){
                findFlag = "3";
            }
            else if(findFlag.equals("2")){
                findFlag = "2";
            }
            else if(findFlag.equals("3")){
                findFlag = "1";
            }else if(findFlag.equals("4")){
                findFlag = "4";
            }
            //IPAddress = AddressUtils.getAddress(IPAddress);
            IPAddress = AddressUtils.getAddresses("ip="+IPAddress, "utf-8");
            List<User> returnUser = userService.findUserByFlagAndAddress(IPAddress,findFlag,userId);
            re.setList(returnUser);
            re.setStatus(ConstantSrting.STATUS_SUCCESS);
            re.setMessage("成功");
            return re;
        }catch (Exception e){
            re.setStatus(ConstantSrting.STATUS_FAIL);
            re.setMessage(e.getMessage());
            return re;
        }

    }









//    @RequestMapping(value = "/first", produces="application/json;charset=UTF-8")
    @CrossOrigin(origins = "*" ,maxAge = 3600)
    public String getName(User user){
        System.out.println(user);
        registservice.Regist(user);
        return null ;

    }
}
