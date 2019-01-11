package linkjb.controller;


import linkjb.base.BaseResult;
import linkjb.pojo.User;
import linkjb.service.serviceInterface.RegistService;
import linkjb.utils.ConstantSrting;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.UUID;

@Controller
@ResponseBody
@RequestMapping(value = "/regist", produces="application/json;charset=UTF-8")
public class RegistController{
    @Autowired
    RegistService registservice;

    @RequestMapping(value = "/regist",method= RequestMethod.POST)
    public  BaseResult<User> Regist(HttpServletRequest request,  User user){
        //@RequestParam("file") MultipartFile file,
        BaseResult<User> re =new BaseResult<>();
         try{
             user.setCreateTime(new Date());
             user.setModifyTime(new Date());
             registservice.Regist(user);
             re.setStatus(ConstantSrting.STATUS_SUCCESS);
             re.setMessage("注册成功");
             return  re;

         }catch (Exception e){
             re.setStatus(ConstantSrting.STATUS_FAIL);
             re.setMessage("注册失败");
             System.out.println(re);
             e.printStackTrace();
             return re;
         }
    }

    @RequestMapping(value = "/checkName")
    public BaseResult checkName(String name){
        //System.out.println(name);
        BaseResult re =new BaseResult();
        try {
            int a = registservice.checkName(name);
            if(a > 0){
                re.setStatus(ConstantSrting.STATUS_FAIL);
                re.setMessage("已存在相同用户名");
                System.out.println(re);
                return  re;
            }else{
                re.setStatus(ConstantSrting.STATUS_SUCCESS);
                re.setMessage("用户名可以使用");
                System.out.println(re);
                return  re;
            }
        }catch (Exception e){
            re.setStatus(ConstantSrting.STATUS_other);
            e.printStackTrace();
            re.setMessage(e.getMessage());
            return  re;
        }


    }

    @RequestMapping(value = "/checkEmail")
    public BaseResult checkEmail(String email){
        //System.out.println(email);
        BaseResult re =new BaseResult();
        try {
            int a = registservice.checkEmail(email);
            if(a > 0){
                re.setStatus(ConstantSrting.STATUS_FAIL);
                re.setMessage("已存在相同邮箱");
                //System.out.println(re);
                return  re;
            }else{
                re.setStatus(ConstantSrting.STATUS_SUCCESS);
                re.setMessage("邮箱可以使用");
                //System.out.println(re);
                return  re;
            }
        }catch (Exception e){
            re.setStatus(ConstantSrting.STATUS_other);
            e.printStackTrace();
            re.setMessage(e.getMessage());
            return  re;
        }


    }

    @RequestMapping(value = "/checkMobile")
    public BaseResult checkMobile(String mobile){
        //System.out.println(email);
        BaseResult re =new BaseResult();
        try {
            int a = registservice.checkMobile(mobile);
            if(a > 0){
                re.setStatus(ConstantSrting.STATUS_FAIL);
                re.setMessage("已存在相同电话号码");
                //System.out.println(re);
                return  re;
            }else{
                re.setStatus(ConstantSrting.STATUS_SUCCESS);
                re.setMessage("电话号码可以使用");
                //System.out.println(re);
                return  re;
            }
        }catch (Exception e){
            re.setStatus(ConstantSrting.STATUS_other);
            e.printStackTrace();
            re.setMessage(e.getMessage());
            return  re;
        }


    }

}
