package com.cg.model.dto;


import com.cg.model.BaseEntity;
import com.cg.model.CustomerInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Accessors(chain = true)
public class CustomerInfoDTO  {
    private Long id;
    
    private String fullName;

    private String phone;

    private Date birthDate;

    private String address;

    public CustomerInfo toCustomer(){
        return new CustomerInfo()
                .setId(id)
                .setFullName(fullName)
                .setPhone(phone)
                .setBirthDate(birthDate)
                .setAddress(address)
                ;
    }
}
