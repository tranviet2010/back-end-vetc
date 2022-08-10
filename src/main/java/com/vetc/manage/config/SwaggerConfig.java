/**
 *
 */
package com.vetc.manage.config;

import com.vetc.manage.enums.ApiGroup;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author HaNQ
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Autowired
  private ApplicationConfig applicationConfig;
  private String basePackage = "com.vetc.manage.controller";

  private ApiKey apiKey() {
    return new ApiKey("JWT", "x-api-key", "header");
  }

  private List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder().securityReferences(defaultAuth()).build();
  }

  @Bean
  public Docket allAPI() {
    return new Docket(DocumentationType.SWAGGER_2).apiInfo(getApiInfo())
        .securityContexts(Arrays.asList(securityContext())).securitySchemes(Arrays.asList(apiKey()))
        .host(applicationConfig.getSwagger().getHost())
        .pathProvider(new RelativePathProvider(null) {
          @Override
          public String getApplicationBasePath() {
            return applicationConfig.getSwagger().getBasePath();
          }
        }).select().apis(RequestHandlerSelectors.basePackage(basePackage))
        .paths(PathSelectors.any()).build();
  }

  @Bean
  public Docket sdkHealthCheck() {
    return new Docket(DocumentationType.SWAGGER_2).apiInfo(getApiInfo())
        .host(applicationConfig.getSwagger().getHost())
        .pathProvider(new RelativePathProvider(null) {
          @Override
          public String getApplicationBasePath() {
            return applicationConfig.getSwagger().getBasePath();
          }
        }).groupName(ApiGroup.NOTOKEN_RestAPI_SIT.getGroupName()).select()
        .apis(RequestHandlerSelectors.basePackage(basePackage))
        .paths(PathSelectors.ant(ApiGroup.NOTOKEN_RestAPI_SIT.getGroupPath())).build();
  }

  private ApiInfo getApiInfo() {
    return new ApiInfo("vetc services http://vetc.com.vn", "Rest api exposed", "1.0", "",
        new Contact("HaNQ", "http://vetc.com.vn", "HaNQ@vetc.com.vn"), "API license",
        "https://www.vetc.com.vn", Collections.emptyList());
  }
}
