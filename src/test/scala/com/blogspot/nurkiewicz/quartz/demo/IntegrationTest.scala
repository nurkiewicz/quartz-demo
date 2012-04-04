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
