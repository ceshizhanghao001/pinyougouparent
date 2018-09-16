package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.sellergoods.service.BrandService;
import com.pinyougou.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {
   @Autowired
   private TbBrandMapper tbBrandMapper;
    public List<TbBrand> findAll() {
        TbBrandExample tbBrandExample = new TbBrandExample();
        return tbBrandMapper.selectByExample(tbBrandExample);
    }

    @Override
    public PageResult findPage(int page, int row) {
        PageHelper.startPage(page,row);
        Page<TbBrand>tbBrandPage= (Page<TbBrand>) tbBrandMapper.selectByExample(null);
        return new PageResult((int) tbBrandPage.getTotal(),tbBrandPage.getResult());
    }

    @Override
    public PageResult search(TbBrand tbBrand,int page,int row) {
        PageHelper.startPage(page,row);
        TbBrandExample tbBrandExample = new TbBrandExample();
        TbBrandExample.Criteria criteria = tbBrandExample.createCriteria();
           if(tbBrand.getName()!=null && !tbBrand.getName().equals("")){
               criteria.andNameLike("%"+tbBrand.getName()+"%");
           }
           if(tbBrand.getFirstChar()!=null && !tbBrand.getFirstChar().equals("")){
               criteria.andFirstCharEqualTo(tbBrand.getFirstChar());
           }
        Page<TbBrand>page1= (Page<TbBrand>) tbBrandMapper.selectByExample(tbBrandExample);
        return new PageResult((int) page1.getTotal(),page1.getResult());
    }

    @Override
    public void add(TbBrand tbBrand) {
        tbBrandMapper.insert(tbBrand);
    }

    @Override
    public void update(TbBrand tbBrand) {
        tbBrandMapper.updateByPrimaryKey(tbBrand);
    }

    @Override
    public void dele(Long[] ids) {
        for (long id:ids) {
            tbBrandMapper.deleteByPrimaryKey(id);
        }

    }

    @Override
    public TbBrand findOne(long id) {
        return tbBrandMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Map<Long, String>> findBrandList() {
        List list = new ArrayList();
        List<TbBrand> tbBrands = tbBrandMapper.selectByExample(null);
        for (TbBrand t:tbBrands) {
            Map map = new HashMap();
            map.put("id",t.getId());
            map.put("text",t.getName());
            list.add(map);
        }
        return list;
    }
}
