package com.iset.produits.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iset.produits.entities.Categorie;
import com.iset.produits.dao.*;
@Service
public class CategorieService{
	@Autowired
	CategorieRepository CategorieRepository;

	public Categorie saveCategorie(Categorie p) {
	return CategorieRepository.save(p);
	}
	public Boolean existsById(Long id){
		return CategorieRepository.existsById(id);
	}

	public Categorie updateCategorie(Long id,Categorie p) {
		if(CategorieRepository.existsById(id)) {
			Categorie a = CategorieRepository.getById(id);
			a.setNomCat(p.getNomCat()!=null?p.getNomCat():a.getNomCat());
			a.setDescriptionCat(p.getDescriptionCat()!=null?p.getDescriptionCat():a.getDescriptionCat());
			this.saveCategorie(a);
			return a;
		}
	return null;
	}

	public void deleteCategorie(Categorie p) {
	CategorieRepository.delete(p);
	}

	public Boolean deleteCategorieById(Long id) {
		if(CategorieRepository.existsById(id)) {
			CategorieRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public Categorie getCategorie(Long id) {
	return CategorieRepository.existsById(id)==false?null:CategorieRepository.findById(id).get();
	}

	public List<Categorie> getAllCategories() {
	return CategorieRepository.findAll();
	}

	public Page<Categorie> getAllCategoriesParPage(int page, int size) {
	return CategorieRepository.findAll(PageRequest.of(page, size));
	}


	public List<Categorie> findByNomCategorie(String nom) {
		return CategorieRepository.findByNomCat(nom);
	};

	public Page<Categorie> findByNomCatContains(String nom ,int page,int size){
		return CategorieRepository.findAllByNomCatContains(nom,PageRequest.of(page, size));
	};

	

}
