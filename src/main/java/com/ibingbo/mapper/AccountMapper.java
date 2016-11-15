package com.ibingbo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.ibingbo.models.Account;

public interface AccountMapper {

	@Select({
        "select",
        "id, name, login_time, create_time, buc_userid, empid ",
        "from user",
        "where id = #{id,jdbcType=INTEGER}"
    })
	@ResultMap("BaseResultMap")
	public Account getAccountById(Integer id);
	
	public List<Account> getAccounts();
}
