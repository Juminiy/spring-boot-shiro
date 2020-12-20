package com.hua.shirospringboot.mapper;


import com.hua.shirospringboot.pojo.userInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    public userInfo getUserByName(@Param("name") String name) ;
}
