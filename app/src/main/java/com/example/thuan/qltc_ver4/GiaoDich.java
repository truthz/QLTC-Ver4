package com.example.thuan.qltc_ver4;

/**
 * Created by truon_000 on 12/06/2015.
 */
public class GiaoDich {
    String ID;
    String user_ID;
    String BenThu2;
    String Ten_TK;
    String Loai_GD;
    int SoTien;
    String ngay;

    public GiaoDich(String ID, String user_ID, String benThu2, String ten_TK, String loai_GD, int soTien, String ngay) {
        this.ID = ID;
        this.user_ID = user_ID;
        BenThu2 = benThu2;
        Ten_TK = ten_TK;
        Loai_GD = loai_GD;
        SoTien = soTien;
        this.ngay = ngay;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(String user_ID) {
        this.user_ID = user_ID;
    }

    public String getBenThu2() {
        return BenThu2;
    }

    public void setBenThu2(String benThu2) {
        BenThu2 = benThu2;
    }

    public String getTen_TK() {
        return Ten_TK;
    }

    public void setTen_TK(String ten_TK) {
        Ten_TK = ten_TK;
    }

    public String getLoai_GD() {
        return Loai_GD;
    }

    public void setLoai_GD(String loai_GD) {
        Loai_GD = loai_GD;
    }

    public int getSoTien() {
        return SoTien;
    }

    public void setSoTien(int soTien) {
        SoTien = soTien;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }
}
