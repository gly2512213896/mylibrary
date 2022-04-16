package com.guo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guo.utils.Result;
import com.guo.entity.Borrow;
import com.guo.mapper.BorrowMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/borrow")
public class BorrowController {
    @Resource
    BorrowMapper borrowMapper;
    @PostMapping
    public Result<?> save(@RequestBody Borrow borrow){
        //borrowService.insert(borrow);
        borrowMapper.insert(borrow);
        return Result.success();
    }
    @PutMapping
    public Result<?> update(@RequestBody Borrow borrow){
        //borrowService.updateById(borrow);
        System.out.println("@PutMapping,update中的borrow:"+borrow);
        borrowMapper.updateById(borrow);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id){
        //borrowService.deleteById(id);
        borrowMapper.deleteById(id);
        return Result.success();
    }
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "5") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<Borrow> wrapper = Wrappers.<Borrow>lambdaQuery();
        if(StrUtil.isNotBlank(search)){
            wrapper.like(Borrow::getUserName,search).or().like(Borrow::getBookName,search);
//            wrapper.like(Borrow::getBookName,search);
        }
        Page<Borrow> borrowPage = borrowMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(borrowPage);
    }
}
