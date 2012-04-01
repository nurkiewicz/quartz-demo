package com.blogspot.nurkiewicz.quartz.demo.spring

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.context.annotation._
import org.apache.commons.dbcp.BasicDataSource
import com.googlecode.flyway.core.Flyway
import org.jdbcdslog.DataSourceProxy
import org.springframework.jdbc.datasource.{DataSourceTransactionManager, LazyConnectionDataSourceProxy}
import org.h2.Driver

/**
 * @author Tomasz Nurkiewicz
 * @since 10.10.11, 16:11
 */
@Configuration
@EnableTransactionManagement
class Persistence {

	@Bean(initMethod = "migrate")
	def flyway() = {
		val fly = new Flyway()
		fly.setDataSource(dataSource())
		fly
	}

	@Bean
	def jdbcTemplate() = new JdbcTemplate(dataSource())

	@Bean
	def transactionManager() = new DataSourceTransactionManager(dataSource())

	@Bean
	@Primary
	def dataSource() = {
		val proxy = new DataSourceProxy()
		proxy.setTargetDSDirect(dbcpDataSource())
		new LazyConnectionDataSourceProxy(proxy)
	}

	@Bean(destroyMethod = "close")
	def dbcpDataSource() = {
		val dataSource = new BasicDataSource
		dataSource.setDriverClassName(classOf[Driver].getName)
		dataSource.setUrl("jdbc:h2:mem:quartz-demo;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;MVCC=TRUE")
		dataSource.setUsername("sa")
		dataSource.setPassword("")
		dataSource.setMaxActive(20)
		dataSource.setMaxIdle(20)
		dataSource.setMaxWait(10000)
		dataSource.setInitialSize(5)
		dataSource.setValidationQuery("SELECT 1")
		dataSource
	}

}
