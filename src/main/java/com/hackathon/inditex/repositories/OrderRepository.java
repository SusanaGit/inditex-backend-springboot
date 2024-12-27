package com.hackathon.inditex.repositories;

import com.hackathon.inditex.Entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {



}
