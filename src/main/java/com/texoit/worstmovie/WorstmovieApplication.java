package com.texoit.worstmovie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@ComponentScan(basePackages = {"com.texoit.worstmovie.service", "com.texoit.worstmovie.web.rest", "com.texoit.worstmovie.entity.mapper"})
@EntityScan(basePackages = {"com.texoit.worstmovie.entity"})
public class WorstmovieApplication {

	private static final Logger log = LoggerFactory.getLogger(WorstmovieApplication.class);

	public static void main(String[] args) throws UnknownHostException {
		SpringApplication app = new SpringApplication(WorstmovieApplication.class);
		Environment env = app.run(args).getEnvironment();
		String protocol = "http";
		if (env.getProperty("server.ssl.key-store") != null) {
			protocol = "https";
		}
		log.info("\n----------------------------------------------------------\n\t" +
				"Application '{}' is running! Access URLs:\n\t" +
				"Local: \t\t{}://localhost:{}\n\t" +
				"External: \t{}://{}:{}\n\t",
			env.getProperty("spring.application.name"),
			protocol,
			env.getProperty("server.port"),
			protocol,
			InetAddress.getLocalHost().getHostAddress(),
			env.getProperty("server.port"));
	}

}
