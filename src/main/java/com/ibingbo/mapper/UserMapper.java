package com.ibingbo.mapper;

import com.ibingbo.models.User;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface UserMapper {


    public User getUser(Integer id);
}
