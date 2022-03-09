package com.iset.produits;

import java.util.List;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
			System.out.println(p.toString());
		}
    }
	@GetMapping("/produits")
	public ResponseEntity<List<Produit>> getAll() {
		List<Produit> list = service.getAllProduits();
		
		return list.size()!=0?ResponseEntity.accepted().body(list):ResponseEntity.notFound().build();

		// return service.getAllProduits();
	}
	@GetMapping("/produits/{id}")
	public ResponseEntity<Produit> getById(@PathVariable(name = "id", required = false) String id) {
		Produit p = service.getProduit(Long.parseLong(id));
		return p!=null?ResponseEntity.accepted().body(service.getProduit(Long.parseLong(id))):ResponseEntity.notFound().build();
	}

	@PostMapping("/produits")
	public ResponseEntity<String> create(@RequestBody Produit produit) {
		produit.setDateCreation(new Date());
		Produit a ;
		if(produit.getNomProduit()==null||produit.getPrixProduit()==null) {
			a=null;
		}else{
			 a = service.saveProduit(produit);
		}
        return a!=null?ResponseEntity.accepted().body(a.toString()):ResponseEntity.badRequest().body("Error");
	}

	@DeleteMapping("/produits/{id}")
	public ResponseEntity<String> delete(@PathVariable(name = "id", required = false) String id) {
		return service.deleteProduitById(Long.parseLong(id))?ResponseEntity.accepted().body("Produit deleted"):ResponseEntity.notFound().build();
	}
	@RequestMapping(value = "/produits/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> update(@PathVariable(name = "id", required = false) String id,@RequestBody Produit produit) {
		Boolean exist = service.existsById(Long.parseLong(id));
		if (exist) {
			Produit ret = new Produit();
			Produit c = new Produit();
				ret = service.updateProduit(Long.parseLong(id),produit);
				c = new Produit();
				c.setDateCreation(ret.getDateCreation());
				c.setNomProduit(ret.getNomProduit());
				c.setPrixProduit(ret.getPrixProduit());
			return c!=null?ResponseEntity.ok().body(ret.toString()):ResponseEntity.badRequest().build();
		}
		return ResponseEntity.notFound().build();
	}
}
