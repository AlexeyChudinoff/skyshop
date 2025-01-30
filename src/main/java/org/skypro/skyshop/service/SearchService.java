package org.skypro.skyshop.service;


import java.util.Collection;
import java.util.stream.Collectors;
import  org.skypro.skyshop.model.search.SearchResult;
import org.springframework.stereotype.Service;


@Service
public class SearchService {

  private final StorageService storageService;

  public SearchService(StorageService storageService) {
    this.storageService = storageService;
  }

  public StorageService getStorageService() {
    return storageService;
  }

  public Collection<SearchResult> search (String searchName) {
    return storageService.getAllCollection()
        .stream()
        .filter(searchable -> searchable.searchTerm().contains(searchName))
        .map(SearchResult::fromSearchable)
        .collect(Collectors.toList());
  }



}
