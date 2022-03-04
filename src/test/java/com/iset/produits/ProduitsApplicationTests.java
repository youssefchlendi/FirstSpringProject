package com.iset.produits;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.iset.produits.dao.ProduitRepository;
import com.iset.produits.entities.Produit;


@SpringBootTest
class ProduitsApplicationTests {

	@Autowired
	private ProduitRepository produitRepository;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void testCreateProduit() {
	Produit prod = new Produit("PC Asus",1500.500,new Date());
	produitRepository.save(prod);
	}
	
	@Test
	public void testFindProduit()
	{
	Produit p = produitRepository.findById(1L).get();
	System.out.println(p);
	}
	
	@Test
	public void testUpdateProduit()
	{
	Produit p = produitRepository.findById(1L).get();
	p.setPrixProduit(2000.0);
	p.setNomProduit("PC acer");
	produitRepository.save(p);
	System.out.println(p);
	}
	
	@Test
	public void testDeleteProduit()
	{
	produitRepository.deleteById(1L);
	}
	
	@Test
	public void testFindAllProduits()
	{
	List<Produit> prods = produitRepository.findAll();
	for (Produit p:prods)
	System.out.println(p.getNomProduit());
	}
	
}
