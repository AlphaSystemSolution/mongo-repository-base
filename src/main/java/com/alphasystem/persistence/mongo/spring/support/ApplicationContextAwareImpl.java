/**
 * 
 */
package com.alphasystem.persistence.mongo.spring.support;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import static com.alphasystem.persistence.mongo.spring.support.ApplicationContextProvider.getInstance;

/**
 * @author sali
 * 
 */
//@Component
public class ApplicationContextAwareImpl implements ApplicationContextAware {

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		getInstance().setApplicationContext(applicationContext);
	}

}
