package com.czxx.campusmanagement.util;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

public class PhotoUtil {
	/**
    *
    * 功能描述   保存图片
    *
    * @param filedata
    *           文件数据
    *　　返回图片位置
    */
   public static String saveFile(MultipartFile filedata) {
       // TODO Auto-generated method stub
	   
	   WebApplicationContext webApplicationContext = ContextLoader
               .getCurrentWebApplicationContext();
       ServletContext servletContext = webApplicationContext
               .getServletContext();
       // 得到文件绝对路径
       String realPath = servletContext.getRealPath("");
       System.out.println("realPath:"+realPath);
       
       String pathval = realPath + "WEB-INF/";//request.getSession().getServletContext().getRealPath("/")+"WEB-INF/";
       // 根据配置文件获取服务器图片存放路径
       String newFileName = String.valueOf( System.currentTimeMillis());
       String saveFilePath = "static/fileUpload/headImages/";
       /* 构建文件目录 */
       File fileDir = new File(pathval + saveFilePath);
       System.out.println(fileDir);
       if (!fileDir.exists()) {
           fileDir.mkdirs();
       }
       //上传的文件名
       String filename=filedata.getOriginalFilename();
       //文件的扩张名
       String extensionName = filename.substring(filename.lastIndexOf(".") + 1);
       try {
           String imgPath = saveFilePath + newFileName + "." +extensionName;
           //System.out.println(pathval + imgPath);打印图片位置
           FileOutputStream out = new FileOutputStream(pathval + imgPath);
           // 写入文件
           out.write(filedata.getBytes());
           out.flush();
           out.close();
           return "/" + saveFilePath + newFileName + "." +extensionName;
       } catch (Exception e) {
           e.printStackTrace();
       }
       return newFileName + "." +extensionName;
   }


   /**
    *
    * 功能描述：删除图片
    *
    * @param oldPic
    *
    */
   private void deleteFile(String oldPic) {
       // TODO Auto-generated method stub

       /* 构建文件目录 */
       File fileDir = new File(oldPic);
       if (fileDir.exists()) {
           //把修改之前的图片删除 以免太多没用的图片占据空间
           fileDir.delete();
       }

   }
}
