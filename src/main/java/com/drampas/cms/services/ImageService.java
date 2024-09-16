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
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final ImageConverter imageConverter;

    public Image findImageById(Long imageId){
        Optional<Image> imageOptional= imageRepository.findById(imageId);
        if(imageOptional.isPresent()){
            return imageOptional.get();
        }else throw new ImageNotFoundException("image not found");
    }

    public void saveImage(MultipartFile file){
        Image image=new Image();
        try {
            image.setImage(file.getBytes());
            image.setFileType(file.getContentType());
            image.setFileName(file.getOriginalFilename());
        } catch (IOException e) {
            throw new UploadFailedException(e.getMessage());
        }
        //checking for duplicates
        if(findImageByFileName(file.getOriginalFilename())==null){
            imageRepository.save(image);
        }else throw new ImageAlreadyExistsException("image already exists");
    }
    public Image findImageByFileName(String fileName){
        Optional<Image> optionalImage=imageRepository.findByFileName(fileName);
        if(optionalImage.isPresent()){
            return optionalImage.get();
        }else return null;
    }
}
