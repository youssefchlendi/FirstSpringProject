package com.iset.produits;

import java.util.List;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.iset.produits.entities.Produit;
import com.iset.produits.service.ProduitServiceImpl;

@SpringBootApplication
@RestController
public class ProduitsApplication implements CommandLineRunner {
    @Autowired
    private ProduitServiceImpl service;

    public static void main(String[] args) {
        SpringApplication.run(ProduitsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Produit prod1 = new Produit("PC Asus", 1500.500, new Date());
        Produit prod2 = new Produit("PC Dell", 2000.500, new Date());
        Produit prod3 = new Produit("PC Toshiba", 2500.500, new Date());
        service.saveProduit(prod1);
        service.saveProduit(prod2);
        service.saveProduit(prod3);
        List<Produit> list = service.getAllProduits();
        System.out.println(list.size() == 0 ? "Aucun produit trouvée" : "");
        for (Produit p : list) {
            System.out.println(p.getNomProduit());
        }
    }
	/*
	@GetMapping("/produits/{id}")
	public JSONWrappedObject getById(@PathVariable(name = "id", required = false) String id) {
		Produit p = service.getProduit(Long.parseLong(id));
		if(p!=null)
			return new JSONWrappedObject("","",service.getProduit(Long.parseLong(id)));
		return new JSONWrappedObject("","",(Object)"Produit not found");
	}
	@GetMapping("/produits")
	public List<Produit> getAll() {
		return service.getAllProduits();
	}

	@PostMapping("/produits")
	public JSONWrappedObject create(@RequestBody Produit produit) {
		produit.setDateCreation(new Date());
		Produit a = service.saveProduit(produit);
		return new JSONWrappedObject("","",a!=null?"Created":"Error");
	}

	@DeleteMapping("/produits/{id}")
	public JSONWrappedObject delete(@PathVariable(name = "id", required = false) String id) {
		return new JSONWrappedObject("","",service.deleteProduitById(Long.parseLong(id)));
	}
	@RequestMapping(value = "/produits/{id}", method = RequestMethod.PUT)
	public JSONWrappedObject update(@PathVariable(name = "id", required = false) String id,@RequestBody Produit produit) {
		Produit ret = service.updateProduit(Long.parseLong(id),produit);

		if (ret!=null) {
			Produit c = new Produit();
			c.setDateCreation(ret.getDateCreation());
			c.setNomProduit(ret.getNomProduit());
			c.setPrixProduit(ret.getPrixProduit());
			return new JSONWrappedObject("","",c);
			}
		return new JSONWrappedObject("","","Produit non existant");
	}
	*/
}
