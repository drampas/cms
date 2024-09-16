package com.drampas.cms.controllers;

import com.drampas.cms.model.Image;
import com.drampas.cms.services.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @Operation(description = "Get an image by its Id")
    @SecurityRequirements()
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<?> getImage(@PathVariable String id){
        Image image=imageService.findImageById(Long.valueOf(id));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename= "+image.getFileName())
                .body(new ByteArrayResource(image.getImage()));
    }
    @Operation(description = "Post an image")
    @PostMapping
    public ResponseEntity<?> saveImage(@RequestParam("image") MultipartFile file) {
        imageService.saveImage(file);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
