package com.example.btlweb.entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="book")
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tieu_de;
    private String tac_gia;
    private Date ngay_ph;
    private Integer so_trang;
    private String the_loai;
    private Integer da_ban;
    private String mo_ta;
    private String bia_sach;
    
    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    List<Order> orders;
    
    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    List<Review> reviews;
    
    public Book() {
    }

    public Book(Integer id, String tieu_de, String tac_gia, Date ngay_ph, Integer so_trang, String the_loai, Integer da_ban, String mo_ta, String bia_sach) {
        this.id = id;
        this.tieu_de = tieu_de;
        this.tac_gia = tac_gia;
        this.ngay_ph = ngay_ph;
        this.so_trang = so_trang;
        this.the_loai = the_loai;
        this.da_ban = da_ban;
        this.mo_ta = mo_ta;
        this.bia_sach = bia_sach;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTieu_de() {
        return tieu_de;
    }

    public void setTieu_de(String tieu_de) {
        this.tieu_de = tieu_de;
    }

    public String getTac_gia() {
        return tac_gia;
    }

    public void setTac_gia(String tac_gia) {
        this.tac_gia = tac_gia;
    }

    public Date getNgay_ph() {
        return ngay_ph;
    }

    public void setNgay_ph(Date ngay_ph) {
        this.ngay_ph = ngay_ph;
    }

    public Integer getSo_trang() {
        return so_trang;
    }

    public void setSo_trang(Integer so_trang) {
        this.so_trang = so_trang;
    }

    public String getThe_loai() {
        return the_loai;
    }

    public void setThe_loai(String the_loai) {
        this.the_loai = the_loai;
    }

    public Integer getDa_ban() {
        return da_ban;
    }

    public void setDa_ban(Integer da_ban) {
        this.da_ban = da_ban;
    }

    public String getMo_ta() {
        return mo_ta;
    }

    public void setMo_ta(String mo_ta) {
        this.mo_ta = mo_ta;
    }

    public String getBia_sach() {
        return bia_sach;
    }

    public void setBia_sach(String bia_sach) {
        this.bia_sach = bia_sach;
    }
}
