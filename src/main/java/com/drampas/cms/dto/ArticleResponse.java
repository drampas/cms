package com.drampas.cms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleResponse {

    private Long id;
    private String title;
    private String content;
    private String downloadUri;
}
