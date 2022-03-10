package com.iset.produits.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iset.produits.entities.Produit;
import com.iset.produits.service.ProduitService;

@Controller
public class CatController {
	@Autowired
	ProduitService produitService;

	@RequestMapping("/showCreate")
	public String showCreate() {
		return "createProduit";
	}

	@RequestMapping("/saveProduit")
	public String saveProduit(@ModelAttribute("produit") Produit produit, @RequestParam("date") String date,
			ModelMap modelMap) throws ParseException {
		// conversion de la date
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateCreation = dateformat.parse(String.valueOf(date));
		produit.setDateCreation(dateCreation);
		Produit saveProduit = produitService.saveProduit(produit);
		String msg = "produit enregistré avec Id " + saveProduit.getIdProduit();
		List<Produit> prods = produitService.getAllProduits();
		modelMap.addAttribute("produits", prods);
		modelMap.addAttribute("msg", msg);
		modelMap.addAttribute("type", "success");
		return "listeProduits";
	}

	@RequestMapping("/ListeProduits")
	public String listeProduits(ModelMap modelMap) {
		List<Produit> prods = produitService.getAllProduits();
		modelMap.addAttribute("produits", prods);
		return "listeProduits";
	}

	@RequestMapping("/supprimerProduit")
	public String supprimerProduit(@RequestParam("id") Long id, ModelMap modelMap) {
		Produit p = new Produit();
		Produit p1 = produitService.getProduit(id);
		p.setIdProduit(id);
		produitService.deleteProduit(p);
		List<Produit> prods = produitService.getAllProduits();
		modelMap.addAttribute("produits", prods);
		if (p1!=null){
			modelMap.addAttribute("msg", "Produit supprimée");
			modelMap.addAttribute("type", "danger");
		}else{
			modelMap.addAttribute("msg", "Id non existant");
			modelMap.addAttribute("type", "warning");
		}

		return "listeProduits";
	}

	@RequestMapping("/modifierProduit")
	public String editerProduit(@RequestParam("id") Long id, ModelMap modelMap) {
		Produit p = produitService.getProduit(id);
		modelMap.addAttribute("produit", p);
		return "editerProduit";
	}

	@RequestMapping("/updateProduit")
	public String updateProduit(@ModelAttribute("produit") Produit produit, @RequestParam("date") String date,
			ModelMap modelMap) throws ParseException {
		// conversion de la date
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateCreation = dateformat.parse(String.valueOf(date));
		produit.setDateCreation(dateCreation);
		produitService.updateProduit(produit.getIdProduit(),produit);
		List<Produit> prods = produitService.getAllProduits();
		modelMap.addAttribute("produits", prods);
		modelMap.addAttribute("msg", "Produit modifiée");
		modelMap.addAttribute("type", "warning");

		return "listeProduits";
	}
}
