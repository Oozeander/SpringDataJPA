package com.oozeander.spring;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.cfg.Environment;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.Resource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import({DataJpaConfig.class})
public class JavaConfig {}

@Configuration
@ComponentScan(basePackages = {"com.oozeander.service", "com.oozeander.repository"})
@EnableJpaRepositories(basePackages = "com.oozeander.repository")
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
class DataJpaConfig {
	@Autowired
	private ConfigurableEnvironment env;
	// For using HBMs
	@Autowired
	private ApplicationContext context;

	@Bean(destroyMethod = "close")
	DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getRequiredProperty("db.driver"));
		dataSource.setUrl(env.getRequiredProperty("db.url"));
		dataSource.setUsername(env.getRequiredProperty("db.user"));
		dataSource.setPassword(env.getRequiredProperty("db.pass"));
		return dataSource;
	}

	@Bean LocalContainerEntityManagerFactoryBean entityManagerFactory(final DataSource dataSource) throws IOException {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource);
		entityManagerFactory.setPackagesToScan("com.oozeander.model"/*, "..", ".."*/);
		// Use HBMs
		Resource[] resources = context.getResources("classpath*:hbm");
		List<String> names = new ArrayList<>();

		for (Resource resource : resources) {
			names.addAll(Files.list( resource.getFile().toPath()).map (path -> path.getFileName().toString())
					.filter(p -> p.endsWith( "hbm.xml")).map (p -> "hbm/".concat(p)).collect(Collectors.toList()));

			System.out.println(Arrays.stream(names.toArray(new String[0])).collect(Collectors.toList()));
			entityManagerFactory.setMappingResources(names.toArray(new String[0]));
		}
		entityManagerFactory.setJpaProperties(jpaProperties());
		entityManagerFactory.setPersistenceProvider(new HibernatePersistenceProvider());
		return entityManagerFactory;
	}

	private Properties jpaProperties() {
		Properties jpaProperties = new Properties();
		jpaProperties.putAll(
				Map.of(
						Environment.DIALECT, env.getRequiredProperty("db.dialect"),
						Environment.HBM2DDL_AUTO, env.getRequiredProperty("db.hbm2ddl_auto"),
						Environment.SHOW_SQL, env.getRequiredProperty("db.show_sql"),
						Environment.FORMAT_SQL, env.getRequiredProperty("db.format_sql"),
						Environment.ENABLE_LAZY_LOAD_NO_TRANS, env.getRequiredProperty("db.enable_lazy_load_no_trans")
						));
		return jpaProperties;
	}

	@Bean PlatformTransactionManager transactionManager(final LocalContainerEntityManagerFactoryBean entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory.getObject());
	}

	@Bean PersistenceExceptionTranslationPostProcessor exceptionTranslationPostProcessor() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
}