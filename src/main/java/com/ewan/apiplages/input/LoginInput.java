package com.ewan.apiplages.input;

public class LoginInput {
    
    private final String email;
    private final String motDePasse;
   

    public LoginInput(String email, String motDePasse) {
        this.email = email;
        this.motDePasse = motDePasse;
    }

    public String getEmail() { return this.email ; }
    public String getMotDePasse() { return this.motDePasse ; }
}
