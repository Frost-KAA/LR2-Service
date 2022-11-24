package com.example.lr3auth2

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication
class Lr3auth2Application

fun main(args: Array<String>) {
	runApplication<Lr3auth2Application>(*args)
}
