package com.iset.produits.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iset.produits.entities.Produit;
public interface ProduitRepository extends JpaRepository<Produit, Long> {

}
