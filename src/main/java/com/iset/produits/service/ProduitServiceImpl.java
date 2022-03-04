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
	public Boolean existsById(Long id){
		return produitRepository.existsById(id);
	}
	@Override
	public Produit updateProduit(Long id,Produit p) {
		if(produitRepository.existsById(id)) {
			Produit a = produitRepository.getById(id);
			a.setNomProduit(p.getNomProduit()!=null?p.getNomProduit():a.getNomProduit());
			a.setPrixProduit(p.getPrixProduit()!=null?p.getPrixProduit():a.getPrixProduit());
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
	public Boolean deleteProduitById(Long id) {
		if(produitRepository.existsById(id)) {
			produitRepository.deleteById(id);
			return true;
		}
		return false;
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
