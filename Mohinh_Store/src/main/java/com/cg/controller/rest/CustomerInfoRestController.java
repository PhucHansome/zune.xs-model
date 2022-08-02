package com.cg.controller.rest;


import com.cg.model.CustomerInfo;
import com.cg.model.dto.CustomerInfoDTO;
import com.cg.service.customerInfo.ICustomerInfoService;
import com.cg.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/customerinfo")
public class CustomerInfoRestController {
    @Autowired
    private ICustomerInfoService customerInfoService;

    @Autowired
    private AppUtil appUtils;

    @PostMapping("/create")
    public ResponseEntity<?> doCreate(@Validated @RequestBody CustomerInfoDTO customerInfoDTO, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return appUtils.mapErrorToResponse(bindingResult);
            }
            customerInfoDTO.setId(0L);
            CustomerInfo customerInfo1 = customerInfoDTO.toCustomer();
            CustomerInfo customerInfo = customerInfoService.save(customerInfo1);
            return new ResponseEntity<>(customerInfo.toCustomerDTO(), HttpStatus.OK);
        } catch (Exception e) {
            customerInfoDTO.setId(0L);
            CustomerInfo customerInfo1 = customerInfoDTO.toCustomer();
            CustomerInfo customerInfo = customerInfoService.save(customerInfo1);
            return new ResponseEntity<>(customerInfo.toCustomerDTO(), HttpStatus.OK);
        }


    }
}
