package com.vetc.manage.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class DomainRegistry implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public synchronized void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (DomainRegistry.applicationContext == null) {
            DomainRegistry.applicationContext = applicationContext;
        }
    }
}
