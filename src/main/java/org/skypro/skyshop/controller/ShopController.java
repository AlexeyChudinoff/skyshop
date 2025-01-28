package org.skypro.skyshop.controller;

import java.util.Collection;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.servise.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopController {

  @GetMapping("/products")
  public Collection<Product> getAllProducts() {
    StorageService storageService = new StorageService();
    return storageService.getAllProducts;
  }

  @GetMapping("/articles")
  public Collection<Article> getAllArticles() {
    StorageService storageService = new StorageService();
    return storageService.getAllArticles;
  }


}
