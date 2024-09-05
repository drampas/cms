package com.drampas.cms.controllers;

import com.drampas.cms.dto.ImageDto;
import com.drampas.cms.model.Image;
import com.drampas.cms.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getImage(@PathVariable String id){
        Image image=imageService.findImageById(Long.valueOf(id));
        return new ResponseEntity<>(image, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> saveImage(@RequestBody ImageDto imageDto){
        imageService.saveImage(imageDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
