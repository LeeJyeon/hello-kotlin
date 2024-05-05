package hello.config

import hello.entity.Article
import hello.entity.User
import hello.repository.ArticleRepository
import hello.repository.UserRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BlogConfiguration {

    @Bean
    fun dbInitializer(userRepository: UserRepository, articleRepository: ArticleRepository): ApplicationRunner {

        return ApplicationRunner() {
            val smaldini = userRepository.save(User("smaldini", "Stéphane", "Maldini"))
            articleRepository.save(Article(
                    title = "Reactor Bismuth is out",
                    headline = "Lorem ipsum",
                    content = "dolor sit amet",
                    author = smaldini
            ))
            articleRepository.save(Article(
                    title = "Reactor Aluminium has landed",
                    headline = "Lorem ipsum",
                    content = "dolor sit amet",
                    author = smaldini
            ))

            val sanzio = userRepository.save(User("sanzio.jyeon", "JIHYUN", "LEE", "테스트 유저"))
            articleRepository.save(
                    Article("Kotlin 으로 해보자", "성공하면 혁명", "이것은 설명입니다. 착실히해보시지요", sanzio))


            articleRepository.save(
                    Article("JAVA 으로 해보자", "실패하면 반역", "때려치자", sanzio))




        }
    }
}