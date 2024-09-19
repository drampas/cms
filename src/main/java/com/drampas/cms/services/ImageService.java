package com.drampas.cms.services;

import com.drampas.cms.exceptions.ImageAlreadyExistsException;
import com.drampas.cms.exceptions.ImageNotFoundException;
import com.drampas.cms.exceptions.UploadFailedException;
import com.drampas.cms.model.Image;
import com.drampas.cms.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public Image findImageById(Long imageId){
        Optional<Image> imageOptional= imageRepository.findById(imageId);
        if(imageOptional.isPresent()){
            return imageOptional.get();
        }else throw new ImageNotFoundException("image not found");
    }

    public Long saveImage(MultipartFile file){
        //checking for duplicates and returning the existing image id through the exception
        if(findImageByFileName(file.getOriginalFilename())!=null){
            throw new ImageAlreadyExistsException("image already exists with id: "+findImageByFileName(file.getOriginalFilename()).getId());
        }
        try {
            Image image=new Image();
            image.setImage(file.getBytes());
            image.setFileType(file.getContentType());
            image.setFileName(file.getOriginalFilename());
            imageRepository.save(image);
        } catch (IOException e) {
            throw new UploadFailedException(e.getMessage());
        }
        Long id=findImageByFileName(file.getOriginalFilename()).getId();
        return id;
    }

    public void deleteImage(Long id){
        Image image = findImageById(id);
        image.getArticles().forEach(article -> {article.setImage(null);});
        imageRepository.deleteById(id);
    }
    public Image findImageByFileName(String fileName){
        Optional<Image> optionalImage=imageRepository.findByFileName(fileName);
        if(optionalImage.isPresent()){
            return optionalImage.get();
        }else return null;
    }
}
