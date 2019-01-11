package linkjb.controller;

import linkjb.base.BaseResult;
import linkjb.utils.ConstantSrting;
import org.apache.commons.io.FileUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Controller
@RequestMapping(value = "/upload", produces="application/json;charset=UTF-8")
@ResponseBody
public class UploadController {

    @RequestMapping(value = "/imageupload")
    public BaseResult<String> UploadImage (HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        //System.out.println(file);
        BaseResult<String> re =new BaseResult<>();
        //获取项目根路径
        String relativelyPath=System.getProperty("user.dir")+File.separator+"src"+File.separator+"main"+File.separator+"webapp";
        //获取项目存放路径
        String staticPath = File.separator+"static"+File.separator+"images"+File.separator+"user"+File.separator;
        String path  = relativelyPath + staticPath;
        if(!file.isEmpty()){
                String fileRealName = file.getOriginalFilename(); //获得原始文件名;
                int pointIndex = fileRealName.indexOf(".");  //点号的位置
                String fileSuffix = fileRealName.substring(pointIndex);  //截取文件后缀
//              UUID FileId =UUID.randomUUID(); //生成文件的前缀包含连字符
                String pic_time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                Random random = new Random();
                //String savedFileName = pic_time.concat(fileSuffix)+random.nextInt(10000);
                String savedFileName = pic_time.concat(String.valueOf(random.nextInt(10000)))+fileSuffix;
                String savedDir = request.getSession().getServletContext().getRealPath("images"); //获取服务器指定文件存取路径
                //String savedDir = "http://www.linkjb.com/guoxiang/images/"; //获取服务器指定文件存取路径
                System.out.println(savedDir);
                File savedFile = new File(savedDir,savedFileName );
                boolean isCreateSuccess = savedFile.createNewFile();
                if(isCreateSuccess){
                    file.transferTo(savedFile);  //转存文件
                    re.setMessage("文件存储成功");
                    re.setStatus(ConstantSrting.STATUS_SUCCESS);
                    re.setEntity(savedFileName);
                    return re;
                }else{
                    re.setStatus(ConstantSrting.STATUS_FAIL);
                    re.setMessage("图片存储失败");
                    return re;
                }
            }else{
                re.setStatus(ConstantSrting.STATUS_FAIL);
                re.setMessage("文件不能为空");
                return  re;
            }
//        try{
//            if(!file.isEmpty()){
//                File saveFile = new File(path);
//                if(saveFile.exists()){
//                    saveFile.mkdirs();
//                }
//                //获取文件名称
//                String fileName = file.getOriginalFilename();
//                //获取文件后缀
//                String suffix = fileName.split("\\.")[1];
//                String pic_time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//                for (int i = 0; i <4; i++) {
//                    Random random = new Random();
//                    int nextInt = random.nextInt(10);
//                    pic_time += nextInt;
//                }
//                FileUtils.copyInputStreamToFile(file.getInputStream(),new File(path + pic_time +"."+suffix));
//                re.setStatus(ConstantSrting.STATUS_SUCCESS);
//                re.setEntity(staticPath + pic_time +"."+suffix);
//                return re;
//            }else{
//                re.setStatus(ConstantSrting.STATUS_FAIL);
//                re.setMessage("上传为空");
//                new Exception();
//                return re;
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//            re.setStatus(ConstantSrting.STATUS_FAIL);
//            re.setMessage(e.getMessage());
//        }



    }


}
