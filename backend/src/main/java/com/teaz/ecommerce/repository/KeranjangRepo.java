package com.teaz.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teaz.ecommerce.entity.Keranjang;

public interface KeranjangRepo extends JpaRepository<Keranjang,String>{

    Optional<Keranjang> findByPenggunaIdAndProdukId(String username, String produkId);

    List<Keranjang> findByPenggunaId(String username);
    
}
