
package dynamaps.config.persistence;

import javax.persistence.EntityManagerFactory;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;

@Configuration
@EnableJpaRepositories({ "dynamaps.repository" })
@EntityScan({ "dynamaps.model" })
public class PersistenceConfiguration {

	/**
	 * Configures and returns the {@link JpaTransactionManager}.
	 *
	 * @param entityManagerFactory
	 *            the {@link EntityManagerFactory} to be used.
	 * @return the instance of {@link JpaTransactionManager}.
	 */
	@Bean
	public JpaTransactionManager transactionManager(
		final EntityManagerFactory entityManagerFactory) {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
}