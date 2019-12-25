package fr.zaroumia.helloworldv2;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableBatchProcessing
public class HelloWorldV2Application {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Bean
	public Step helloWorldStep() {
		return stepBuilderFactory.get("step")
				.tasklet(new HelloWorldTasklet())
				.build();
	}

	@Bean
	public Job helloWorldJob() {
		return jobBuilderFactory.get("job")
				.start(helloWorldStep())
				.build();
	}

	public static void main(final String[] args) {
		SpringApplication.run(HelloWorldV2Application.class, args);
	}

}
