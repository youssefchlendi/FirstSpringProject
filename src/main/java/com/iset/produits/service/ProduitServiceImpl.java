package com.iset.produits.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iset.produits.entities.Produit;
import com.iset.produits.dao.*;
@Service
public class ProduitServiceImpl implements ProduitService{
	@Autowired
	ProduitRepository produitRepository;
	@Override
	public Produit saveProduit(Produit p) {
	return produitRepository.save(p);
	}
	@Override
	public Produit updateProduit(Long id,Produit p) {
		if(produitRepository.existsById(id)) {
			Produit a = produitRepository.getById(id);
			a.setNomProduit(p.getNomProduit());
			a.setPrixProduit(p.getPrixProduit());
			this.saveProduit(a);
			return a;
		}
	return null;
	}
	@Override
	public void deleteProduit(Produit p) {
	produitRepository.delete(p);
	}
	@Override
	public String deleteProduitById(Long id) {
		if(produitRepository.existsById(id)) {
			produitRepository.deleteById(id);
			return "Done";
		}
		return "Produit non existant";
	}
	@Override
	public Produit getProduit(Long id) {
	return produitRepository.existsById(id)==false?null:produitRepository.findById(id).get();
	}
	@Override
	public List<Produit> getAllProduits() {
	return produitRepository.findAll();
	}

}
