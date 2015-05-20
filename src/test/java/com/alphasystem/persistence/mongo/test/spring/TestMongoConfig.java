/**
 * 
 */
package com.alphasystem.persistence.mongo.test.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.alphasystem.persistence.mongo.spring.support.config.MongoConfig;

/**
 * @author sali
 * 
 */
@Configuration
@EnableMongoRepositories(basePackages = { "com.alphasystem.persistence.mongo.test.repository" })
public class TestMongoConfig extends MongoConfig {


}
