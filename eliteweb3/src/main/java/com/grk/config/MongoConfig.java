package com.grk.config;

import java.net.UnknownHostException;

import org.cloudfoundry.runtime.env.CloudEnvironment;
import org.cloudfoundry.runtime.env.MongoServiceInfo;
import org.cloudfoundry.runtime.service.document.MongoServiceCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;

@Configuration
@EnableMongoRepositories
// @Profile("cloud")
public class MongoConfig {

	private Logger LOG = LoggerFactory.getLogger(MongoConfig.class);
	
	@Autowired
	private Environment env;

	@Bean
	public MongoDbFactory mongoDbFactory() {
		LOG.debug("mongoDbFactory()");

		String[] activeProfiles = env.getActiveProfiles();
		LOG.info("# of activeProfiles: " + activeProfiles.length);

		LOG.debug("VCAP_APPLICATION:: "
				+ System.getenv("VCAP_APPLICATION"));
		LOG.debug("VCAP_SERVICES:: " + System.getenv("VCAP_SERVICES"));

		if (System.getenv("VCAP_APPLICATION") == null) {
			LOG.info("configuring for Mongo: Local");
			try {
				return new SimpleMongoDbFactory(new Mongo(), "test");
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		} else {
			LOG.info("configuring for Mongo: Cloud Foundry");
			CloudEnvironment cloudEnvironment = new CloudEnvironment();
			MongoServiceInfo serviceInfo = cloudEnvironment.getServiceInfo(
					"mongolab-be5bb", MongoServiceInfo.class);
			LOG.debug("serviceInfo: " + serviceInfo.getUri());
			MongoServiceCreator serviceCreator = new MongoServiceCreator();
			return serviceCreator.createService(serviceInfo);
		}
	}

	@Bean
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongoDbFactory());
	}

	// @Bean
	// public JacksonRepositoryPopulatorFactoryBean repositoryPopulator() {
	//
	// JacksonRepositoryPopulatorFactoryBean factory = new
	// JacksonRepositoryPopulatorFactoryBean();
	// // Set a custom ObjectMapper if Jackson customization is needed
	// Resource[] resources =
	// new Resource[] { new ClassPathResource("data.json") };
	// factory.setResources(resources);
	// return factory;
	// }
}
