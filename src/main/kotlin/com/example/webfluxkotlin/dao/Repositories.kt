package com.example.webfluxkotlin.dao

import com.example.webfluxkotlin.model.Person
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : ReactiveCrudRepository<Person, String>