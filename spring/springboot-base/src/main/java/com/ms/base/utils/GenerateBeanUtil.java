package com.ms.base.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class GenerateBeanUtil implements ApplicationContextAware {
	private static ApplicationContext appCtx;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		appCtx = applicationContext;
	}
	public static ApplicationContext getApplicationContext() {
        return appCtx;
    }
	public static Object getBean(String beanName) {
        return appCtx.getBean(beanName);
    }
}