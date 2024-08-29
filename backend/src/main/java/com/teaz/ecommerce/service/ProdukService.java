package com.teaz.ecommerce.service;

import java.util.List;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.teaz.ecommerce.entity.Produk;
import com.teaz.ecommerce.repository.KategoriRepo;
import com.teaz.ecommerce.repository.ProdukRepo;
import com.teaz.ecommerce.exception.*;
@Service
public class ProdukService {
    @Autowired
    private KategoriRepo kategoriRepo;
    @Autowired 
    private ProdukRepo produkRepo;

    // public List<Produk> findRange(String filterText, int page, int limit){

    // }
   
    public List<Produk> findAll(){
        return produkRepo.findAll();
    }
    public Produk findById(String id ){
        return produkRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("produk dengan id "+id+" tidak ditemukan"));
    }
    public Produk create(Produk produk){

        if(!StringUtils.hasText(produk.getNama())){
            throw new BadRequestException("nama produk tidak boleh kosong");
        }
        if(produk.getKategori().getId() == null){
            throw new BadRequestException("kategori produk tidak boleh kosong");
        }
        if(!StringUtils.hasText(produk.getKategori().getId())){
            throw new BadRequestException("id kategori produk tidak boleh kosong");
        }
        kategoriRepo.findById(produk.getKategori().getId())
        .orElseThrow(() -> new BadRequestException("id kategori"+produk.getKategori().getId()+" produk tidak ditemukan didalam database"));
        produk.setId(UUID.randomUUID().toString());

        return produkRepo.save(produk);
    }
    public Produk edit(Produk produk){
        if(!StringUtils.hasText(produk.getId())){
            throw new BadRequestException("id produk tidak boleh kosong");
        }
        if(!StringUtils.hasText(produk.getNama())){
            throw new BadRequestException("Produk Id harus diisi");
        }
        if(produk.getKategori().getId() == null){
            throw new BadRequestException("kategori produk tidak boleh kosong");
        }
        if(!StringUtils.hasText(produk.getKategori().getId())){
            throw new BadRequestException("id kategori produk tidak boleh kosong");
        }
        kategoriRepo.findById(produk.getKategori().getId())
        .orElseThrow(() -> new BadRequestException("id kategori"+produk.getKategori().getId()+" produk tidak ditemukan didalam database"));
        

        return produkRepo.save(produk);
    }
    public Produk ubahGambar(String id, String gambar){
        Produk produk = findById(id);
        produk.setGambar(gambar);
        return produkRepo.save(produk);
    }
    public void deleteById(String id){
        produkRepo.deleteById(id);
    }
}
