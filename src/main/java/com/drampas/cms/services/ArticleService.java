package com.drampas.cms.services;

import com.drampas.cms.dto.ArticleDto;
import com.drampas.cms.model.Article;
import com.drampas.cms.repositories.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<Article> findAll(){
        List<Article> articles=new ArrayList<>();
        articleRepository.findAll().iterator().forEachRemaining(articles::add);
        return articles;
    }

    public Article findArticleById(String id){
        Optional<Article> article=articleRepository.findById(Long.valueOf(id));
        if (article.isPresent()){
            return article.get();
        }else throw new RuntimeException("article not found");
    }

    public void saveOrUpdate(ArticleDto articleDto){
        Article article=convertDtoToEntity(articleDto);
         articleRepository.save(article);
    }
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

    private ArticleDto convertEntityToDto(Article article){
        ArticleDto articleDto=new ArticleDto();
        articleDto.setId(article.getId());
        articleDto.setTitle(article.getTitle());
        articleDto.setContent(article.getContent());
        articleDto.setImages(article.getImages());
        return articleDto;
    }
    private Article convertDtoToEntity(ArticleDto articleDto){
        Article article=new Article();
        article.setId(articleDto.getId());
        article.setTitle(articleDto.getTitle());
        article.setContent(articleDto.getContent());
        article.setImages(articleDto.getImages());
        return article;
    }


}
