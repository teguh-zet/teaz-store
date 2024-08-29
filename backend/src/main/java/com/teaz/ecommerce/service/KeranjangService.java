package com.teaz.ecommerce.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teaz.ecommerce.entity.Keranjang;
import com.teaz.ecommerce.entity.Pengguna;
import com.teaz.ecommerce.entity.Produk;
import com.teaz.ecommerce.exception.BadRequestException;
import com.teaz.ecommerce.repository.KeranjangRepo;
import com.teaz.ecommerce.repository.ProdukRepo;

@Service
public class KeranjangService {

    @Autowired
    private KeranjangRepo keranjangRepo;

    @Autowired
    private ProdukRepo produkRepo;
    

    @Transactional
    public Keranjang addKeranjang(String username, String produkId, Double kuantitas) {
        // apakah produk ada dalam database
        // apakah sudah ada dalam keranjang milik user
        // jika sudah maka update kuantitas nya dan hitung
        // jika belum buat baru
        
        Produk produk = produkRepo.findById(produkId)
                .orElseThrow(() -> new BadRequestException(
                        "produk id " + produkId + " tidak ditemukan"));
        Optional<Keranjang> optional = keranjangRepo.findByPenggunaIdAndProdukId(username, produkId);
        Keranjang keranjang;
        if (optional.isPresent()) {
            keranjang = optional.get();
            keranjang.setKuantitas(keranjang.getKuantitas() + kuantitas);
            keranjang.setJumlah(new BigDecimal(keranjang.getHarga().doubleValue() * keranjang.getKuantitas()));
            keranjangRepo.save(keranjang);
        } else {
            keranjang = new Keranjang();
            keranjang.setId(UUID.randomUUID().toString());
            keranjang.setProduk(produk);
            keranjang.setKuantitas(kuantitas);
            keranjang.setHarga(produk.getHarga());
            keranjang.setJumlah(new BigDecimal(keranjang.getHarga().doubleValue() * keranjang.getKuantitas()));
            keranjang.setPengguna(new Pengguna(username));
            keranjangRepo.save(keranjang);
        }
        return keranjang;
    }
    @Transactional
    public Keranjang updateKuantitas(String username, String produkId, Double kuantitas) {
        Keranjang keranjang = keranjangRepo.findByPenggunaIdAndProdukId(username, produkId)
                .orElseThrow(() -> new BadRequestException(
                        "Produk ID " + produkId + " tidak ditemukan didalam keranjang anda"));
        keranjang.setKuantitas(keranjang.getKuantitas() + kuantitas);
        keranjang.setJumlah(new BigDecimal(keranjang.getHarga().doubleValue() * keranjang.getKuantitas()));
        keranjangRepo.save(keranjang);
        return keranjang;
    }
    @Transactional
    public void delete(String username, String produkId) {
        Keranjang keranjang = keranjangRepo.findByPenggunaIdAndProdukId(username, produkId)
                .orElseThrow(() -> new BadRequestException(
                        "Produk ID " + produkId + " tidak ditemukan didalam keranjang anda"));

        keranjangRepo.delete(keranjang);
    }

    public List<Keranjang> findByPenggunaId(String username) {
        return keranjangRepo.findByPenggunaId(username);
    }
}
