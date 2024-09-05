package com.drampas.cms.controllers;

import com.drampas.cms.dto.ArticleDto;
import com.drampas.cms.model.Article;
import com.drampas.cms.services.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public ResponseEntity<?> getAllArticles(){
        List<Article> articles=articleService.findAll();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getArticleById(@PathVariable String id){
        Article article=articleService.findArticleById(id);
        return new ResponseEntity<>(article,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> saveArticle(@RequestBody ArticleDto articleDto){
        articleService.saveOrUpdate(articleDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateArticle(@RequestBody ArticleDto articleDto,@PathVariable String id){
        articleDto.setId(Long.valueOf(id));
        articleService.saveOrUpdate(articleDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable String id){
        articleService.deleteArticle(Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
