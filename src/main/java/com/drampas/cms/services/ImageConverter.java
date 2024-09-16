package com.drampas.cms.services;


import com.drampas.cms.exceptions.UploadFailedException;
import com.drampas.cms.model.Image;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class ImageConverter {

    public Image multipartFileToImage(MultipartFile file){
        Image image=new Image();
        try {
            image.setImage(file.getBytes());
            image.setFileType(file.getContentType());
            image.setFileName(file.getOriginalFilename());
            return image;
        } catch (IOException e) {
            throw new UploadFailedException("upload failed");
        }
    }

}
