package com.iset.produits;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// import com.iset.produits.dao.ProduitRepository;
import com.iset.produits.entities.Produit;
import com.iset.produits.service.ProduitServiceImpl;


@SpringBootTest
class ProduitsApplicationTests {

	@Autowired
	private ProduitServiceImpl service;
//	private ProduitRepository produitRepository;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void testCreateProduit() {
	Produit prod = new Produit("PC Asus",1500.500,new Date());
	service.saveProduit(prod);
//	produitRepository.save(prod);
	}
	@Test
	public void testFindProduit	()
	{
	Produit p = service.getProduit(1L); 
//			produitRepository.findById(1L).get();
	System.out.println(p);
	}
//	
	@Test
	public void testUpdateProduit()
	{
	Produit p = service.getProduit(1L); 
//			produitRepository.findById(1L).get();
	p.setPrixProduit(2000.0);
	p.setNomProduit("PC acer");
	service.saveProduit(p);
//	produitRepository.save(p);
	System.out.println(p);
	}
//	
	@Test
	public void testDeleteProduit()
	{
	service.deleteProduitById(1L);
//	produitRepository.deleteById(1L);
	}
//	
	@Test
	public void testFindAllProduits()
	{
	List<Produit> prods = service.getAllProduits();
//			produitRepository.findAll();
	for (Produit p:prods)
	System.out.println(p.getNomProduit());
	}
	
}
