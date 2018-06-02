package cz.upce.unicorn.configuration;

import cz.upce.unicorn.util.ExcludeFromTests;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "cz.upce.unicorn")
@ExcludeFromTests
public class HelloWorldConfiguration {
	

}