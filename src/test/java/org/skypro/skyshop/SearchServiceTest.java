package org.skypro.skyshop;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.*;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;

import java.util.*;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class SearchServiceTest {

  private StorageService storageService;
  private SearchService searchService;

  @BeforeEach
  void setUp() {
    storageService = Mockito.mock(StorageService.class);
    searchService = new SearchService(storageService);
  }

  @Test
  void search_WhenStorageEmpty_ReturnsEmptyList() {
    when(storageService.getAllCollection()).thenReturn(Collections.emptyList());
    Collection<SearchResult> results = searchService.search("test");
    assertTrue(results.isEmpty());
  }

  @Test
  void search_WhenNoMatchingObjects_ReturnsEmptyList() {
    List<Searchable> mockData = List.of(
        new FixPriceProduct(UUID.randomUUID(), "Шило"),
        new Article(UUID.randomUUID(), "articl1", "Инструкция")
    );
    when(storageService.getAllCollection()).thenReturn(mockData);
    Collection<SearchResult> results = searchService.search("мыло");
    assertTrue(results.isEmpty());
  }

  @Test
  void search_WhenMatchingObjectsExist_ReturnsResults() {
    UUID productId1 = UUID.randomUUID();
    UUID productId2 = UUID.randomUUID();
    List<Searchable> mockData = List.of(
        new SimpleProduct(productId1, "Сало", 300),
        new SimpleProduct(productId2, "Сало Сало Сало", 300)
    );
    when(storageService.getAllCollection()).thenReturn(mockData);
    Collection<SearchResult> results = searchService.search("Сало");
    assertEquals(2, results.size());
    assertTrue(results.stream().anyMatch(r -> r.getId().equals(productId1)));
    assertTrue(results.stream().anyMatch(r -> r.getId().equals(productId2)));
  }

  @Test
  void search_IsCaseSensitive() {
    List<Searchable> mockData = List.of(
        new SimpleProduct(UUID.randomUUID(), "Сало", 300)
    );
    when(storageService.getAllCollection()).thenReturn(mockData);
    Collection<SearchResult> results = searchService.search("сало");
    assertTrue(results.isEmpty());
  }

  @Test
  void search_PartialMatch() {
    List<Searchable> mockData = List.of(
        new Article(UUID.randomUUID(), "articl1", "text"),
        new Article(UUID.randomUUID(), "article2", "text")
    );
    when(storageService.getAllCollection()).thenReturn(mockData);
    Collection<SearchResult> results = searchService.search("articl");
    assertEquals(2, results.size());
  }

  @Test
  void search_ReturnsBothArticlesAndProducts() {
    List<Searchable> mockData = List.of(
        new Article(UUID.randomUUID(), "searchTerm", "text"),
        new SimpleProduct(UUID.randomUUID(), "searchTerm", 100)
    );
    when(storageService.getAllCollection()).thenReturn(mockData);
    Collection<SearchResult> results = searchService.search("searchTerm");
    assertEquals(2, results.size());
    assertTrue(results.stream().anyMatch(r -> "ARTICLE".equals(r.getContentType())));
    assertTrue(results.stream().anyMatch(r -> "PRODUCT".equals(r.getContentType())));
  }

  @Test
  void search_WithEmptyQuery_ReturnsAll() {
    List<Searchable> mockData = List.of(
        new Article(UUID.randomUUID(), "article1", "text"),
        new SimpleProduct(UUID.randomUUID(), "product1", 100)
    );
    when(storageService.getAllCollection()).thenReturn(mockData);
    Collection<SearchResult> results = searchService.search("");
    assertEquals(2, results.size());
  }
}
