package com.drampas.cms.services;

import com.drampas.cms.dto.ArticleDto;
import com.drampas.cms.exceptions.ArticleNotFoundException;
import com.drampas.cms.exceptions.InvalidArticleException;
import com.drampas.cms.model.Article;
import com.drampas.cms.model.Image;
import com.drampas.cms.repositories.ArticleRepository;
import com.drampas.cms.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;


@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ImageRepository imageRepository;
    private final ImageConverter imageConverter;

    public List<Article> findAll(){
        List<Article> articles=new ArrayList<>();
        articleRepository.findAll().iterator().forEachRemaining(articles::add);
        return articles;
    }

    public Article findArticleById(Long id){
        Optional<Article> article=articleRepository.findById(Long.valueOf(id));
        if (article.isPresent()){
            return article.get();
        }else throw new ArticleNotFoundException("article not found");
    }

    public void saveOrUpdate(ArticleDto articleDto, MultipartFile file,Long id){
        Article article = (id == null) ? new Article() : findArticleById(id);

        //checking if the article has title and content(could use validation)
        if(articleDto.getTitle().isEmpty() || articleDto.getContent().isEmpty()){
            throw new InvalidArticleException("article title and article content are required");
        }
        article.setTitle(articleDto.getTitle());
        article.setContent(articleDto.getContent());

        //checking if the file is empty,but we allow articles without images
        if(!file.isEmpty()){
            Image image=duplicateResolver(file);
            Image saved=imageRepository.save(image);
            article.setImage(saved);
        }
        articleRepository.save(article);
    }
    public void deleteArticle(Long id) {
        findArticleById(id);
        articleRepository.deleteById(id);
    }
    //Check if the image already exists in the db,and if so return the existing image in order to avoid duplicates
    private Image duplicateResolver(MultipartFile file){
        Image image=imageConverter.multipartFileToImage(file);
        Optional<Image> imageToSave=imageRepository.findByFileName(image.getFileName());
        if(imageToSave.isPresent()){
            return imageToSave.get();
        }else return image;
    }
}
