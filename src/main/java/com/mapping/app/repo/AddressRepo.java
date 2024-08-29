package com.mapping.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapping.app.model.Address;

public interface AddressRepo extends JpaRepository<Address, Long>{

}
