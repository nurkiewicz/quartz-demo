package com.blogspot.nurkiewicz.quartz.demo.spring

import javax.annotation.Resource
import org.springframework.core.io.ClassPathResource
import java.util.Properties
import org.springframework.scheduling.quartz.{SpringBeanJobFactory, SchedulerFactoryBean}
import org.springframework.scheduling.quartz.SchedulerFactoryBean._
import org.springframework.context.annotation.{DependsOn, Bean, Configuration}
import scala.collection.JavaConverters._

/**
 * @author Tomasz Nurkiewicz
 * @since 25.10.11, 15:06
 */
@Configuration
class Scheduling {

	@Resource
	val persistence: Persistence = null

	@Bean
	@DependsOn(Array("flyway"))
	def schedulerFactory() = {
		val schedulerFactoryBean = new SchedulerFactoryBean()
		schedulerFactoryBean.setDataSource(persistence.dataSource())
		schedulerFactoryBean.setTransactionManager(persistence.transactionManager())
		schedulerFactoryBean.setConfigLocation(new ClassPathResource("quartz.properties"))
		schedulerFactoryBean.setQuartzProperties(quartzProperties())
		schedulerFactoryBean.setJobFactory(jobFactory())
		schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContext")
		schedulerFactoryBean.setSchedulerContextAsMap(schedulerContextMap())
		schedulerFactoryBean.setWaitForJobsToCompleteOnShutdown(true)
		schedulerFactoryBean
	}

	def schedulerContextMap() =
		Map(
		).asJava

	@Bean
	def jobFactory() = new SpringBeanJobFactory

	@Bean
	def quartzProperties() = {
		val properties = new Properties()
		properties.put(PROP_THREAD_COUNT, 10.toString)
		properties
	}

}

