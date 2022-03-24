package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;

@Mapper
public interface CredentialMapper {
	
	@Select("select * from CREDENTIALS")
    List<Credentials> getAllCreds();

    @Select("select * from CREDENTIALS where credid = #{credId}")
    public Credentials getByCredId(int credId);

    @Select("select * from CREDENTIALS where userid = #{userId}")
    public List<Credentials> getByUserId(int userId);

    @Insert("insert into CREDENTIALS (username, password, url, key, userid) values (#{credential.username}, #{credential.password}, #{credential.url}, #{credential.key}, #{userId})")
    public int insertCred(Credentials credential, int userId);

    @Delete("delete from CREDENTIALS where credid = #{credId}")
    public int deleteCred(int credId);

    @Update("update CREDENTIALS set password = #{password}, url = #{url}, username = #{username}, key = #{key} where credid = #{credId}")
    public int updateCred(Credentials credential);

}
