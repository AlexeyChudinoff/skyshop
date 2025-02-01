package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {

  int costSimpleProduct;
  //private final UUID id;

  public SimpleProduct(UUID id, String nameProduct, int costSimpleProduct)
      throws IllegalArgumentException {
    super(nameProduct, id);
    //this.id = id;

    if (costSimpleProduct <= 0) {
      throw new IllegalArgumentException(
          ANSI_GREEN + "ВНИМАНИЕ ! Цена меньше нуля !" + ANSI_RESET);
    }
    this.costSimpleProduct = costSimpleProduct;
  }

  @Override
  public int getPrice() {
    return costSimpleProduct;
  }

  @Override
  public String toString() {
    return "(Simpl.) " + getNameProduct() +
        " , цена =  " + costSimpleProduct;
  }

//  @Override
//  public UUID getId() {
//    return id;
//  }

  public boolean isSpecial() {
    return false;
  }

  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_BLUE = "\u001B[34m";

  //ANSI_GREEN + "ВНИМАНИЕ !" + ANSI_RESET +


}