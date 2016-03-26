
package dynamaps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@PropertySources({
		@PropertySource(value = { "classpath:dynamaps-service.properties" }),
		@PropertySource(value = { "file:${catalina.base}/conf/dynamaps-service.properties" }) })
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@IntegrationComponentScan
public class DynamapsApplication {

	/**
	 * Spring Boot application entry point.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(
		final String[] args) {
		final SpringApplication application = new SpringApplication(DynamapsApplication.class);
		application.setShowBanner(false);
		final ApplicationContext ctx = application.run(args);
	}
}