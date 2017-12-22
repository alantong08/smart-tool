package com.citi.alan.myproject.tess4j.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.citi.alan.myproject.tess4j.entity.OrderDetail;

@Repository
public interface OrderDetailDao extends CrudRepository<OrderDetail, Long>{
    
    public OrderDetail findByUserName(String userName);
   

}
