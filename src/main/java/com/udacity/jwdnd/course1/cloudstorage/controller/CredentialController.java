package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
public class CredentialController {

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private UserService userService;
    
    @PostMapping("/credentials")
    public String saveOrUpdateCredentials(Authentication authentication, Credentials credential) {
       
    	String userName = authentication.getName();
    	User user = userService.getUser(userName);
    	
        if (credential.getCredId() > 0) {
        	System.out.println("update Cred");
        	credentialService.updateCred(credential);
        }
        else {
        	System.out.println("add Cred");
        	System.out.println(credential.getCredId());
            credentialService.addCred(credential, user.getUserId());
        }
        return "success";
    }

    @GetMapping("/credentials/delete")
    public String deleteNote(@RequestParam("credId") int credId) {
    	System.out.println("In Delte");
    	System.out.println(credId);
        if (credId > 0) {
            credentialService.deleteCred(credId);
            return "success";
        }
        return "error";
    }
}
