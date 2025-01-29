package org.skypro.skyshop.servise;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.naming.directory.SearchResult;
import org.springframework.context.annotation.Bean;
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

  public Collection <SearchResult> search (String searchName) {
    List<SearchResult> searchResultList = new ArrayList<>();
    searchResultList = storageService.getAllCollection()
        .stream()
        .filter(result -> result.searchTerm().contains(searchName))
        .collect(Collectors.toList());

    return searchResultList;
  }



}
