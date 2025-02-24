package org.skypro.skyshop.model.modelBasket;

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

  public ProductBasket() {
  }

  public void addProduct(UUID id) {
    productBasket.merge(id, 1, Integer::sum);
  }

  public Map<UUID, Integer> getAllProducts() {
    return Collections.unmodifiableMap(new HashMap<>(productBasket));
  }

}//
