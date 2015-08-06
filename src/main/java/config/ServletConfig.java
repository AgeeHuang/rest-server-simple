package config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc  
@ComponentScan(basePackages ="controller")
public class ServletConfig extends WebMvcConfigurerAdapter{
  @Bean
   public ViewResolver internalResourceViewResolver() {  
       InternalResourceViewResolver viewResolver = new InternalResourceViewResolver(); 
       viewResolver.setViewClass(JstlView.class);
       viewResolver.setPrefix("/WEB-INF/views/");  
       viewResolver.setSuffix(".jsp"); 
       return viewResolver;  
   }
  @Bean
   public MultipartResolver multipartResolver() throws IOException {
      CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
      multipartResolver.setMaxUploadSize(4 * 1024 * 1024);
      multipartResolver.setDefaultEncoding("utf-8");
      multipartResolver.setUploadTempDir(new FileSystemResource("/tmp/uploads"));
      return multipartResolver;
  }
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry){
    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
  }
  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
     configurer.enable();
  }
}
