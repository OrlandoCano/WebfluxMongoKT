package com.example.webfluxkotlin.model

import org.springframework.data.annotation.Id
import java.util.*


data class Person (@Id
                   var id: String?,
                   var firstName: String,
                   var lastName: String,
                   var email: String,
                   var address: String,
                   var createdDate: Date = Date())

