package com.drampas.cms.repositories;

import com.drampas.cms.model.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends CrudRepository<Image,Long> {

    Optional<Image> findByFileName(String filename);
}
