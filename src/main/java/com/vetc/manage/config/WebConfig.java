package com.vetc.manage.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

  @Autowired
  private Environment env;

  @Override
  public void configurePathMatch(PathMatchConfigurer configurer) {
    AntPathMatcher matcher = new AntPathMatcher();
    matcher.setCaseSensitive(false);
    configurer.setPathMatcher(matcher);
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    String localStaticFile = "file:" + env.getProperty("server.image.dir.host");
    registry.addResourceHandler("/media/**").addResourceLocations(localStaticFile);
  }

}
