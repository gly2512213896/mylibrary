package com.guo.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.guo.utils.Result;
import com.guo.entity.Admin;
import com.guo.mapper.AdminMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    AdminMapper adminMapper;
    @PostMapping("/login")
    public Result<?> login(@RequestBody Admin admin) {
        System.out.println(admin);
        //Admin res=adminService.loginIdentify(admin);
        System.out.println("进入login方法了");
        Admin res=adminMapper.selectOne(Wrappers.<Admin>lambdaQuery().
                eq(Admin::getAdminName,admin.getAdminName()).
                eq(Admin::getAdminPassword,admin.getAdminPassword()));
        if(res==null){
            System.out.println("error");
            return Result.error("-1","用户名或密码错误");
        }
        return Result.success(res);
    }
    @PostMapping("/register")
    public Result<?> register(@RequestBody Admin admin){
        //Admin res=adminService.registerIdentify(admin);
        System.out.println("进入了register控制器");
        Admin res=adminMapper.selectOne(Wrappers.<Admin>lambdaQuery().eq(Admin::getAdminName,admin.getAdminName()));
        if(res!=null){
            return Result.error("-1","该用户已存在，请更改用户名");
        }
        //adminService.insert(admin);
        adminMapper.insert(admin);
        return Result.success();
    }
    @PostMapping("/updatePass")
    public Result<?> updatePass(@RequestBody Admin oldAdmin){
        System.out.println("进入updatePass方法了");
        Admin newAdmin = adminMapper.selectById(oldAdmin.getAdminId());
        System.out.println(newAdmin.toString());
        System.out.println(oldAdmin.toString());
        newAdmin.setAdminPassword(oldAdmin.getAdminPassword());
        adminMapper.updateById(newAdmin);
        return Result.success(newAdmin);
    }
}
