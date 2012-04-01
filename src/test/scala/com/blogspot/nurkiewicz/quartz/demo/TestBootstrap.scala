package com.blogspot.nurkiewicz.quartz.demo

import org.h2.tools.Server
import org.springframework.context.annotation.{Bean, Configuration}

/**
 * @author Tomasz Nurkiewicz
 * @since 01.04.12, 19:23
 */
@Configuration
class TestBootstrap {

	@Bean(initMethod = "start", destroyMethod = "stop")
	def h2WebServer() = Server.createWebServer("-web", "-webAllowOthers", "-webDaemon")

}
