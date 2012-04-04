package com.blogspot.nurkiewicz.quartz.demo

import org.springframework.stereotype.Service
import com.weiglewilczek.slf4s.Logging

/**
 * @author Tomasz Nurkiewicz
 * @since 01.04.12, 17:50
 */
@Service
class Printer extends Logging {

	def print(msg: String) {
		logger.info(msg)
	}

}