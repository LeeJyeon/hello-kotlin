package hello.controller

import com.ninjasquad.springmockk.MockkBean
import hello.entity.Article
import hello.entity.User
import hello.repository.ArticleRepository
import hello.repository.UserRepository
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest
class HttpControllerTests(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    private lateinit var userRepository: UserRepository

    @MockkBean
    private lateinit var articleRepository: ArticleRepository

    @Test
    fun `아티클 리스트`() {
        val sanzio = User("sanzio.jyeon", "JIHYUN", "LEE", "테스트 유저")
        val article1 = Article("Kotlin 으로 해보자", "성공하면 혁명", "이것은 설명입니다. 착실히해보시지요", sanzio)
        val article2 = Article("Java 으로 해보자", "실패하면 사형", "이것은 설명입니다. 착실히해보시지요", sanzio)

        every { articleRepository.findAllByOrderByAddedAtDesc() } returns listOf(article1, article2)

        mockMvc.perform(get("/api/article/").accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("\$.[0].author.login").value(sanzio.login))
                .andExpect(jsonPath("\$.[0].slug").value(article1.slug))
                .andExpect(jsonPath("\$.[1].author.login").value(sanzio.login))
                .andExpect(jsonPath("\$.[1].slug").value(article2.slug))
    }


    @Test
    fun `유저 리스트`() {
        val sanzio = User("sanzio.jyeon", "JIHYUN", "LEE", "테스트 유저")
        val chacha = User("chacha.j", "YOONSEO", "JANG", "테스트 유저")

        every { userRepository.findAll() } returns listOf(sanzio, chacha)

        mockMvc.perform(get("/api/user/").accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("\$.[0].login").value(sanzio.login))
                .andExpect(jsonPath("\$.[1].login").value(chacha.login))


    }
}