package com.drampas.cms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {
    private Long Id;
    //change to string for the base64 encoding and
    private Byte[] imageContent;
    private Long articleId;
}
