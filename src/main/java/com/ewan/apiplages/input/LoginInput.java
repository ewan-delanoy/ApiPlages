package com.ewan.apiplages.input;

public class LoginInput {
    
    private final String email;
    private final String motDePasse;
   

    public LoginInput(String email, String motDePasse) {
        this.email = email;
        this.motDePasse = motDePasse;
    }

    public String email() { return this.email ; }
    public String motDePasse() { return this.motDePasse ; }
}
