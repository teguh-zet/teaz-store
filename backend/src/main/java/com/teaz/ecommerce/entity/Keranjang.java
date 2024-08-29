package com.teaz.ecommerce.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
public class Keranjang implements Serializable {
    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "produk_id",referencedColumnName = "id")
    private Produk produk;
    @ManyToOne
    @JoinColumn(name = "pengguna_id",referencedColumnName = "id")
    private Pengguna pengguna;
    private double kuantitas;
    private BigDecimal harga;
    private BigDecimal jumlah;
    @Temporal(TemporalType.TIMESTAMP)
    private Date waktuDibuat;

}
