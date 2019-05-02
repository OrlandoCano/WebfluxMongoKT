package com.example.webfluxkotlin.controller

import com.example.webfluxkotlin.model.Person
import com.example.webfluxkotlin.service.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/people")
//@CrossOrigin("http://localhost:3000")
class PersonController{
    @Autowired
    private lateinit var service: PersonService

    @PostMapping
    fun create (@RequestBody person : Person) : Mono<Person>{
        return service.save(Mono.just(person))
    }

    @PutMapping
    fun update (@RequestBody person : Person) : Mono<Person>{
        return service.save(Mono.just(person))
    }

    @GetMapping("/{id}")
    fun load(@PathVariable id:String) : Mono<Person>{
        return service.load(id)
    }

    @GetMapping
    fun loadAll() : Flux<Person>{
        return service.loadAll()
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable id:String) {
        service.delete(id)
    }

}