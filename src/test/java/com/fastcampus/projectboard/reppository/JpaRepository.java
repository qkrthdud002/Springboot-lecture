package com.fastcampus.projectboard.reppository;

import com.fastcampus.projectboard.config.JpaConfig;
import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.repository.ArticleCommentRepository;
import com.fastcampus.projectboard.repository.ArticleRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.hamcrest.Matchers;

import java.util.List;

//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class) // auditing (자동으로 연결해주는 기능).
@DataJpaTest
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;

    private final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository
    ) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

//     .hasSize() 부분에서 오류남.
    @DisplayName("select 테스트")
    @Test
    void givenTestData_whenSelecting_thenWorkFine() {
        // Given

        // When
        List<Article> articles = articleRepository.findAll();

        // Then

        assertThat(articles)
                .isNotNull()
                .hasSize(123);


    }

    @DisplayName("insert 테스트")
    @Test
    void givenTestData_whenInserting_thenWorksFine() {
        // Given
        long previousCount = articleRepository.count();

        // When
        Article savedArticle = articleRepository.save(Article.of("new article", "new content", "#spring"));


        // Then
        assertThat(articleRepository.count()).isEqualTo(previousCount + 1);



    }

}
