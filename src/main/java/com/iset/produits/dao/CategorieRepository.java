package com.iset.produits.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.iset.produits.entities.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    List<Categorie> findByNomCat(String nom);
    
    Page<Categorie> findAllByNomCatContains(String nom,Pageable pageable);


}
