package com.itheima.ssm.dao;
import com.itheima.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;
public interface ITravellerDao {
    //在order_traveller表中根据ordersId查询出travellerId，再在traveller表里根据travellerId查询出来
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId=#{ordersId})")
    public List<Traveller> findByOrdersId(String ordersId) throws  Exception;
}
