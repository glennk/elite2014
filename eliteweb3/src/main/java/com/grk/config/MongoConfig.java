package com.grk.config;

import java.net.UnknownHostException;

import javax.annotation.Resources;

import org.cloudfoundry.runtime.env.CloudEnvironment;
import org.cloudfoundry.runtime.env.MongoServiceInfo;
import org.cloudfoundry.runtime.service.document.MongoServiceCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.repository.init.JacksonRepositoryPopulatorFactoryBean;

import com.mongodb.Mongo;

@Configuration
// @Profile("cloud")
public class MongoConfig {

	@Bean
	public MongoDbFactory mongoDbFactory() {
		System.out.println("mongoDbFactory()");

		System.out.println("VCAP_APPLICATION:: " + System.getenv("VCAP_APPLICATION"));
		System.out.println("VCAP_SERVICES:: " + System.getenv("VCAP_SERVICES"));

		if (System.getenv("VCAP_APPLICATION") == null) {
			System.out.println("configuring for Mongo: Local");
			try {
				return new SimpleMongoDbFactory(new Mongo(), "test");
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		} else {
			System.out.println("configuring for Mongo: Cloud Foundry");
			CloudEnvironment cloudEnvironment = new CloudEnvironment();
			MongoServiceInfo serviceInfo = cloudEnvironment.getServiceInfo(
					"mongolab-be5bb", MongoServiceInfo.class);
			System.out.println("serviceInfo: " + serviceInfo.getUri());
			MongoServiceCreator serviceCreator = new MongoServiceCreator();
			return serviceCreator.createService(serviceInfo);
		}
	}

	@Bean
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongoDbFactory());
	}

//	@Bean
//	  public JacksonRepositoryPopulatorFactoryBean repositoryPopulator() {
//
//	    JacksonRepositoryPopulatorFactoryBean factory = new JacksonRepositoryPopulatorFactoryBean();
//	    // Set a custom ObjectMapper if Jackson customization is needed
//	    Resource[] resources =
//	            new Resource[] { new ClassPathResource("data.json") };
//	    factory.setResources(resources);
//	    return factory;
//	  }
}
