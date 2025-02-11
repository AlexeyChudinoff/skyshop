package org.skypro.skyshop.service;

import java.util.List;

public final class UserBasket {

  private final List<BasketItem> basketItems;
  private final double totalPrice;

  private double calculateTotalPrice() {
    if (basketItems == null || basketItems.isEmpty()) {
      return 0.0;
    }
    return basketItems.stream()
        .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
        .sum();
  }

  public UserBasket(List<BasketItem> basketItems) {
    this.basketItems = basketItems;
    this.totalPrice = calculateTotalPrice();
  }

  public List<BasketItem> getBasketItems() {
    return basketItems;
  }

  public double getTotalPrice() {
    return totalPrice;
  }


}//
