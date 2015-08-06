package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import webLoction.ServerLocation;
import webLoction.ServerLocationBo;

@Controller
@RequestMapping(value = "/simple")
public class simpleController {
  
  @Autowired
  ServerLocationBo serverLocationBo;
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ModelAndView getPage(){
    ModelAndView model = new ModelAndView("main");
    return model;
  }
  
  @RequestMapping(value = "/getLocationByIpAddress", method = RequestMethod.GET)
  @ResponseBody
  public String getDomain(@RequestParam String ipAddress){
    ObjectMapper mapper = new ObjectMapper();
    System.out.print(ipAddress);
    ServerLocation location = serverLocationBo.getLocation(ipAddress);
    String result = "";
    try{
      result = mapper.writeValueAsString(location);
    }catch(Exception e){
      e.printStackTrace();
    }
    return result;
  }
  
  @RequestMapping(value="/login" ,method = RequestMethod.GET)
  public String readyLogin(){
    return "login";
  }
  
  @RequestMapping(value="/getIpAddress" ,method = RequestMethod.POST)
  @ResponseBody
  public String getIpAdress(HttpServletRequest request){
    String sIPAddress = request.getRemoteAddr();
    System.out.println("IP:"+sIPAddress);
    return sIPAddress;
  }
  
  @RequestMapping(value="/upLoadImg")
  public void fileUpload(HttpServletRequest request, HttpServletResponse response) throws IOException{
   MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
   MultipartFile imgFile1 = multipartRequest.getFile("fileToUpload");
   response.setContentType("text/html; charset=UTF-8");
   String ogFilename = imgFile1.getOriginalFilename();
   String newFilename = System.currentTimeMillis() + "";;
   PrintWriter out = response.getWriter();
   if(!(ogFilename == null || "".equals(ogFilename))){
     String uploadfile = request.getServletContext().getRealPath("/upload"); //上傳路徑
         try{
               FileUtils.copyInputStreamToFile(imgFile1.getInputStream(), new File(uploadfile, newFilename)); 
            }catch(IOException e){
                out.print("ERROR");
                out.flush();
         }
   }
   out.println(request.getContextPath() + "/upload/" + newFilename);  
   out.flush();
   System.out.println("上傳檔案類型：" + imgFile1.getContentType() + " 上傳檔案長度：" + imgFile1.getSize() + " 上傳文件：" + imgFile1.getName());
  }
}
 