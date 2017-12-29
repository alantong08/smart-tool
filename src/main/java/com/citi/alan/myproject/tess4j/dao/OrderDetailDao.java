package com.citi.alan.myproject.tess4j.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.citi.alan.myproject.tess4j.entity.OrderDetail;
import com.citi.alan.myproject.tess4j.entity.UserInfo;

@Repository
public interface OrderDetailDao extends CrudRepository<OrderDetail, Long>{
    
    public List<OrderDetail> findByUserInfo(UserInfo userInfo);

}
