package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
public class NoteController {

    @Autowired
    private NoteService noteService;
    
    @Autowired
    private UserService userService;
    

    @PostMapping("/notes")
    public String updateNote(Authentication authentication, Notes note) {
        
    	String userName = authentication.getName();
    	User user = userService.getUser(userName);
    	
    	System.out.println(userName);
    	
        if (note.getNoteId() > 0) {
        	System.out.println("update note");
            noteService.updateNote(note);
        } else {
        	System.out.println("add note");
        	System.out.println(note.getNoteTitle());
            noteService.addNote(note, user.getUserId());
        }
        
        return "success";
    }

    @GetMapping("/notes/delete")
    public String deleteNote(@RequestParam("noteId") int noteId) {
    	noteService.deleteNote(noteId);
        return "success";
    }
}
