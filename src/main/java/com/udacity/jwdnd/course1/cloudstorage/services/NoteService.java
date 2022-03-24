package com.udacity.jwdnd.course1.cloudstorage.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;

@Service
public class NoteService {

    @Autowired
    private NoteMapper notesMapper;

    public List<Notes> getNotesList(int userId) throws Exception {
        List<Notes> notes = notesMapper.getByUserId(userId);
        for(Notes note: notes) {
        	System.out.println(note.getNoteTitle());
        }
        return notes;
    }

    public void deleteNote(int noteId) {
        notesMapper.deleteNote(noteId);
    }
    
    public void addNote(Notes note, int userId) {
        notesMapper.insertNote(note, userId);
    }

    public void updateNote(Notes note) {
        notesMapper.updateNote(note);
    }

}
