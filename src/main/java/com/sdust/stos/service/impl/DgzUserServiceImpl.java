package com.sdust.stos.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdust.stos.common.R;
import com.sdust.stos.entity.DgzUser;
import com.sdust.stos.mapper.DgzUserMapper;
import com.sdust.stos.service.DgzUserService;
import org.springframework.stereotype.Service;

@Service
public class DgzUserServiceImpl extends ServiceImpl<DgzUserMapper, DgzUser> implements DgzUserService {

    /**
     * 用户登录
     * @param username
     * @param pwd
     * @param type
     * @return
     */
    @Override
    public R<String> login(String username,String pwd,String type) {


        //教师和学生登录
        if("A".equals(type) || "B".equals(type)){

            //1.根据用户账号查询该账号是否在数据库中
            LambdaQueryWrapper<DgzUser> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(DgzUser::getDgzUsername,username);
            DgzUser oneDgzUser = this.getOne(queryWrapper);

            //3.如不在，返回错误结果
            if(oneDgzUser ==null){
                return R.error("登录失败");
            }

            //4。如在，比较该密码是否匹配，如不匹配，返回错误结果
            if(!oneDgzUser.getDgzPassword().equals(pwd)){
                return R.error("登录失败");
            }

            return R.success("登录成功");
        }

        //发行人登录
        if("B".equals(type)){

        }
        //采购人登录
        if("C".equals(type)){

        }

        return R.error("出错了，请重试");

    }
}
