package com.drampas.cms.services;

import com.drampas.cms.dto.ImageDto;
import com.drampas.cms.model.Image;
import com.drampas.cms.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public Image findImageById(Long imageId){
        Optional<Image> imageOptional= imageRepository.findById(imageId);
        if(imageOptional.isPresent()){
            return imageOptional.get();
        }else throw new RuntimeException("image not found");
    }

    public void saveImage(ImageDto imageDto){
        Image image=new Image();
        image.setImageContent(imageDto.getImageContent());
        imageRepository.save(image);
    }
}
