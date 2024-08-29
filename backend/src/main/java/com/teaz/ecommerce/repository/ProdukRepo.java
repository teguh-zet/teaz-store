package com.teaz.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teaz.ecommerce.entity.Produk;

public interface ProdukRepo extends JpaRepository<Produk,String>{
    
}
