/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author XUÂN THÀNH
 */
public class LoginAccountList implements Serializable{
    private String passWord,username;

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LoginAccountList(String passWord, String username) {
        this.passWord = passWord;
        this.username = username;
    }

    public LoginAccountList() {
    }
}
