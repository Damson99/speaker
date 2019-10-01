package com.speaker;

import com.speaker.controller.UserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;

@SpringBootApplication
@ComponentScan({"com.speaker", "controller"})
public class SpeakerApplication extends SpringBootServletInitializer
{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
	{
		return application.sources(SpeakerApplication.class);
	}

	public static void main(String[] args) throws Exception
	{
		new File(UserController.uploadDirectory).mkdir();
		new File(UserController.postUploadDirectory).mkdir();
		SpringApplication.run(SpeakerApplication.class, args);
	}
}
