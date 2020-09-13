/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

/**
 *
 * @author ASUS
 */
public class ThongKe {
    private int thangId;
    private String tenThang;
    private int dangChoDuyet;
    private int daGiaoHang;
    private int daHuyDon;

    public ThongKe() {
    }

    public ThongKe(int thangId, String tenThang, int dangChoDuyet, int daGiaoHang, int daHuyDon) {
        this.thangId = thangId;
        this.tenThang = tenThang;
        this.dangChoDuyet = dangChoDuyet;
        this.daGiaoHang = daGiaoHang;
        this.daHuyDon = daHuyDon;
    }

    public int getThangId() {
        return thangId;
    }

    public void setThangId(int thangId) {
        this.thangId = thangId;
    }

    public String getTenThang() {
        return tenThang;
    }

    public void setTenThang(String tenThang) {
        this.tenThang = tenThang;
    }

    public int getDangChoDuyet() {
        return dangChoDuyet;
    }

    public void setDangChoDuyet(int dangChoDuyet) {
        this.dangChoDuyet = dangChoDuyet;
    }

    public int getDaGiaoHang() {
        return daGiaoHang;
    }

    public void setDaGiaoHang(int daGiaoHang) {
        this.daGiaoHang = daGiaoHang;
    }

    public int getDaHuyDon() {
        return daHuyDon;
    }

    public void setDaHuyDon(int daHuyDon) {
        this.daHuyDon = daHuyDon;
    }
    
    
}
