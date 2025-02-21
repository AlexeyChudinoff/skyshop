package org.skypro.skyshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.modelBasket.ProductBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;

import java.util.*;
import org.skypro.skyshop.service.BasketItem;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;
import org.skypro.skyshop.service.UserBasket;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BasketServiceTest {

  @Mock
  private ProductBasket productBasket;

  @Mock
  private StorageService storageService;

  @InjectMocks
  private BasketService basketService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    basketService = new BasketService(productBasket, storageService);
  }

  @Test
  void addProductById_WhenProductDoesNotExist_ThrowsException() {
    UUID nonExistentId = UUID.randomUUID();
    when(storageService.getProductById(nonExistentId)).thenReturn(Optional.empty());

    assertThrows(NoSuchProductException.class, () -> basketService.addProductById(nonExistentId));
    verify(productBasket, never()).addProduct(any());
  }

  @Test
  void addProductById_WhenProductExists_AddsToBasket() {
    UUID productId = UUID.randomUUID();
    Product product = new SimpleProduct(productId, "Test Product", 100);
    when(storageService.getProductById(productId)).thenReturn(Optional.of(product));

    basketService.addProductById(productId);

    verify(productBasket).addProduct(productId);
  }

  @Test
  void getUserBasket_WhenBasketIsEmpty_ReturnsEmptyList() {
    when(productBasket.getAllProducts()).thenReturn(Collections.emptyMap());

    UserBasket result = basketService.getUserBasket();

    assertTrue(result.getBasketItems().isEmpty());
  }

  @Test
  void getUserBasket_WhenBasketHasItems_ReturnsCorrectItems() {
    UUID productId = UUID.randomUUID();
    Product product = new SimpleProduct(productId, "Test Product", 100);
    int quantity = 2;

    when(storageService.getProductById(productId)).thenReturn(Optional.of(product));
    when(productBasket.getAllProducts()).thenReturn(Map.of(productId, quantity));

    UserBasket result = basketService.getUserBasket();
    List<BasketItem> items = result.getBasketItems();

    assertEquals(1, items.size());
    assertEquals(product, items.get(0).getProduct());
    assertEquals(quantity, items.get(0).getQuantity());
  }

  // Дополнительный тест: Несуществующий товар в корзине вызывает исключение
  @Test
  void getUserBasket_WhenProductInBasketDoesNotExist_ThrowsException() {
    UUID invalidProductId = UUID.randomUUID();
    when(productBasket.getAllProducts()).thenReturn(Map.of(invalidProductId, 1));
    when(storageService.getProductById(invalidProductId)).thenReturn(Optional.empty());

    assertThrows(NoSuchProductException.class, () -> basketService.getUserBasket());
  }
}