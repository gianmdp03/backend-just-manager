package com.gianmdp03.gestor_just_backend.Service;

import com.gianmdp03.gestor_just_backend.DTO.Customer.CustomerDetailDTO;
import com.gianmdp03.gestor_just_backend.DTO.Customer.CustomerListDTO;
import com.gianmdp03.gestor_just_backend.DTO.Customer.CustomerRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    CustomerDetailDTO addCustomer(CustomerRequestDTO customerRequestDTO);
    Page<CustomerListDTO> listCustomers(Pageable pageable);
    void deleteCustomer(Long id);
}
