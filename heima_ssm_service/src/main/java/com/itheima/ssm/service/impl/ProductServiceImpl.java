package com.itheima.ssm.service.impl;
import com.itheima.ssm.dao.IProductDao;
import com.itheima.ssm.domain.Product;
import com.itheima.ssm.service.IProdictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service//标识服务层组件
@Transactional//开启事务控制
public class ProductServiceImpl implements IProdictService {

    @Override
    public void save(Product product) throws Exception {
        productDao.save(product);
    }

    @Autowired//Autowired作用：  自动按照类型注入
    private IProductDao productDao;

    @Override
    public List<Product> findAll() throws Exception {
        return productDao.findAll();
    }
}
