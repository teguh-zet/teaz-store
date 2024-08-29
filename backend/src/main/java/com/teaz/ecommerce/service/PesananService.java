package com.teaz.ecommerce.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teaz.ecommerce.entity.Pengguna;
import com.teaz.ecommerce.entity.Pesanan;
import com.teaz.ecommerce.entity.PesananItem;
import com.teaz.ecommerce.entity.Produk;
import com.teaz.ecommerce.exception.BadRequestException;
import com.teaz.ecommerce.model.KeranjangRequest;
import com.teaz.ecommerce.model.PesananRequest;
import com.teaz.ecommerce.model.PesananResponse;
import com.teaz.ecommerce.model.StatusPesanan;
import com.teaz.ecommerce.repository.ProdukRepo;

@Service
public class PesananService {

    @Autowired
    private ProdukRepo produkRepo;
    
    @Transactional
    public PesananResponse create(String username, PesananRequest request){
        Pesanan pesanan = new Pesanan();
        pesanan.setId(UUID.randomUUID().toString());
        pesanan.setTanggal(new Date());
        pesanan.setNomor(generateNomorPesanan());
        pesanan.setPengguna(new Pengguna(username));
        pesanan.setAlamatPengiriman(request.getAlamatPengiriman());
        pesanan.setStatusPesanan(StatusPesanan.DRAFT);
        pesanan.setWaktuPesan(new Date());

        List<PesananItem> items = new ArrayList<>();

        for(KeranjangRequest k : request.getItems() ){
            Produk produk = produkRepo.findById(k.getProdukId())
            .orElseThrow(()-> new BadRequestException("Produk ID "+k.getProdukId()+" tidak ditemukan"));
            if(produk.getStok()  <  k.getKuantitas()){
                throw new BadRequestException("Stok tidak mencukupi");
            }

            PesananItem pi = new PesananItem();
            pi.setId(UUID.randomUUID().toString());
            pi.setProduk(produk);
            pi.setDeskripsi(produk.getNama());
            pi.setKuantitas(k.getKuantitas());
            pi.setHarga(produk.getHarga());
            pi.setJumlah(new BigDecimal(pi.getHarga().doubleValue() * pi.getKuantitas()));
            pi.setPesanan(pesanan);
            items.add(pi);
        }

            BigDecimal jumlah = BigDecimal.ZERO;
            for(PesananItem pesananItem : items){
                jumlah = jumlah.add(pesananItem.getJumlah());
            }

            pesanan.setJumlah(jumlah);
            pesanan.setOngkir(request.getOngkir());
            pesanan.setTotal(pesanan.getJumlah().add(pesanan.getOngkir()));

            
    }

    private String generateNomorPesanan() {
        return String.format("%016d", System.nanoTime());
    }
}
