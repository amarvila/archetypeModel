package com.everis.d4i.project_x.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.everis.d4i.project_x.persistence.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<CustomerEntity, Long> {

}
