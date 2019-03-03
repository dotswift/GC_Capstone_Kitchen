package co.kitchen.kitchen;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.kitchen.kitchen.dao.FavoriteHitDao;
import co.kitchen.kitchen.model.Hit;
import co.kitchen.kitchen.service.RecipeService;

@Controller
public class RecipeController {

	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private FavoriteHitDao favoriteHitDao;

	@RequestMapping("/recipes")
	public ModelAndView searchRecipes(@RequestParam(name="search", defaultValue="brisket") String search,
			@ModelAttribute(name="uri") String uri) {
		List<Hit> hits = recipeService.findRecipes(search);
		for (Hit hit : hits) {
			if (hit.getRecipe().getUri().equalsIgnoreCase(uri)) {
				favoriteHitDao.create(hit);
			}
		}
		return new ModelAndView("recipes", "hits", hits);		
	}
	
//	@RequestMapping("/recipes/add")
//	public ModelAndView favoriteAdd(Hit hitRecipe) {
//		Hit hit = new Hit();
//		hit = hitRecipe;
//		System.out.println("aFavorite = " + hitRecipe);
//		
//		favoriteHitDao.create(hitRecipe);
//		
//		return new ModelAndView("redirect:/recipes");
//	}
	
}
