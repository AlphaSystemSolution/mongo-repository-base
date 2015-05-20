/**
 * 
 */
package com.alphasystem.persistence.mongo.spring.support;

import org.springframework.context.ApplicationContext;

/**
 * @author sali
 * 
 */
public class ApplicationContextProvider {

	private static ApplicationContextProvider instance;

	public synchronized static ApplicationContextProvider getInstance() {
		if (instance == null) {
			instance = new ApplicationContextProvider();
		}
		return instance;
	}

	private ApplicationContext applicationContext;

	/**
	 * Do not let anyone instantiate this class
	 */
	private ApplicationContextProvider() {
	}

	public <T> T getBean(Class<T> type) {
		return applicationContext.getBean(type);
	}

	public Object getBean(String name) {
		return applicationContext.getBean(name);
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

}
