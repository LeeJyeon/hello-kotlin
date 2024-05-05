package hello.controller


import hello.repository.UserRepository
import hello.util.toSlug
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HtmlControllerTest(@Autowired val restTemplate: TestRestTemplate) {

    @BeforeEach
    fun setUp(): Unit {
        println(">> setUp")
    }

    @AfterEach
    fun clearAll(): Unit {
        println(">> clearAll")
    }


    @Test
    fun `이것은 테스트 코드입니다`() {
        println("Test1")
    }

    @Test
    fun `홈 페이지 문구 잘 나오는지 확인`() {
        val entity = restTemplate.getForEntity<String>("/")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains("Hello Sanzio")
    }

    @Test
    fun `Assert article page title, content and status code`() {
        val title = "Reactor Aluminium has landed"
        val entity = restTemplate.getForEntity<String>("/article/${title.toSlug()}")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains(title, "Lorem ipsum", "dolor sit amet")
    }

}

