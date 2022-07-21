package com.fastcampus.projectboard.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Data REST 테스트 - API 테스트")
@Transactional // test에는 기본적으로 Rollback으로 적용
@AutoConfigureMockMvc // 해당 어노테이션을 선언하지 않으면 MockMvc 사용 불가
@SpringBootTest
public class DataRestTest {

    private final MockMvc mvc;


    public DataRestTest(@Autowired  MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[api] 게시글 리스트 조회")
    @Test
    void givenNothing_whenRequesting_thenReturnsArticlesJsonResonse(){
        // Given

        // When & Then
        try {
            mvc.perform(get("/api/articles"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("[api] 게시글 단건 조회")
    @Test
    void givenNothing_whenRequestingArticle_thenReturnsArticlesJsonResonse(){
        // Given

        // When & Then
        try {
            mvc.perform(get("/api/articles/1"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("[api] 게시글 -> 댓글 리스트 조회")
    @Test
    void givenNothing_whenRequestingArticleCommentsFromArticle_thenReturnsArticlesJsonResonse(){
        // Given

        // When & Then
        try {
            mvc.perform(get("/api/articles/1/articleComments"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("[api] 댓글 리스트 조회")
    @Test
    void givenNothing_whenRequestingArticleComments_thenReturnsArticlesCommentsJsonResonse(){
        // Given

        // When & Then
        try {
            mvc.perform(get("/api/articleComments"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("[api] 댓글 단건 조회")
    @Test
    void givenNothing_whenRequestingArticleComment_thenReturnsArticleCommentJsonResonse(){
        // Given

        // When & Then
        try {
            mvc.perform(get("/api/articleComments/1"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
