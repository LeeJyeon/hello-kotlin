package hello

import hello.config.BlogProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(BlogProperties::class)
class HelloApplication

fun main(args: Array<String>) {
    runApplication<HelloApplication>(*args)
}

