package com.cg.model.dto;


import com.cg.model.BaseEntity;
import com.cg.model.CustomerInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Accessors(chain = true)
public class CustomerInfoDTO  {
    private Long id;

    @NotEmpty(message = "Họ và tên không được để trống")
    @Size(min = 3, message = "Họ và tên phải từ 4 ký tự")
    @Size(max = 254, message ="họ và tên tối đa 255 ký tự" )
    private String fullName;

    @NotEmpty(message = "Số điện thoại không được để trống")
    @Size(min = 10, message = "Số điện thoại phải từ 10 số")
    @Size(max = 15, message ="Số điẹn thoại tối đa 15 số" )
    private String phone;

//    @PastOrPresent(message = "Ngày đấy chưa đến sao bạn có mặt được!!")
    private Date birthDate;

    @NotEmpty(message = "Địa chỉ không được để trống")
    @Size(min = 4, message = "địa chỉ tối thiểu 4 ký tự")
    @Size(max = 254, message ="địa chỉ tối đa 254 ký tự" )
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
