package com.drampas.cms.services;

import com.drampas.cms.dto.ArticleDto;
import com.drampas.cms.exceptions.ArticleNotFoundException;
import com.drampas.cms.model.Article;
import com.drampas.cms.model.Image;
import com.drampas.cms.repositories.ArticleRepository;
import com.drampas.cms.repositories.ImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ArticleServiceTest {

    @Mock
    private ArticleRepository articleRepository;
    @Mock
    private ImageRepository imageRepository;
    @InjectMocks
    private ArticleService articleService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<Article> articles=Arrays.asList(new Article(), new Article());
        when(articleRepository.findAll()).thenReturn(articles);

        List<Article> result=articleService.findAll();

        assertEquals(articles.size(),result.size());
        verify(articleRepository,times(1)).findAll();
    }
    @Test
    void testFindArticleById() {
        Long id=1L;
        Article article=new Article();
        article.setId(id);
        when(articleRepository.findById(id)).thenReturn(Optional.of(article));

        Article result = articleService.findArticleById(id);

        assertEquals(article,result);
        verify(articleRepository,times(1)).findById(id);
    }
    @Test
    void testFindArticleByIdInvalidId() {
        Long id=1L;
        when(articleRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ArticleNotFoundException.class,() -> articleService.findArticleById(id));
        verify(articleRepository,times(1)).findById(id);
    }
    @Test
    void testSaveOrUpdate_CreateNewArticle() {
        ArticleDto articleDto=new ArticleDto();
        articleDto.setTitle("Title");
        articleDto.setContent("Content");
        MultipartFile file=mock(MultipartFile.class);

        when(file.isEmpty()).thenReturn(true);
        when(articleRepository.save(any(Article.class))).thenReturn(new Article());

        articleService.saveOrUpdate(articleDto,file, null);

        verify(articleRepository,times(1)).save(any(Article.class));
        verify(imageRepository,never()).save(any(Image.class));
    }
    @Test
    void testDeleteArticle_ValidId() {
        Long id=1L;
        Article article=new Article();
        when(articleRepository.findById(id)).thenReturn(Optional.of(article));

        articleService.deleteArticle(id);

        verify(articleRepository,times(1)).deleteById(id);
    }
}