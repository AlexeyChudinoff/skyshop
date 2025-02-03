package org.skypro.skyshop.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;


@Service
public class StorageService {

  private final Map<UUID, Product> storageProduct;
  private final Map<UUID, Article> storageArticle;

  public StorageService() {
    this.storageArticle = new HashMap<>();
    this.storageProduct = new HashMap<>();
    greatingRepository();
  }

  public Optional<Product> getProductById(UUID id) {
    return Optional.ofNullable(storageProduct.get(id));
  }

  private void greatingRepository() {
    UUID article1Id = UUID.randomUUID();
    UUID article2Id = UUID.randomUUID();
    UUID article3Id = UUID.randomUUID();
    UUID article4Id = UUID.randomUUID();
    UUID product1Id = UUID.randomUUID();
    UUID product2Id = UUID.randomUUID();
    UUID product3Id = UUID.randomUUID();
    UUID product4Id = UUID.randomUUID();
    UUID product5Id = UUID.randomUUID();
    UUID product6Id = UUID.randomUUID();

    storageArticle.put(article1Id,
        new Article(article1Id, "articl1", "Инструкция к article1"));
    storageArticle.put(article2Id,
        new Article(article2Id, "articl2", "Инструкция к article2"));
    storageArticle.put(article3Id,
        new Article(article3Id, "articl3", "Инструкция к article3"));
    storageArticle.put(article4Id,
        new Article(article4Id, "articl4", "Инструкция к article4"));
    storageProduct.put(product1Id,
        new FixPriceProduct(product1Id, "Шило"));
    storageProduct.put(product2Id,
        new FixPriceProduct(product2Id, "Водичка"));
    storageProduct.put(product3Id,
        new DiscountedProduct(product3Id, "Еда", 800, 50));
    storageProduct.put(product4Id,
        new DiscountedProduct(product4Id, "Мыло", 600, 50));
    storageProduct.put(product5Id,
        new SimpleProduct(product5Id, "Сало", 300));
    storageProduct.put(product6Id,
        new SimpleProduct(product6Id, "Сало Сало Сало", 300));
  }

  public Collection<Article> getAllArticles() {
    return Collections.unmodifiableCollection(storageArticle.values());
  }

  public Collection<Product> getAllProducts() {
    return Collections.unmodifiableCollection(storageProduct.values());
  }

  public Collection<Searchable> getAllCollection() {
    List<Searchable> allCollection = new ArrayList<>();
    allCollection.addAll(storageArticle.values());
    allCollection.addAll(storageProduct.values());
    return allCollection;
  }

  public Map<UUID, Article> getStorageArticle() {
    return storageArticle;
  }

  public Map<UUID, Product> getStorageProduct() {
    return storageProduct;
  }


}