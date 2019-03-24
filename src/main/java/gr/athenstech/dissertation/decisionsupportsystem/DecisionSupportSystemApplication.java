package gr.athenstech.dissertation.decisionsupportsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import gr.athenstech.dissertation.decisionsupportsystem.configurations.properties.FileStorageProperties;
import gr.athenstech.dissertation.decisionsupportsystem.configurations.properties.MailProperties;

@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class, MailProperties.class
})
public class DecisionSupportSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(DecisionSupportSystemApplication.class, args);
	}
}
