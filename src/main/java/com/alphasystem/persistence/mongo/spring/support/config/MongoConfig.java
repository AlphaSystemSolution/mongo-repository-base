/**
 * 
 */
package com.alphasystem.persistence.mongo.spring.support.config;

import static java.lang.System.getProperty;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

/**
 * @author sali
 * 
 */
@Configuration
@ComponentScan(basePackages = { "com.alphasystem.persistence.mongo.spring.support" })
public class MongoConfig extends AbstractMongoConfiguration {

	public static final String MONGO_DB_NAME_PROPERTY = "mongo.db.name";

	@Override
	protected String getDatabaseName() {
		return getProperty(MONGO_DB_NAME_PROPERTY, "__DEFAULT__");
	}

	@Override
	public Mongo mongo() throws Exception {
		return new MongoClient("127.0.0.1");
	}

}
