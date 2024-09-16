package com.drampas.cms.security;

import com.drampas.cms.exceptions.InvalidLoginException;
import com.drampas.cms.model.Admin;
import com.drampas.cms.repositories.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin=adminRepository.findByUsername(username);
        if (admin!=null){
            return admin;
        }else throw new InvalidLoginException("username not found");
    }
}
