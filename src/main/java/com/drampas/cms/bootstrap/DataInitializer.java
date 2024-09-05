package com.drampas.cms.bootstrap;

import com.drampas.cms.model.Admin;
import com.drampas.cms.model.Article;
import com.drampas.cms.repositories.AdminRepository;
import com.drampas.cms.repositories.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private final AdminRepository adminRepository;
    private final ArticleRepository articleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Admin admin=new Admin();
        admin.setUsername("admin");
        admin.setPassword(bCryptPasswordEncoder.encode("password"));
        adminRepository.save(admin);

        Article article=new Article();
        article.setTitle("Test article");
        article.setContent("Some content");
        articleRepository.save(article);

        Article article2=new Article();
        article2.setTitle("Test article2");
        article2.setContent("Some more content");
        articleRepository.save(article2);
    }
}
