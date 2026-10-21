package com.example.qlsach;

public class Book {
    private String tenSach;
    private String tacGia;
    private String theLoai;
    private String tinhTrang;
    private float danhGia;
    private String ghiChu;

    public Book(String tenSach, String tacGia, String theLoai,
                String tinhTrang, float danhGia, String ghiChu) {
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.theLoai = theLoai;
        this.tinhTrang = tinhTrang;
        this.danhGia = danhGia;
        this.ghiChu = ghiChu;
    }

    public String getTenSach() { return tenSach; }
    public String getTacGia() { return tacGia; }
    public String getTheLoai() { return theLoai; }
    public String getTinhTrang() { return tinhTrang; }
    public float getDanhGia() { return danhGia; }
    public String getGhiChu() { return ghiChu; }

    public void setTinhTrang(String tinhTrang) { this.tinhTrang = tinhTrang; }
    public void setDanhGia(float danhGia) { this.danhGia = danhGia; }
}
