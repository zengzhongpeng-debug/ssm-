package com.itheima.ssm.service;
import com.itheima.ssm.domain.Product;

import java.util.List;
public interface IProdictService {

    void save(Product product)throws Exception;

    public List<Product> findAll() throws Exception;


}
