package org.skypro.skyshop.service;

import java.util.UUID;
import org.skypro.skyshop.model.model.basket.ProductBasket;

public class BasketService {

   private final StorageService storageService;
private  final ProductBasket productBasket;

   public BasketService(ProductBasket productBasket, StorageService storageService) {
      this.productBasket = productBasket;
      this.storageService = storageService;
   }

   public addingById(UUID id) {

   }



}//
