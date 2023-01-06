// package com.example.api.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.api.model.dto.HandleErrorDto;
// import com.example.api.model.form.BuyMyStockForm;
// import com.example.api.service.PropertyService;

// import lombok.AllArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// @RestController
// @RequestMapping("/api/property")
// @AllArgsConstructor
// @Slf4j

// // ポイント更新
// // 株購入
// public class PropertyController {

//   @Autowired
//   PropertyService propertyService;

//   @PutMapping("/stock/{id}")
//   public HandleErrorDto buyMyStock(@PathVariable String id, @RequestBody BuyMyStockForm form) {
//     log.info("form:{}", form);
//     HandleErrorDto dto = propertyService.buyMyStock(id, form);
//     return dto;
//   }
// }
