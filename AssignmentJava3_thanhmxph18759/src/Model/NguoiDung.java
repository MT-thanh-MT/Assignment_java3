/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author XUÂN THÀNH
 */
public class NguoiDung {
    private String user,pass,vaiTro;

    public NguoiDung() {
    }

    public NguoiDung(String user, String pass, String vaiTro) {
        this.user = user;
        this.pass = pass;
        this.vaiTro = vaiTro;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }
    
}
