package com.teaz.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.teaz.ecommerce.entity.Pengguna;
import com.teaz.ecommerce.exception.BadRequestException;
import com.teaz.ecommerce.exception.ResourceNotFoundException;
import com.teaz.ecommerce.repository.PenggunaRepo;

@Service
public class PenggunaService {
    @Autowired
    private PenggunaRepo penggunaRepo;

    public Pengguna findById(String id){
        return penggunaRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("pengguna dengan id "+ id +"tidak ditemukan"));
    }
    public List<Pengguna> findAll(){
        return penggunaRepo.findAll();
    }
    public Pengguna create(Pengguna pengguna){
        if (!StringUtils.hasText(pengguna.getId())) {
            throw new BadRequestException("username harus diisi");
        }
        if (penggunaRepo.existsById(pengguna.getId())) {
            throw new BadRequestException("username "+pengguna.getId()+" sudah terdaftar");
        }
        if (!StringUtils.hasText(pengguna.getEmail())) {
            throw new BadRequestException("email harus diisi ");
        }
        if (penggunaRepo.existsByEmail(pengguna.getEmail())) {
            throw new BadRequestException("Email  "+pengguna.getEmail()+" sudah terdaftar");
        }
        pengguna.setAktif(true);
        return penggunaRepo.save(pengguna);


    }
    public Pengguna edit(Pengguna pengguna){
        if (!StringUtils.hasText(pengguna.getId())) {
            throw new BadRequestException("username harus diisi");
        }
        if (!StringUtils.hasText(pengguna.getEmail())) {
            throw new BadRequestException("email harus diisi ");
        }
 

        return penggunaRepo.save(pengguna);
    }
    public void deleteById(String id){
        penggunaRepo.deleteById(id);
    }
    
}
