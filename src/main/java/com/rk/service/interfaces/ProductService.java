package com.rk.service.interfaces;

import com.rk.entity.Product;


/**
 * Created by Qin_Yikai on 2018-09-20.
 */
public interface ProductService extends BaseService<Product, Long> {
    Integer[] getByTypeIds(Integer[] keys);
}
