package com.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.demo.repository")
public class RepositoryConfig extends AbstractReactiveMongoConfiguration {

	@Value("${port}")
	private String port;
	@Value("${dbname}")
	private String dbName;

	@Override
	public MongoClient mongoClient() {
		// TODO Auto-generated method stub
		return MongoClients.create();
	}

	@Override
	protected String getDatabaseName() {
		// TODO Auto-generated method stub
		return dbName;
	}

	@Bean
	public ReactiveMongoTemplate reactiveMongoTemplate() {
		return new ReactiveMongoTemplate(mongoClient(), getDatabaseName());
	}
}
