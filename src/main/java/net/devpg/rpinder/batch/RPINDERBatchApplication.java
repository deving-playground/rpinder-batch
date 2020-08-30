package net.devpg.rpinder.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@EnableBatchProcessing
@SpringBootApplication
public class RPINDERBatchApplication {

	public static void main(String[] args) {
		// SpringApplication.run(RPINDERBatchApplication.class, args);
		ConfigurableApplicationContext context = SpringApplication.run(RPINDERBatchApplication.class, args);
		SpringApplication.exit(context);
	}

}
