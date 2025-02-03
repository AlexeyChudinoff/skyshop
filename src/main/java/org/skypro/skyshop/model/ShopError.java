package org.skypro.skyshop.model;

public class ShopError {
  private final String message;
  private final String code;

  public ShopError(String code, String message) {
    this.code = code;
    this.message = message;
  }
  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }


}