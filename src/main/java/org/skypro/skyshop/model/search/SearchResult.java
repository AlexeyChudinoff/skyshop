package org.skypro.skyshop.model.search;

import java.util.UUID;
import org.springframework.context.annotation.EnableMBeanExport;

final class SearchResult /*implements Searchable*/ {

  final UUID id;
  final String name;
  final String contentType;

  public  SearchResult(String contentType, UUID id, String name) {
    this.contentType = contentType;
    this.id = id;
    this.name = name;
  }

  public String getContentType() {
    return contentType;
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public static SearchResult fromSearchable(Searchable searchable) {
    return new SearchResult (searchable.searchTipContent(), searchable.getId(),
        searchable.searchTerm());
  }


}
