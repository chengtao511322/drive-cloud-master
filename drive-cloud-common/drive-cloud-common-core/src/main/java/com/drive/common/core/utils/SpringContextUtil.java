package com.drive.common.core.utils;

import org.springframework.context.ApplicationContext;
/**
 * 上下文获取工具类
 * @author xiaoguo
 *
 */
public class SpringContextUtil {
	  private static ApplicationContext applicationContext;

	  public static void setApplicationContext(ApplicationContext context) {
	    applicationContext = context;
	  }

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static Object getBean(String beanId) {
	    return getApplicationContext().getBean(beanId);
	  }

	/**
	 * 通过class获取Bean.
	 *
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz) {
		return getApplicationContext().getBean(clazz);
	}

	/**
	 * 通过name,以及Clazz返回指定的Bean
	 *
	 * @param name
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> T getBean(String name, Class<T> clazz) {
		return getApplicationContext().getBean(name, clazz);
	}

}