package com.k.web.constructor.service;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.annotation.PostConstruct;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {

    @Value("${app.server.scheme}")
    @Getter
    private String scheme;

    @Value("${app.server.host}")
    @Getter
    private String host;

    @Value("${app.server.port}")
    @Getter
    private int port;

    @PostConstruct
    private void logProperties() {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        ReflectionUtils.doWithFields(this.getClass(), field -> {
            logger.info("Field \"" + field.getName() + "\", value \"" + field.get(this) + "\"");
        });
    }

}
