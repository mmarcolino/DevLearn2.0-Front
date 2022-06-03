package br.com.devLearn.application.integration

import br.com.devLearn.application.model.User
import br.com.devLearn.application.repository.UserRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest


@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private lateinit var userRepository: UserRepository

    @AfterEach
    fun tearDown(){
        userRepository.deleteAll()
    }

    @Test
    fun `return all drivers after finding them`(){
        userRepository.deleteAll()

        //given
        userRepository.save(User(null, "Kenma123", "12345678", "Kenma"))
        userRepository.save(User(null, "Johan123", "12345678", "Johan"))

        //when
        val result = userRepository.findAll()

        //then
        Assertions.assertEquals(2, result.size)
    }

    @Test
    fun `return user after finding it by id`(){
        //given
        val user =User(null, "Kenma123", "12345678", "Kenma")
        val userId = userRepository.save(user).id ?: throw java.lang.RuntimeException("User id is null")
        //when
        val result = userRepository.getById(userId)
        //then
        Assertions.assertEquals(userId, result.id)
        Assertions.assertEquals("Kenma123", result.username)
        Assertions.assertEquals("12345678", result.password)
        Assertions.assertEquals("Kenma", result.name)
    }

    @Test
    fun `verify if user is truly deleted`(){
        //given
        val user = User(null, "Kenma123", "12345678", "Kenma")
        val userId = userRepository.save(user).id ?: throw java.lang.RuntimeException("User id is null")
        //when
        userRepository.deleteById(userId)
        //then
        Assertions.assertFalse(userRepository.existsById(userId))
    }

    @Test
    fun `verify if it returns the user after saving it`(){
        //given
        val user = User(null, "Kenma123", "12345678", "Kenma")
        //when
        val result = userRepository.save(user)
        //then
        Assertions.assertEquals(user.id, result.id)
        Assertions.assertEquals(user.username, result.username)
        Assertions.assertEquals(user.password, result.password)
        Assertions.assertEquals(user.name, result.name)
    }

}