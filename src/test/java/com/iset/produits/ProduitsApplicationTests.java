package com.iset.produits;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.iset.produits.dao.ProduitRepository;
import com.iset.produits.entities.Categorie;
// import com.iset.produits.dao.ProduitRepository;
import com.iset.produits.entities.Produit;
import com.iset.produits.service.ProduitServiceImpl;

import org.springframework.data.domain.Page;

@SpringBootTest
class ProduitsApplicationTests {

	@Autowired
	private ProduitServiceImpl service;
	@Autowired
	private ProduitRepository produitRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void testCreateProduit() {
		Produit prod = new Produit("PC Asus", 1500.500, new Date());
		service.saveProduit(prod);
		// produitRepository.save(prod);
	}

	@Test
	public void testFindProduit() {
		Produit p = service.getProduit(1L);
		// produitRepository.findById(1L).get();
		System.out.println(p);
	}

	//
	@Test
	public void testUpdateProduit() {
		Produit p = service.getProduit(1L);
		// produitRepository.findById(1L).get();
		p.setPrixProduit(2000.0);
		p.setNomProduit("PC acer");
		service.saveProduit(p);
		// produitRepository.save(p);
		System.out.println(p);
	}

	//
	@Test
	public void testDeleteProduit() {
		service.deleteProduitById(1L);
		// produitRepository.deleteById(1L);
	}

	//
	@Test
	public void testFindAllProduits() {
		List<Produit> prods = service.getAllProduits();
		// produitRepository.findAll();
		for (Produit p : prods)
			System.out.println(p.getNomProduit());
	}

	@Test
	public void testFindByNomProduit() {
		List<Produit> prods = produitRepository.findByNomProduit("PC Asus");
		for (Produit p : prods) {
			System.out.println("testFindByNomProduit " + p.getNomProduit());
		}
	}

	@Test
	public void testFindByNomProduitContains() {
		List<Produit> prods = produitRepository.findByNomProduitContains("PC");
		for (Produit p : prods) {
			System.out.println(p.getNomProduit());
		}
	}

	@Test
	public void testfindByNomPrix() {
		List<Produit> prods = produitRepository.findByNomPrix("PC ASUS", 1000.0);
		for (Produit p : prods) {
			System.out.println(p);
		}
	}

	@Test
	public void testfindByCategorie() {
		Categorie cat = new Categorie();
		cat.setIdCat(1L);
		List<Produit> prods = produitRepository.findByCategorie(cat);
		for (Produit p : prods) {
			System.out.println(p);
		}
	}

	@Test
	public void findByCategorieIdCat() {
		List<Produit> prods = produitRepository.findByCategorieIdCat(1L);
		for (Produit p : prods) {
			System.out.println(p);
		}
	}

	@Test
	public void testfindByOrderByNomProduitAsc() {
		List<Produit> prods = produitRepository.findByOrderByNomProduitAsc();
		for (Produit p : prods) {
			System.out.println(p);
		}
	}

	@Test
	public void testTrierProduitsNomsPrix() {
		List<Produit> prods = produitRepository.trierProduitsNomsPrix();
		for (Produit p : prods) {
			System.out.println(p);
		}
	}
}
