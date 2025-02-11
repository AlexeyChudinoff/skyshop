package org.skypro.skyshop.controller;

import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.ShopError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ShopControllerAdvice {

  @ExceptionHandler(NoSuchProductException.class)
public ResponseEntity<ShopError> handleNoSuchProduct(NoSuchProductException ex) {
    // Создаем объект ошибки с сообщением "Продукт не найден" и деталями исключения
    ShopError error = new ShopError("Продукт не найден", ex.getMessage());
    // Возвращаем ответ с ошибкой и статусом NOT_FOUND
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
}


}
//