package org.skypro.skyshop.model.model.basket;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class ProductBasket {

  private final Map<UUID, Integer> productBasket = new HashMap<>();

  public ProductBasket() {
    // Конструктор по умолчанию, если нужно инициализировать что-то
  }

  public void addProduct(UUID uuid, Integer quantity) {
    if (uuid == null || quantity == null || quantity <= 0) {
      throw new IllegalArgumentException("UUID и количество должны быть ненулевыми" + " и положительными.");
    }
    productBasket.put(uuid, productBasket.getOrDefault(uuid, 0) + quantity);
  }

  public Map<UUID, Integer> getAllProducts() {
    Map<UUID, Integer> unmodifiableMap = new HashMap<>(productBasket);
    unmodifiableMap = Collections.unmodifiableMap(unmodifiableMap);
    return unmodifiableMap;
  }


}//
