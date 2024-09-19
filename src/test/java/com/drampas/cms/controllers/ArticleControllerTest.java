package com.drampas.cms.controllers;

import com.drampas.cms.dto.ArticleDto;
import com.drampas.cms.model.Article;
import com.drampas.cms.services.ArticleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ArticleControllerTest {

    @Mock
    private ArticleService articleService;
    @InjectMocks
    private ArticleController articleController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllArticles() {
        Article article1=new Article();
        article1.setId(1L);
        Article article2=new Article();
        article2.setId(2L);
        List<Article> articles=Arrays.asList(article1, article2);

        when(articleService.findAll()).thenReturn(articles);
        ResponseEntity<?> response = articleController.getAllArticles();

        assertEquals(HttpStatus.OK,response.getStatusCode());
        verify(articleService,times(1)).findAll();
    }
    @Test
    void testGetArticleById() {
        Long id=1L;
        Article article=new Article();
        article.setId(id);

        when(articleService.findArticleById(id)).thenReturn(article);

        ResponseEntity<?> response=articleController.getArticleById(String.valueOf(id));

        assertEquals(HttpStatus.OK,response.getStatusCode());
        verify(articleService,times(1)).findArticleById(id);
    }
    @Test
    void testSaveArticle_Valid() {
        ArticleDto articleDto=new ArticleDto();
        articleDto.setTitle("Article");
        articleDto.setContent("Content");
        MultipartFile file=mock(MultipartFile.class);
        articleDto.setImage(file);

        ResponseEntity<?> response=articleController.saveArticle(articleDto);

        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        verify(articleService,times(1)).saveOrUpdate(articleDto, file, null);
    }
    @Test
    void testDeleteArticle() {
        Long id=1L;
        ResponseEntity<?> response=articleController.deleteArticle(String.valueOf(id));

        assertEquals(HttpStatus.OK,response.getStatusCode());
        verify(articleService,times(1)).deleteArticle(id);
    }
}