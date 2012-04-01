package com.blogspot.nurkiewicz.quartz.demo

import org.springframework.test.context.ContextConfiguration
import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import javax.annotation.Resource
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import spring.Bootstrap
import reflect.BeanProperty
import com.weiglewilczek.slf4s.Logging
import org.quartz.{Job, JobExecutionContext, Scheduler}
import java.util.concurrent.TimeUnit
import org.quartz.SimpleScheduleBuilder._
import org.quartz.TriggerBuilder._
import org.quartz.JobBuilder._

/**
 * @author Tomasz Nurkiewicz
 * @since 01.04.12, 15:16
 */
@RunWith(classOf[JUnitRunner])
@ContextConfiguration(classes = Array(classOf[Bootstrap]))
class IntegrationTest extends FunSuite with ShouldMatchers with SpringRule {

	@Resource
	val scheduler: Scheduler = null

	test("Bootstrap test") {
		val trigger = newTrigger().
				withIdentity("Every-few-seconds", "Demo").
				withSchedule(
			simpleSchedule().
					withIntervalInSeconds(4).
					repeatForever()
		).
				build()

		val job = newJob(classOf[PrintMessageJob]).
				withIdentity("Print-message", "Demo").
				usingJobData("msg", "Hello, world!").
				build()

		scheduler.scheduleJob(job, trigger)

		TimeUnit.MINUTES.sleep(10)
	}

}

class PrintMessageJob extends Job with Logging {

	@BeanProperty
	var printer: Printer = _

	@BeanProperty
	var msg = ""

	def execute(context: JobExecutionContext) {
		printer print msg
	}
}
