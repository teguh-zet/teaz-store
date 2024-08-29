package com.teaz.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teaz.ecommerce.entity.Pesanan;


public interface PesananRepo extends JpaRepository<Pesanan, String> {
    
}