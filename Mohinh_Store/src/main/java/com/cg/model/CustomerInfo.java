package com.cg.model;


import com.cg.model.dto.CustomerInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "customer_infors")
@Accessors (chain = true)
public class CustomerInfo extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(unique = true,nullable = false)
    private  String fullName;

    @Column(unique = true, nullable = false)
    private  String phone;

    @Column( name = "bOd",unique = true, nullable = false)
    private Date birthDate;

    @Column( name = "address")
    private String address;

    public CustomerInfoDTO toCustomerDTO(){
        return new CustomerInfoDTO()
                .setId(id)
                .setFullName(fullName)
                .setPhone(phone)
                .setBirthDate(birthDate)
                .setAddress(address)
                ;
    }
}
