package com.example.thuan.qltc_ver4;

/**
 * Created by truon_000 on 12/06/2015.
 */
public class Users {
    String ID="ID";
    String MatKhau="MATKHAU";
    String Ten="TEN";
    int Luong = 3000000;

    public Users(String ID, String matKhau, String ten, int luong) {
        this.ID = ID;
        this.MatKhau = matKhau;
        this.Ten = ten;
        this.Luong = luong;
    }
    public Users() {
    }

    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public String getMatKhau() {
        return MatKhau;
    }
    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }
    public String getTen() {
        return Ten;
    }
    public void setTen(String ten) {
        Ten = ten;
    }
    public int getLuong() {
        return Luong;
    }
    public void setLuong(int luong) {
        Luong = luong;
    }
}
