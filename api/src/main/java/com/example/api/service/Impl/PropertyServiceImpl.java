// package com.example.api.service.Impl;

// import javax.persistence.EntityNotFoundException;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.example.api.model.dto.HandleErrorDto;
// import com.example.api.model.entity.User;
// import com.example.api.model.form.BuyMyStockForm;
// import com.example.api.repo.UserRepo;
// import com.example.api.service.PropertyService;

// import lombok.AllArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// @Service
// @AllArgsConstructor
// @Slf4j
// public class PropertyServiceImpl implements PropertyService {

//   @Autowired
//   UserRepo userRepo;

//   @Override
//   public HandleErrorDto updateMyChildPoint(String id, UpdateMyChildPointForm form) {
//     User user = userRepo.findById(id).orElseThrow(() -> new EntityNotFoundException());
//     user.setHavePoint(form.getHavePoint());
//     userRepo.save(user);
//     HandleErrorDto handleErrorDto = new HandleErrorDto();

//     handleErrorDto.setId(id);
//     handleErrorDto.setMessage("ポイント更新");
//     return handleErrorDto;
//   }

//   @Override
//   public HandleErrorDto buyMyStock(String id, BuyMyStockForm form) {
//     User user = userRepo.findById(id).orElseThrow(() -> new EntityNotFoundException());
//     user.setHavePoint(form.getHavePoint());
//     user.setHaveStock(form.getHaveStock());
//     userRepo.save(user);
//     HandleErrorDto handleErrorDto = new HandleErrorDto();

//     handleErrorDto.setId(id);
//     handleErrorDto.setMessage("株購入");
//     return handleErrorDto;
//   }
// }
