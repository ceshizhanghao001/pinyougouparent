package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbBrand;
import com.pinyougou.vo.PageResult;


import java.util.List;

public interface BrandService {
    List<TbBrand> findAll();

    PageResult findPage(int page, int row);

    PageResult search(TbBrand tbBrand,int page,int row);

    void add(TbBrand tbBrand);

    void update(TbBrand tbBrand);

    void dele(Long[] ids);

    TbBrand findOne(long id);
}
