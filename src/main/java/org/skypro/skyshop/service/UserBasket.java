package org.skypro.skyshop.service;

import java.util.List;

public class UserBasket {

  private final List<BasketItem> basketItems;
  private final double totalPrice;

  public UserBasket(List<BasketItem> basketItems) {
    this.basketItems = basketItems;
    this.totalPrice = calculateTotalPrice();
  }

  private double calculateTotalPrice() {
    return basketItems.stream()
        .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
        .sum();
  }

  public List<BasketItem> getBasketItems() {
    return basketItems;
  }

  public double getTotalPrice() {
    return totalPrice;
  }
}
