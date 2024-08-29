package com.teaz.ecommerce.security.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.teaz.ecommerce.entity.Pengguna;
import com.teaz.ecommerce.repository.PenggunaRepo;
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    PenggunaRepo penggunaRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Pengguna pengguna = penggunaRepo.findById(username)
                .orElseThrow(()-> new UsernameNotFoundException("Username" + username + "tidak ditemukan"));
        return UserDetailsImpl.build(pengguna); 
        
    }
    

}
