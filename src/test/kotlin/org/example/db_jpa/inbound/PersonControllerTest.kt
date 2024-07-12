package org.example.db_jpa.inbound

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.example.db_jpa.domain.Person
import org.example.db_jpa.infra.PersonRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.time.LocalDate
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
class PersonControllerTest {

    @InjectMockKs
    private lateinit var personController: PersonController

    @MockK
    private lateinit var personRepository: PersonRepository

    @Test
    fun `calls person repository when create person`() {
        val person = Person(1L, "Marielle Franco", LocalDate.of(1980, 3, 13))
        every { personRepository.save(person) } returns person

        val result = personController.create(person)

        assertEquals(ResponseEntity(person, HttpStatus.CREATED), result)
    }

    @Test
    fun `returns 200 when person exists in database`() {
        val existentId = 1L
        val person = Person(1L, "Marielle Franco", LocalDate.of(1980, 3, 13))
        every { personRepository.findPersonById(existentId) } returns person

        val result = personController.getBy(existentId)

        assertEquals(ResponseEntity(person, HttpStatus.OK), result)
    }

    @Test
    fun `returns 404 when person does not exist in database`() {
        val nonexistentId = 5L
        every { personRepository.findPersonById(nonexistentId) } returns null

        val result = personController.getBy(nonexistentId)

        assertEquals(ResponseEntity(HttpStatus.NOT_FOUND), result)
    }
}