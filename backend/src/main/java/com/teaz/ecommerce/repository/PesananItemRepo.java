package com.teaz.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teaz.ecommerce.entity.PesananItem;

public interface PesananItemRepo extends JpaRepository<PesananItem,String>{
    
}
