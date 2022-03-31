package com.udacity.jwdnd.course1.cloudstorage.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
@ControllerAdvice
public class FileController {

    @Autowired
    private FileService filesService;

    @Autowired
    private UserService userService;
    
    @PostMapping("/files")
    public String uploadFile(Authentication authentication,MultipartFile fileUpload) throws IOException {
    	
    	try {   	
    		String userName = authentication.getName();
    		User user = userService.getUser(userName);
 
    		if (fileUpload.isEmpty()) {
    			return "error";
    		}
    		filesService.uploadFile(fileUpload, user.getUserId());
    		return "success";
    	} catch (Exception exception ) {
    		System.out.print(exception);
        	return "error";
        }
    }

    @GetMapping("/files/delete")
    public String deleteNote(@RequestParam("fileId") int fileId) {
        if (fileId > 0) {
            filesService.deleteFile(fileId);
            return "success";
        }
        return "error";
    }
    
    
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handelFileUploadError() {
    	return "error";
    }
}
