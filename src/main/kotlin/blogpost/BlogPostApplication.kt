package blogpost

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = ["blogpost.post"])
class BlogPostApplication

fun main(args: Array<String>) {
    runApplication<BlogPostApplication>(*args)
}
