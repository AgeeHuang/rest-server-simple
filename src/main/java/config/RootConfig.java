package config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import webLoction.ServerLocationBo;
import webLoction.ServerLocationBoImpl;

@Configuration
@MapperScan({"repository.UserRepository"})
public class RootConfig {
  private static final Logger logger = LoggerFactory.getLogger(RootConfig.class);

    @Bean
    public ServerLocationBo serverLocationBo() {
      return new ServerLocationBoImpl();
    
  }
}
