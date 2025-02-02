package org.skypro.skyshop.controller;

import java.util.Collection;
import java.util.UUID;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;
import org.skypro.skyshop.service.UserBasket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopController {

  private final StorageService storageService;
  private final SearchService searchService;
  private final BasketService basketService;

  //Dependency Injection: Контроллер теперь получает StorageService
  // через конструктор с аннотацией @Autowired,
  // что позволяет Spring управлять зависимостями.
  @Autowired
  public ShopController(StorageService storageService, SearchService searchService,
      BasketService basketService) {
    this.storageService = storageService;
    this.searchService = searchService;
    this.basketService = basketService;
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

  // PathVariable — специальный аргумент, похожий на параметр запроса.
// Но в этом случае в качестве аргумента мы выделяем часть URL
  @PostMapping("/basket/{id}")
  public ResponseEntity<String> addProduct(@PathVariable("id") UUID id) {
    try {
      basketService.addProductById(id);
      return ResponseEntity.ok("Продукт успешно добавлен");
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }

//  @GetMapping("/basket/{id}")
//  public String addProduct(@PathVariable("id") UUID id) {
//    basketService.addProductById(id);
//    return "Продукт успешно добавлен";
//  }

  @GetMapping("/basket")
  public UserBasket getUserBasket() {
    return basketService.getUserBasket();
  }

}//
