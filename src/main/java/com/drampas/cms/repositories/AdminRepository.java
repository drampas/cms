package com.drampas.cms.repositories;

import com.drampas.cms.model.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<Admin,Long> {

    Admin findByUsername(String username);
}
