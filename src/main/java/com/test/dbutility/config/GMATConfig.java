package com.test.dbutility.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.test.dbutility.gmatdb.model.GMATStudyMaster;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "gmatdbEntityManager", 
		transactionManagerRef = "gmatdbTransactionManager", 
		basePackages = "com.test.dbutility.gmatdb.repository"
)
public class GMATConfig {

	@Primary
	@Bean
	@ConfigurationProperties(prefix = "spring.gmatdb.datasource")
	public DataSource mysqlDataSource() {
		return DataSourceBuilder
					.create()
					.build();
	}

	@Primary
	@Bean(name = "gmatdbEntityManager")
	public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder
					.dataSource(mysqlDataSource())
					.properties(hibernateProperties())
					.packages(GMATStudyMaster.class)
					.persistenceUnit("gmatdbPU")
					.build();
	}

	@Primary
	@Bean(name = "gmatdbTransactionManager")
	public PlatformTransactionManager mysqlTransactionManager(@Qualifier("gmatdbEntityManager") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

	private Map<String, Object> hibernateProperties() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("hibernate.show_sql", "true");
		map.put("hibernate.format_sql", "true");
		map.put("hibernate.hbm2ddl.auto", "validate");
		return map;
	}
}
