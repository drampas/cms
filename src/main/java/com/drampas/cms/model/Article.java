package com.drampas.cms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    //@Column(columnDefinition = "varchar(max)") error?? use @Lob instead
    //using @Lob because an article could be big
    @Lob
    private String content;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "imageId",nullable = true,updatable = true)
    private Image image;
}
