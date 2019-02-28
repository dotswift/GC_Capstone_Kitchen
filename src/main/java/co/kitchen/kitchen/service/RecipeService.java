package co.kitchen.kitchen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import co.kitchen.kitchen.model.Hit;
import co.kitchen.kitchen.model.Recipe;
import co.kitchen.kitchen.model.RecipeResponse;

@Component
public class RecipeService {
	@Value("${recipe.key}")
	private String key;
	private String appId = "8396f82d";

	private RestTemplate restTemplate = new RestTemplate();
	
	public List<Hit> findRecipes(String search) {

		String url = UriComponentsBuilder.fromHttpUrl("https://api.edamam.com/search")
				.queryParam("q", search)
				.queryParam("app_id", appId)
				.queryParam("app_key", key)
				.toUriString();
		
		RecipeResponse response = restTemplate.getForObject(url, RecipeResponse.class);
		
		return response.getHits();

	}

}