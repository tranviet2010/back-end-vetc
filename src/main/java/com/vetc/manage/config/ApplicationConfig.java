package com.vetc.manage.config;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties()
@Getter
@Setter
public class ApplicationConfig {

  private Settings settings;
  private ServerConfig server;
  private boolean allowModifySettings;
  private HftQueries hftQueries;
  private Constants constants;
  private SwaggerConfig swagger;
  private Datetimes datetimes;

  @Getter
  @Setter
  public static class ServerConfig {

    private String port;
    private Endpoints endpoints;
    private HealthcheckConfig healthcheck;
  }

  @Getter
  @Setter
  public static class Datetimes {

    private String dateFormatPattern;
    private String datetimeFormatPattern;
  }

  @Getter
  @Setter
  public static class Endpoints {

    private String bps;
    private String host;
    private String fo;
    private String portfolio;
  }

  @Getter
  @Setter
  public static class Settings {

    private boolean checkToken;
    private boolean checkIdempotencyKey;
    private String disableDivideBigVolumnOrder;
    private String checkSessionPlaceOrder;
    private String checkOrderType;
    private String preProcessRequire;
    private String processSendToFO;
    private String preAPIcheckRequire;
    private boolean callBank2Hold;
    private String BDSServiceBranchID;
    private String BDSServiceTellerID;
    private List<String> ignoredHeaders = new ArrayList<>();
  }

  @Getter
  @Setter
  public static class HftQueries {

    private String hftCache;
    private String hftUserUseFoCache;
    private String hftMode;
    private String defError;
    private String getMaxOrderQuantity;
  }

  @Getter
  @Setter
  public static class Constants {

    private String custodyId;
    private String custId;
    private String accountNo;
  }

  @Getter
  @Setter
  public static class SwaggerConfig {

    private String basePath;
    private String host;
  }

  @Getter
  @Setter
  public static class HealthcheckConfig {

    private String uri;
  }
}






