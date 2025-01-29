package org.skypro.skyshop.servise;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

//@Service чтобы Spring мог зарегистрировать его как управляемый объект
// и предоставлять этот класс другим классам
@Service

public class StorageService {

  private final Map<UUID, Product> storageProduct;
  private final Map<UUID, Article> storageArticle;

  public StorageService() {
    this.storageArticle = new HashMap<>();
    this.storageProduct = new HashMap<>();
    greatingRepository();
  }

  private void greatingRepository() {
    storageArticle.put(UUID.randomUUID(),
        new Article(UUID.randomUUID(), "articl1", "Инструкция к article1"));
    storageArticle.put(UUID.randomUUID(),
        new Article(UUID.randomUUID(), "articl2", "Инструкция к article2"));
    storageArticle.put(UUID.randomUUID(),
        new Article(UUID.randomUUID(), "articl3", "Инструкция к article3"));
    storageArticle.put(UUID.randomUUID(),
        new Article(UUID.randomUUID(), "articl4", "Инструкция к article4"));
    storageProduct.put(UUID.randomUUID(),
        new FixPriceProduct(UUID.randomUUID(), "Шило"));
    storageProduct.put(UUID.randomUUID(),
        new FixPriceProduct(UUID.randomUUID(), "Водичка"));
    storageProduct.put(UUID.randomUUID(),
        new DiscountedProduct(UUID.randomUUID(), "Еда", 800, 50));
    storageProduct.put(UUID.randomUUID(),
        new DiscountedProduct(UUID.randomUUID(), "Мыло", 600, 50));
    storageProduct.put(UUID.randomUUID(),
        new SimpleProduct(UUID.randomUUID(), "Сало", 300));
    storageProduct.put(UUID.randomUUID(),
        new SimpleProduct(UUID.randomUUID(), "Сало Сало Сало", 300));
  }

  public Collection<Article> getAllArticles() {
    return storageArticle.values();
  }

  public Collection<Product> getAllProducts() {
    return storageProduct.values();
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
