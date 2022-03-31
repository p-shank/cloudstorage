package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;

@Mapper
public interface FileMapper {

    @Select("select * from FILES where userid = #{userId}")
    public List<Files> getByUserId(int userId);

    @Insert("insert into FILES (filename, filesize, contenttype, filedata, userId) values (#{file.fileName}, #{file.fileSize}, #{file.contentType}, #{file.fileData}, #{userId})")
    public int insertFile(Files file, int userId);

    @Delete("delete from FILES where fileid = #{fileId}")
    public int deleteFile(int fileId);
    
    @Select("select filename from FILES where filename = #{filename} and userid = #{userid}")
    public String getFileByName(String filename, int userid);

}
