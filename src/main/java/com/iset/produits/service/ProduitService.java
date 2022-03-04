package com.iset.produits.service;

import java.util.List;


import com.iset.produits.entities.Produit;
public interface ProduitService {
	Produit saveProduit(Produit p);
	Produit updateProduit(Long id,Produit p);
	void deleteProduit(Produit p);
	String deleteProduitById(Long id);
	Produit getProduit(Long id);
	List<Produit> getAllProduits();
}
