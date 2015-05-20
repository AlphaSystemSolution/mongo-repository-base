/**
 * 
 */
package com.alphasystem.persistence.mongo.spring.support;

import static com.alphasystem.persistence.mongo.spring.support.ApplicationContextProvider.getInstance;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author sali
 * 
 */
@Component
public class ApplicationContextAwareImpl implements ApplicationContextAware {

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		getInstance().setApplicationContext(applicationContext);
	}

}
