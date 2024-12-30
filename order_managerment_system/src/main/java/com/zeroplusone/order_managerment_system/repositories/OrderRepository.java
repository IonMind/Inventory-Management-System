package com.zeroplusone.order_managerment_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zeroplusone.order_managerment_system.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    
}
