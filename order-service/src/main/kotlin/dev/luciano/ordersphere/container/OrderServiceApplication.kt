package dev.luciano.ordersphere.container

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication(scanBasePackages = ["dev.luciano.ordersphere"])
@EnableJpaRepositories(basePackages = ["dev.luciano.ordersphere.infrastructure.persistence"])
@EntityScan(basePackages = ["dev.luciano.ordersphere.infrastructure.persistence.entity", "dev.luciano.ordersphere.infrastructure"])
class OrderServiceApplication

fun main(args: Array<String>) {
    runApplication<OrderServiceApplication>(*args)
}
