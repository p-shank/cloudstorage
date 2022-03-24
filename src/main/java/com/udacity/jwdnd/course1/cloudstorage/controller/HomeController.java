package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
public class HomeController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userSerivice;
    
    @GetMapping(value = {"/", "/home"})
    public ModelAndView getHomePage(Authentication authentication) throws  Exception {
        
    	String userName = authentication.getName();
    	User user = userSerivice.getUser(userName);
        
    	ModelAndView modelAndView = new ModelAndView();
    	
        if (user != null ) {
            modelAndView.addObject("notes", noteService.getNotesList(user.getUserId()));
            modelAndView.addObject("credentials", credentialService.getCredList(user.getUserId()));
            modelAndView.addObject("files", fileService.getFileList(user.getUserId()));
            modelAndView.setViewName("home");
            return modelAndView;
        }
        
        modelAndView.setViewName("login");
        return modelAndView;
    }

}
