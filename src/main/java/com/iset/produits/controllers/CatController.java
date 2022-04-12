package com.iset.produits.controllers;

import java.text.ParseException;
// import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iset.produits.entities.Produit;
import com.iset.produits.service.ProduitService;
import org.springframework.data.domain.Page;

@Controller
public class CatController {
	@Autowired
	ProduitService produitService;

	@RequestMapping("/showCreate")
	public String showCreate(ModelMap modelMap) {
		modelMap.addAttribute("produit", new Produit());
		modelMap.addAttribute("edit", false);
		return "createProduit";
	}

	@RequestMapping("/saveProduit")
	public String saveProduit(
			@Valid Produit produit,
			BindingResult bindingResult,
			ModelMap modelMap,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size) throws ParseException {
		if (bindingResult.hasErrors()) {
			modelMap.addAttribute("edit", false);
			return "createProduit";
		} else {
			Produit saveProduit = produitService.saveProduit(produit);
			String msg = "produit enregistré avec Id " + saveProduit.getIdProduit();
			Page<Produit> prods = produitService.getAllProduitsParPage(page, size);
			modelMap.addAttribute("produits", prods);
			modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
			modelMap.addAttribute("msg", msg);
			modelMap.addAttribute("type", "success");
			return "listeProduits";
		}
	}

	@RequestMapping("/ListeProduits")
	public String listeProduits(ModelMap modelMap, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size) {
		Page<Produit> prods = produitService.getAllProduitsParPage(page, size);
		modelMap.addAttribute("produits", prods);
		modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		return "listeProduits";
	}

	@RequestMapping("/supprimerProduit")
	public String supprimerProduit(@RequestParam("id") Long id, ModelMap modelMap,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size) {
		Produit p = new Produit();
		Produit p1 = produitService.getProduit(id);
		p.setIdProduit(id);
		produitService.deleteProduit(p);
		Page<Produit> prods = produitService.getAllProduitsParPage(page, size);
		modelMap.addAttribute("produits", prods);
		modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
		if (p1 != null) {
			modelMap.addAttribute("msg", "Produit supprimée");
			modelMap.addAttribute("type", "danger");
		} else {
			modelMap.addAttribute("msg", "Id non existant");
			modelMap.addAttribute("type", "warning");
		}
		return "listeProduits";
	}

	@RequestMapping("/modifierProduit")
	public String editerProduit(@RequestParam("id") Long id, ModelMap modelMap) {
		Produit p = produitService.getProduit(id);
		modelMap.addAttribute("produit", p);
		modelMap.addAttribute("edit", true);
		return "createProduit";
	}

	@RequestMapping("/updateProduit")
	public String updateProduit(
			@Valid Produit produit,
			BindingResult bindingResult,
			ModelMap modelMap,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size) throws ParseException {
		if (bindingResult.hasErrors()) {
			modelMap.addAttribute("edit", true);
			return "createProduit";
		}
		produitService.updateProduit(produit.getIdProduit(), produit);
		Page<Produit> prods = produitService.getAllProduitsParPage(page, size);
		modelMap.addAttribute("produits", prods);
		modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
		modelMap.addAttribute("msg", "Produit modifiée");
		modelMap.addAttribute("type", "warning");
		return "listeProduits";
	}

	// function to search for produit by name
	@RequestMapping("/searchProduit")
	public String searchProduit(@RequestParam("name") String name, ModelMap modelMap,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size) {
		Page<Produit> prods = produitService.findByNomProduitContains(name, page, size);
		modelMap.addAttribute("produits", prods);
		modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("name", name);
		return "search";
	}
}
