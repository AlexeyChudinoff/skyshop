package org.skypro.skyshop.controller;

import java.util.Collection;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.servise.SearchService;
import org.skypro.skyshop.servise.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopController {

  private final StorageService storageService;
  private  final SearchService searchService;

  //Dependency Injection: Контроллер теперь получает StorageService
  // через конструктор с аннотацией @Autowired,
  // что позволяет Spring управлять зависимостями.
  @Autowired
  public ShopController(StorageService storageService, SearchService searchService) {
    this.storageService = storageService;
    this.searchService = searchService;
  }

  @GetMapping("/products")
  public Collection<Product> getAllProducts() {
    return storageService.getAllProducts();
  }

  @GetMapping("/articles")
  public Collection<Article> getAllArticles() {
    return storageService.getAllArticles();
  }
//@RequestParam т.е должен быть получен из URL-запроса (например: /search?pattern=Сало)
  @GetMapping("/search")
  public Collection<SearchResult> getSearchList(@RequestParam String pattern) {
    return searchService.search(pattern);
  }

//  @GetMapping("/search")
//  public Collection<SearchResult> getSearchList (String search){
//    return SearchService.search(search);
//  }





}//
