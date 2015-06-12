package com.example.thuan.qltc_ver4;

/**
 * Created by truon_000 on 12/06/2015.
 */
public class TaiSan {
    String ID_TK;
    String Ten_TK;
    int Tong;

    public TaiSan(String ID_TK, String ten_TK, int tong) {
        this.ID_TK = ID_TK;
        Ten_TK = ten_TK;
        Tong = tong;
    }

    public String getID_TK() {
        return ID_TK;
    }

    public void setID_TK(String ID_TK) {
        this.ID_TK = ID_TK;
    }

    public String getTen_TK() {
        return Ten_TK;
    }

    public void setTen_TK(String ten_TK) {
        Ten_TK = ten_TK;
    }

    public int getTong() {
        return Tong;
    }

    public void setTong(int tong) {
        Tong = tong;
    }
}
