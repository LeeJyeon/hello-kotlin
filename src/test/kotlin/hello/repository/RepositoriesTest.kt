package hello.repository

import hello.entity.Article
import hello.entity.User
import jakarta.transaction.Transactional
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.annotation.Rollback

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RepositoriesTest @Autowired constructor(
        val entityManager: TestEntityManager,
        val userRepository: UserRepository,
        val articleRepository: ArticleRepository
) {

    @Test
    fun `아티클의 findByIdOrNull 테스트`() {
        val sanzio = User("sanzio.jyeon", "JIHYUN", "LEE", "테스트 유저")
        val article = Article("Kotlin 으로 해보자", "성공하면 혁명", "이것은 설명입니다. 착실히해보시지요", sanzio)
        entityManager.persist(sanzio)
        entityManager.persist(article)
        entityManager.flush()

        val found = articleRepository.findByIdOrNull(article.id!!)
        print(found.toString())

        assertThat(found).isEqualTo(article)
    }

    @Test
    fun `login 테스트`() {
        val sanzio = User("sanzio.jyeon", "JIHYUN", "LEE", "테스트 유저")
        entityManager.persist(sanzio)
        entityManager.flush()


        val found = userRepository.findByLogin(sanzio.login)
        print(found.toString())

        assertThat(found).isEqualTo(sanzio)
    }


}
