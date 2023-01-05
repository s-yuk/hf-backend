package com.example.api.service;

import com.example.api.model.dto.HandleErrorDto;
import com.example.api.model.form.BuyMyStockForm;
import com.example.api.model.form.UpdateMyChildPointForm;

public interface PropertyService {
  HandleErrorDto buyMyStock(String id, BuyMyStockForm form);
  HandleErrorDto updateMyChildPoint(String id, UpdateMyChildPointForm form);
}
