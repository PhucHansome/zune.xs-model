package com.cg.service.customerInfo;

import com.cg.model.CustomerInfo;
import com.cg.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerInfoInfoServiceImpl implements ICustomerInfoService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<CustomerInfo> findAll() {
        return null;
    }

    @Override
    public Boolean existById(Long id) {
        return null;
    }

    @Override
    public Optional<CustomerInfo> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public CustomerInfo getById(Long id) {
        return null;
    }

    @Override
    public CustomerInfo save(CustomerInfo customerInfo) {
        return customerRepository.save(customerInfo);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public void softDelete(CustomerInfo customerInfo) {

    }
}
