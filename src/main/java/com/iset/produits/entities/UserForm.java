package com.iset.produits.entities;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.iset.produits.annotaion.equalPasswordsConstraint;
import com.iset.produits.annotaion.uniqueConstraint;

@equalPasswordsConstraint.List({
    @equalPasswordsConstraint(
            field = "password",
            fieldMatch = "confirmedPassword",
            message = "Les mots de passe ne sont pas identiques!"
    )
})
public
class UserForm {
    @NotNull
    @Email
    @uniqueConstraint    
    private String username;
    @NotNull
    @Size(min = 8, max = 15)
    private String password;
    @NotNull
    @Size(min = 8, max = 15)
    private String confirmedPassword;
    public UserForm(@NotNull String username, @NotNull @Size(min = 8, max = 15) String password,
            @NotNull String confirmedPassword) {
        this.username = username;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
    }

    public UserForm() {
    }


    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmedPassword() {
        return this.confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }




}