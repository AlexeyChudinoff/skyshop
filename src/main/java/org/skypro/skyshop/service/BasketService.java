package org.skypro.skyshop.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.model.basket.ProductBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

@Service
public class BasketService {

  private final ProductBasket productBasket;
  private final StorageService storageService;

  public BasketService(ProductBasket productBasket, StorageService storageService) {
    this.productBasket = productBasket;
    this.storageService = storageService;
  }

  public void addProductById(UUID id) {
    if (id == null) {
      throw new IllegalArgumentException("ID продукта не может быть null");
    }
    Optional<Product> optionalProduct = storageService.getProductById(id);
    if (!optionalProduct.isPresent()) {
      throw new NoSuchProductException("Продукт с ID " + id + " не найден");
    }
    productBasket.addProduct(id);

  }

  public UserBasket getUserBasket() {
    Map<UUID, Integer> productInBasket = productBasket.getAllProducts();
    List<BasketItem> basketItems = productInBasket.entrySet().stream()
        .map(entry -> {
          Product product = storageService.getProductById(entry.getKey())
              .orElseThrow(() -> new NoSuchProductException("Продукт не найден"));
          return new BasketItem(product, entry.getValue());
        })
        .collect(Collectors.toList());
    return new UserBasket(basketItems);
  }

}