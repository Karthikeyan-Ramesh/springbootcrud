package com.example.sbwmcum.config;

import com.mongodb.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.morphia.Datastore;
import xyz.morphia.Morphia;

@Configuration
public class DataSourceConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfig.class);

    @Autowired
    private MongoProperties mongoProperties;
    private Morphia morphia() {

        final Morphia morphia = new Morphia();
        return morphia;
    }

    @Bean(name = "emp_details")
    public Datastore dataSource(MongoClient mongoClient) {
        LOGGER.info("Creating emp_details DataSource");

        final Datastore datastore = morphia().createDatastore(mongoClient, mongoProperties.getDatabase());
        return datastore;
    }


}
