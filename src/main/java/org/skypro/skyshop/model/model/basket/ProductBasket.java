package org.skypro.skyshop.model.model.basket;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ProductBasket {

  private final Map<UUID, Integer> productBasket = new HashMap<>();

  public void addProduct(UUID productId, Integer quantity) {
    if (productId == null || quantity == null || quantity <= 0) {
      throw new IllegalArgumentException("UUID и количество должны быть ненулевыми" + " и положительными.");
    }
    productBasket.merge(productId, quantity, Integer::sum);
  }

  public Map<UUID, Integer> getAllProducts() {
    return Collections.unmodifiableMap(new HashMap<>(productBasket));
  }

}//
