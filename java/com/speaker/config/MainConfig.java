package com.speaker.config;

import com.speaker.repository.DBUserRepository;
import com.speaker.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class MainConfig
{
    @Bean(name="DBUserRepository")
    @Profile("prod")
    public UserService createDBRepo()
    {
        UserService repo = new DBUserRepository();
        return repo;
    }
}
