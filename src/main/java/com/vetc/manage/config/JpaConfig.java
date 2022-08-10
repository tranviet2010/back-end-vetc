package com.vetc.manage.config;

import com.vetc.manage.jpa.GenericRepositoryImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @Author HaNQ
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.vetc.manage.repository", repositoryBaseClass = GenericRepositoryImpl.class)
@EntityScan("com.vetc.manage.entity")
public class JpaConfig {

}
