package com.teaz.ecommerce.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teaz.ecommerce.entity.Kategori;
import com.teaz.ecommerce.exception.ResourceNotFoundException;
import com.teaz.ecommerce.repository.KategoriRepo;

@Service
public class KategoriService {
    @Autowired
    private KategoriRepo kategoriRepo;

    public Kategori findById(String id){
        return kategoriRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("kategori dengan id "+ id +"tidak ditemukan"));
    }
    public List<Kategori> findAll(){
        return kategoriRepo.findAll();
    }
    public Kategori create(Kategori kategori){
        kategori.setId(UUID.randomUUID().toString());
        return kategoriRepo.save(kategori);

    }
    public Kategori edit(Kategori kategori){
        return kategoriRepo.save(kategori);
    }
    public void deleteById(String id){
        kategoriRepo.deleteById(id);
    }
    
}
