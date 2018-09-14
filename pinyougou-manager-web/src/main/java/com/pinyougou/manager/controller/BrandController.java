package com.pinyougou.manager.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;

import com.pinyougou.vo.PageResult;
import com.pinyougou.vo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/brand")
public class BrandController {
    @Reference
    private BrandService brandService;

    @RequestMapping("/findAll")
    @ResponseBody
    public List<TbBrand> findAll(){
        List<TbBrand> list = brandService.findAll();

        return  list;
    }
    @RequestMapping("/findPage")
    @ResponseBody
    public PageResult findPage(int page,int rows){
      PageResult pageResult= brandService.findPage(page,rows);
        return  pageResult;
    }
    @RequestMapping("/search")
    @ResponseBody
    public PageResult search(@RequestBody TbBrand tbBrand,int page,int rows){
        PageResult pageResult= brandService.search(tbBrand,page,rows);
        return  pageResult;
    }
    @RequestMapping("/add")
    @ResponseBody
    public Result add(@RequestBody TbBrand tbBrand){
        try {
            brandService.add(tbBrand);
            return new Result(true, "添加成功");
        }catch (Exception e){
            return new Result(false, "添加失败");
        }
    }
    @RequestMapping("/update")
    @ResponseBody
    public Result update(@RequestBody TbBrand tbBrand){
        try {
            brandService.update(tbBrand);
            return new Result(true, "修改成功");
        }catch (Exception e){
            return new Result(false, "修改失败");
        }
    }
    @RequestMapping("/dele")
    @ResponseBody
    public Result dele(Long[] ids){
        try {
            brandService.dele(ids);
            return new Result(true, "删除成功");
        }catch (Exception e){
            return new Result(false, "删除失败");
        }
    }
    @RequestMapping("/findOne")
    @ResponseBody
    public TbBrand findOne(long id){

            TbBrand tbBrand=brandService.findOne(id);
          return  tbBrand;
    }
}
