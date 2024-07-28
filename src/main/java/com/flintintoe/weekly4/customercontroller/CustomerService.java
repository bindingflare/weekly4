package com.flintintoe.weekly4.customercontroller;

import com.flintintoe.weekly4.customer.Customer;
import com.flintintoe.weekly4.customer.CustomerDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = customerDto.toEntity();
        return CustomerDto.of(customerRepository.save(customer));
    }

    @Transactional(readOnly = true)
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(CustomerDto::of)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<CustomerDto> getCustomer(Integer id) {
        return customerRepository.findById(id)
                .map(CustomerDto::of);
    }

    @Transactional
    public Optional<CustomerDto> updateCustomer(Integer id, CustomerDto customerDto) {
        return customerRepository.findById(id)
                .map(existingCustomer -> {
                    existingCustomer.update(
                            customerDto.getCustomerName(),
                            customerDto.getPhoneNumber(),
                            customerDto.getAddress()
                    );
                    return CustomerDto.of(customerRepository.save(existingCustomer));
                });
    }

    @Transactional
    public boolean deleteCustomer(Integer id) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customerRepository.delete(customer);
                    return true;
                })
                .orElse(false);
    }
}
