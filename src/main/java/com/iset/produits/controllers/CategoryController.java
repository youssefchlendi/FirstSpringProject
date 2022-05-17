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

import com.iset.produits.entities.Categorie;
import com.iset.produits.service.CategorieService;
import org.springframework.data.domain.Page;

@Controller
public class CategoryController {
	@Autowired
	CategorieService CategorieService;

	@RequestMapping("/showCreateC")
	public String showCreate(ModelMap modelMap) {
		modelMap.addAttribute("categorie", new Categorie());
		modelMap.addAttribute("edit", false);
		return "createCategorie";
	}

	@RequestMapping("/saveCategorie")
	public String saveCategorie(
			@Valid Categorie Categorie,
			BindingResult bindingResult,
			ModelMap modelMap,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size) throws ParseException {
		if (bindingResult.hasErrors()) {
			modelMap.addAttribute("edit", false);
			return "createCategorie";
		} else {
			Categorie saveCategorie = CategorieService.saveCategorie(Categorie);
			String msg = "Categorie enregistré avec Id " + saveCategorie.getIdCat();
			Page<Categorie> prods = CategorieService.getAllCategoriesParPage(page, size);
			modelMap.addAttribute("categories", prods);
			modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
			modelMap.addAttribute("msg", msg);
			modelMap.addAttribute("type", "success");
			return "listeCategories";
		}
	}

	@RequestMapping("/ListeCategories")
	public String listeCategories(ModelMap modelMap, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size) {
		Page<Categorie> prods = CategorieService.getAllCategoriesParPage(page, size);
		modelMap.addAttribute("categories", prods);
		modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		return "listeCategories";
	}

	@RequestMapping("/supprimerCategorie")
	public String supprimerCategorie(@RequestParam("id") Long id, ModelMap modelMap,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size) {
		Categorie p = new Categorie();
		Categorie p1 = CategorieService.getCategorie(id);
		p.setIdCat(id);
		CategorieService.deleteCategorie(p);
		Page<Categorie> prods = CategorieService.getAllCategoriesParPage(page, size);
		modelMap.addAttribute("categories", prods);
		modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
		if (p1 != null) {
			modelMap.addAttribute("msg", "Categorie supprimée");
			modelMap.addAttribute("type", "danger");
		} else {
			modelMap.addAttribute("msg", "Id non existant");
			modelMap.addAttribute("type", "warning");
		}
		return "listeCategories";
	}

	@RequestMapping("/modifierCategorie")
	public String editerCategorie(@RequestParam("id") Long id, ModelMap modelMap) {
		Categorie p = CategorieService.getCategorie(id);
		modelMap.addAttribute("categorie", p);
		modelMap.addAttribute("edit", true);
		return "createCategorie";
	}

	@RequestMapping("/updateCategorie")
	public String updateCategorie(
			@Valid Categorie Categorie,
			BindingResult bindingResult,
			ModelMap modelMap,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size) throws ParseException {
		if (bindingResult.hasErrors()) {
			modelMap.addAttribute("edit", true);
			return "createCategorie";
		}
		CategorieService.saveCategorie(Categorie);
		Page<Categorie> prods = CategorieService.getAllCategoriesParPage(page, size);
		modelMap.addAttribute("categories", prods);
		modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
		modelMap.addAttribute("msg", "Categorie modifiée");
		modelMap.addAttribute("type", "warning");
		return "listeCategories";
	}

	// function to search for Categorie by name
	@RequestMapping("/searchCategorie")
	public String searchCategorie(@RequestParam("name") String name, ModelMap modelMap,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size) {
		Page<Categorie> prods = CategorieService.findByNomCatContains(name, page, size);
		System.out.println(prods);
		modelMap.addAttribute("categories", prods);
		modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("name", name);
		return "listeCategories";
	}
}
