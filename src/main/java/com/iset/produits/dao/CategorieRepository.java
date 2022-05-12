package com.iset.produits.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.iset.produits.entities.Categorie;
import com.iset.produits.entities.Produit;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    List<Categorie> findByNomCat(String nom);
    
    Page<Categorie> findAllByNomCatContains(String nom,Pageable pageable);


}
