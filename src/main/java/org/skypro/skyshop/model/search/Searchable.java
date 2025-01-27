package org.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable {

  String searchTerm();

  String searchTipContent();

  public UUID getId();

  // вместо toString
  default void getStringRepresentation() {
    System.out.println("Имя объекта: " + searchTerm() + " ;  " +
        " тип объекта: " + searchTipContent());
  }





}