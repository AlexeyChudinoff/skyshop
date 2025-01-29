package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;
import java.util.UUID;
import org.skypro.skyshop.model.search.Searchable;
//import org.skypro.skyshop.searchProduct.Searchable;

public abstract class Product implements Searchable, Comparable {

  private final String nameProduct;
  private final UUID id;

  public Product(String nameProduct) throws IllegalArgumentException {
    if (nameProduct.isBlank()) {
      throw new IllegalArgumentException(
          ANSI_GREEN + "ВНИМАНИЕ ! Нет имени продукта !" + ANSI_RESET);
    }
    this.nameProduct = nameProduct;
    this.id = getId();
  }
  @JsonIgnore
  public abstract boolean isSpecial();
  @JsonIgnore// чтобы они не выводились в браузере
  public abstract int getCostProduct();
  @JsonIgnore
  public String getNameProduct() {
    return nameProduct;
  }

  @Override
  public String toString() {
    return "Product- имя продукта : " + (nameProduct != null ? nameProduct : "null")
        + " ;  = цена =  "
        + getCostProduct();
  }

  @Override
  public String searchTerm() {
    return nameProduct;
  }

  @Override
  public String searchTipContent() {
    return "PRODUCT";
  }

  @Override
  public UUID getId() { return id; }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Product product = (Product) o;
    return Objects.equals(nameProduct, product.nameProduct);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(nameProduct);
  }

  @Override
  public int compareTo(Object o) {
    if (o == null) {
      throw new IllegalArgumentException("Объект для сравнения не может быть null !");
    }
    if (o instanceof Product) {
      Product other = (Product) o;
      if (this.nameProduct == null || other.nameProduct == null) {
        throw new IllegalArgumentException("Имя продукта не может быть null !");
      }
      return this.nameProduct.compareTo(other.nameProduct);
    }
    throw new IllegalArgumentException("Объект не является Product");
  }

  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_BLUE = "\u001B[34m";

  //ANSI_GREEN + "ВНИМАНИЕ !" + ANSI_RESET +

}