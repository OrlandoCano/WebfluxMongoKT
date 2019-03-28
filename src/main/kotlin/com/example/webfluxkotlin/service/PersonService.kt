package com.example.webfluxkotlin.service

import com.example.webfluxkotlin.dao.PersonRepository
import com.example.webfluxkotlin.model.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.function.Consumer

interface PersonService {
    fun save (person: Mono<Person>) : Mono<Person>
    fun loadAll() : Flux<Person>
    fun load (id: String) : Mono<Person>
    fun delete(id:String)

}

@Service
class PersonServiceImpl : PersonService{

    @Autowired
    lateinit var repository : PersonRepository

    override fun save(person: Mono<Person>): Mono<Person> {
        return person.flatMap {p -> repository.save(p)}
    }

    override fun loadAll(): Flux<Person> {
        return repository.findAll()
    }

    override fun load(id: String) = repository.findById(id)

    override fun delete(id: String){
        var d = repository.deleteById(id)
        d.subscribe(System.out::println)
    }


}