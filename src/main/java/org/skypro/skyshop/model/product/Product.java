package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;
import java.util.UUID;
import org.skypro.skyshop.model.search.Searchable;


public abstract class Product implements Searchable, Comparable {

  private final String nameProduct;
  private final UUID id;

  public Product(String nameProduct, UUID id) throws IllegalArgumentException {
    if (nameProduct.isBlank()) {
      throw new IllegalArgumentException("ВНИМАНИЕ ! Нет имени продукта !");
    }
    this.nameProduct = nameProduct;
    this.id = id;
  }
  @JsonIgnore
  public abstract boolean isSpecial();

  @JsonIgnore// чтобы они не выводились в браузере
  public abstract int getPrice();
  @JsonIgnore
  public String getNameProduct() {
    return nameProduct;
  }
  @Override
  public UUID getId() {
    return id; }

  @Override
  public String toString() {
    return "Product- имя продукта : " + (nameProduct != null ? nameProduct : "null")
        + " ;  = цена =  "
        + getPrice();
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

}