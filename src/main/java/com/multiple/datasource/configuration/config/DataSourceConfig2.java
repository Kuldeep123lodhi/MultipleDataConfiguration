package com.multiple.datasource.configuration.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.multiple.datasource.configuration.entity.Product;
import com.zaxxer.hikari.HikariDataSource;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "containerEntityManagerFactoryBean2",
						transactionManagerRef = "productTransactionManager",
		basePackages = { "com.multiple.datasource.configuration.product.repository" }
        
)
public class DataSourceConfig2 {

//	@Autowired
//	public Environment environment;

	/*
	 * // @Bean // public DataSource dataSource() { // // DriverManagerDataSource
	 * dataSource= new DriverManagerDataSource(); //
	 * dataSource.setDriverClassName(environment.getProperty(
	 * "second.datasource.driver-class-name")); //
	 * dataSource.setUrl(environment.getProperty("second.datasource.url")); //
	 * dataSource.setUsername(environment.getProperty("second.datasource.username"))
	 * ; //
	 * dataSource.setPassword(environment.getProperty("second.datasource.password"))
	 * ; // // return dataSource; // }
	 */

	@Bean(name="dataSource1")
	@ConfigurationProperties(prefix="second.datasource")
	public DataSource dataSource1() {

		return DataSourceBuilder.create().build();
	}

	/*
	 * @Bean
	 * 
	 * @Primary
	 * 
	 * @ConfigurationProperties("second.datasource") public DataSourceProperties
	 * dataSourceProperties() { return new DataSourceProperties(); }
	 * 
	 * @Bean
	 * 
	 * @ConfigurationProperties("app.datasource.configuration") public
	 * HikariDataSource dataSource(DataSourceProperties properties) { return
	 * properties.initializeDataSourceBuilder().type(HikariDataSource.class).build()
	 * ; }
	 */

	@Bean(name = "containerEntityManagerFactoryBean2")
	public LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean(
			EntityManagerFactoryBuilder builder,@Qualifier("dataSource1") DataSource dataSource1) {
		return builder.dataSource(dataSource1)
				.packages("com.multiple.datasource.configuration.entity")
				.build();

	}
	@Bean(name="productTransactionManager")
	public PlatformTransactionManager productTransactionManager(@Qualifier("containerEntityManagerFactoryBean2" ) EntityManagerFactory containerEntityManagerFactoryBean2) {
		
		return new JpaTransactionManager(containerEntityManagerFactoryBean2);
	}
}
