package org.skypro.skyshop.controller;

import java.util.Collection;
import java.util.UUID;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;
import org.skypro.skyshop.service.UserBasket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopController {

  private final StorageService storageService;
  private final SearchService searchService;
  private final BasketService basketService;

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

  @GetMapping("/allCollection")
  public Collection<Searchable> getAllCollection() {
    return storageService.getAllCollection();
  }

  //@RequestParam т.е должен быть получен из URL-запроса (например: /search?pattern=Сало)
  @GetMapping("/search")
  public Collection<SearchResult> getSearchList(@RequestParam String pattern) {
    return searchService.search(pattern);
  }

  @GetMapping("/basket/{id}")
  public ResponseEntity<String> addProduct(@PathVariable("id") UUID id) {
    try {
      basketService.addProductById(id);
      return ResponseEntity.ok("Продукт успешно добавлен в корзину");
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/basket")
  public ResponseEntity<UserBasket> getUserBasket() {
    UserBasket userBasket = basketService.getUserBasket();
    if (userBasket.getBasketItems().isEmpty()) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
    }
    return ResponseEntity.ok(userBasket); // 200 OK
  }

//  @GetMapping("/basket")
//  public ResponseEntity<UserBasket> getUserBasket() {
//    UserBasket userBasket = basketService.getUserBasket();
//    if (userBasket.getBasketItems().isEmpty()) {
//      return ResponseEntity.badRequest().body("Корзина пуста");
//    }
//    return ResponseEntity.ok(userBasket);
//  }


}//
