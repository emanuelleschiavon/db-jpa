package org.example.db_jpa.inbound

import org.example.db_jpa.domain.Person
import org.example.db_jpa.infra.PersonRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PersonController(
    private val personRepository: PersonRepository,
) {

    @PostMapping("/persons")
    fun create(@RequestBody person: Person) = personRepository.save(person)
        .let { ResponseEntity(it, HttpStatus.CREATED) }

    @GetMapping("/persons/{id}")
    fun getBy(@PathVariable id: Long) = personRepository.findPersonById(id)
        ?.let { ResponseEntity(it, HttpStatus.OK) }
        ?: ResponseEntity(HttpStatus.NOT_FOUND)
}