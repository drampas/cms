package com.drampas.cms.controllers;

import com.drampas.cms.dto.ArticleDto;
import com.drampas.cms.dto.ArticleResponse;
import com.drampas.cms.model.Article;
import com.drampas.cms.services.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.util.List;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @Operation(description = "Get a list of all articles")
    @SecurityRequirements()
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllArticles(){
        List<Article> articles=articleService.findAll();
        List<ArticleResponse> articleResponses=articles.stream().map(this::entityToResponse).toList();
        return new ResponseEntity<>(articleResponses, HttpStatus.OK);
    }
    @Operation(description = "Get an article by its id")
    @SecurityRequirements()
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getArticleById(@PathVariable String id){
        Article article=articleService.findArticleById(id);
        ArticleResponse response=entityToResponse(article);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @Operation(description = "Post a new article")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> saveArticle(@ModelAttribute ArticleDto article){
        articleService.saveOrUpdate(article,article.getImage());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @Operation(description = "Update an existing article")
    @PutMapping(value = "/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateArticle(@ModelAttribute ArticleDto article,@PathVariable String id){
        article.setId(Long.valueOf(id));
        articleService.saveOrUpdate(article, article.getImage());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Operation(description = "Delete an article")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable String id){
        articleService.deleteArticle(Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private ArticleResponse entityToResponse(Article article){
        ArticleResponse response=new ArticleResponse();
        if(article.getImage()!=null){
            String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/images/")
                    .path(article.getImage().getId().toString())
                    .toUriString();
            response.setDownloadUri(downloadUri);
        }
        response.setId(article.getId());
        response.setTitle(article.getTitle());
        response.setContent(article.getContent());

        return response;
    }
}
