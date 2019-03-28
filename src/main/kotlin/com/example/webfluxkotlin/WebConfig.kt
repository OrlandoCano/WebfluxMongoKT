package com.example.webfluxkotlin

import com.example.webfluxkotlin.dao.PersonRepository
import com.example.webfluxkotlin.model.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Configuration
@EnableMongoRepositories
@EnableWebFlux
class WebConfig {

    @Autowired
    private lateinit var repository : PersonRepository

    @Bean
    fun routerFunctions() = router {
        GET("/personas"){
            _ -> ServerResponse.ok().body(repository.findAll(), Person::class.java)
        }
        GET("/personas/{id}"){
            request -> ServerResponse.ok()
                .body(repository
                        .findById(request.pathVariable("id"))
                        , Person::class.java)
        }
        POST("/personas"){
            request -> ServerResponse.ok()
                .body(request.bodyToMono(Person::class.java)
                        .flatMap { person -> repository.save(person)}
                        , Person::class.java)
        }
        PUT("/personas"){
            request -> ServerResponse.ok()
                .body(request.bodyToMono(Person::class.java)
                        .flatMap { person -> repository.save(person)}
                        , Person::class.java)
        }
        DELETE("/personas/{id}"){
            request -> repository.deleteById(request.pathVariable("id"))
                .flatMap { ServerResponse.noContent().build() }
        }
    }
}