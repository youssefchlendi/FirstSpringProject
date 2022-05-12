package com.iset.produits.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCat;
    @NotNull
	@Size(min = 4, max = 255)
    private String nomCat;
    @NotNull
	@Size(min = 4, max = 255)
    private String descriptionCat;
    @OneToMany(mappedBy = "categorie")
    private List<Produit> produits;


    public Categorie() {
    }


    public Categorie(Long idCat, String nomCat, String descriptionCat, List<Produit> produits) {
        this.idCat = idCat;
        this.nomCat = nomCat;
        this.descriptionCat = descriptionCat;
        this.produits = produits;
    }


    public Long getIdCat() {
        return this.idCat;
    }

    public void setIdCat(Long idCat) {
        this.idCat = idCat;
    }

    public String getNomCat() {
        return this.nomCat;
    }

    public void setNomCat(String nomCat) {
        this.nomCat = nomCat;
    }

    public String getDescriptionCat() {
        return this.descriptionCat;
    }

    public void setDescriptionCat(String descriptionCat) {
        this.descriptionCat = descriptionCat;
    }

    public List<Produit> getProduits() {
        return this.produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }
    

}
