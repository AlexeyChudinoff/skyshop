package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {

  static final int FIX_PRICE_PRODUCT = 500;

  public FixPriceProduct(UUID id, String nameProduct) {
    super(nameProduct, id);
     }

  public int getCostProduct() {
    return FIX_PRICE_PRODUCT;
  }

  @Override
  public String toString() {
    return "(Fix.) "
        + getNameProduct() + " , цена = " + FIX_PRICE_PRODUCT;
  }

  public boolean isSpecial() {
    return true;
  }


}//