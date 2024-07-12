package org.example.db_jpa.infra

import org.example.db_jpa.domain.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : JpaRepository<Person, Long> {
    fun save(person: Person): Person
    fun findPersonById(id: Long): Person?
}