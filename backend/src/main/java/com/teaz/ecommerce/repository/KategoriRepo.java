package com.teaz.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teaz.ecommerce.entity.Kategori;

public interface KategoriRepo extends JpaRepository<Kategori,String> {
    
}

