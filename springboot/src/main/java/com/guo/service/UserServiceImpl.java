//package com.guo.service;
//
//import cn.hutool.core.util.StrUtil;
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.baomidou.mybatisplus.core.toolkit.Wrappers;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.guo.common.Result;
//import com.guo.entity.User;
//import com.guo.mapper.UserMapper;
//import com.guo.service.UserService;
//import lombok.Data;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import javax.annotation.Resource;
//
//@Service
//public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
//    @Autowired
//    UserMapper userMapper;
//
//    public User loginIdentify(User user) {
//        return userMapper.selectOne(Wrappers.<User>lambdaQuery().
//                                    eq(User::getUsername,user.getUsername()).
//                                    eq(User::getPassword,user.getPassword()));
//    }
//
//    public User registerIdentify(User user) {
//        return userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,user.getUsername()));
//    }
//
//    public int insert(User user) {
//        return userMapper.insert(user) ;
//    }
//
//    public int updateByUserId(User user) {
//       return userMapper.updateById(user);
//    }
//
//    public int deleteById(Long id) {
//       return userMapper.deleteById(id);
//    }
//
//    public Page<User> selectPage(Integer pageNum,Integer pageSize,String search) {
//        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
//        if(StrUtil.isNotBlank(search)){
//            wrapper.like(User::getNickname,search);
//        }
//        return userMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
//    }
//
//}
