
package dynamaps;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.boot.context.web.SpringBootServletInitializer#configure(org.springframework.boot.builder.SpringApplicationBuilder)
	 */
	@Override
	protected SpringApplicationBuilder configure(
		final SpringApplicationBuilder application) {
		return application.sources(SensorsApplication.class);
	}
}