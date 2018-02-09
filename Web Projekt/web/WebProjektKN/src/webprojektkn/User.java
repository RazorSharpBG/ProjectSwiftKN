/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webprojektkn;

/**
 *
 * @author Koleto
 */
public class User {
    
    private String username;
    private String password;
    private String name;
    private String gsm;
    private String address;
    private String email;
    
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setGsm(String gsm) {
        this.gsm = gsm;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getName() {
        return name;
    }
    
    public String getGsm() {
        return this.gsm;
    }
    
    public String getAddress() {
        return this.address;
    }
    
    public String getEmail() {
        return this.email;
    }
    
}
