package com.blogspot.nurkiewicz.quartz.demo

import org.springframework.test.context.TestContextManager
import org.scalatest._
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext

/**
 * @author Tomasz Nurkiewicz
 * @since 08.10.11, 00:06
 */
trait SpringRule extends Suite with BeforeAndAfterAll {
	this: AbstractSuite =>

	@Autowired val applicationContext: ApplicationContext = null

	override protected def beforeAll() {
		new TestContextManager(this.getClass).prepareTestInstance(this)
		super.beforeAll();
	}

}
