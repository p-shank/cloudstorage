package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.udacity.jwdnd.course1.cloudstorage.model.Notes;

@Mapper
public interface NoteMapper {

    @Select("select * from NOTES")
    List<Notes> getAllNotes();

    @Select("select * from NOTES where noteid = #{noteId}")
    public Notes getNoteById(int noteId);

    @Select("select * from NOTES where userid = #{userId}")
    public List<Notes> getByUserId(int userId);

    @Insert("insert into NOTES (notetitle, notedescription, userid) values (#{note.noteTitle}, #{note.noteDescription}, #{userId})")
    public int insertNote(Notes note, int userId);

    @Delete("delete from NOTES where noteid = #{noteId}")
    public int deleteNote(int noteId);

    @Update("update NOTES set notetitle = #{noteTitle}, notedescription = #{noteDescription} where noteid = #{noteId}")
    public int updateNote(Notes note);
	
}
