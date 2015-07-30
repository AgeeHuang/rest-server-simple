package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/simple")
public class simpleController {
  
  private static final Logger logger = LoggerFactory.getLogger(simpleController.class);
  @RequestMapping(value = "/main", method = RequestMethod.GET)
  public String hello(){
    logger.error("get request");
    return "main";
  }
}
 