package com.example.codechallenge.repository;

import com.example.codechallenge.repository.entities.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface OrderRepository extends CrudRepository<Order, String> {

}
