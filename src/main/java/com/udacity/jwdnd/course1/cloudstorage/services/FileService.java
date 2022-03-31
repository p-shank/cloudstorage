package com.udacity.jwdnd.course1.cloudstorage.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.FileResponse;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;

@Service
public class FileService {

	@Autowired
	private FileMapper fileMapper;
	
    public List<FileResponse> getFileList(Integer userId) {
    	
        List<Files> files = fileMapper.getByUserId(userId);
        List<FileResponse> fileList = new ArrayList<FileResponse>();
        
        for (Files file: files) {
        	FileResponse fileRes = new FileResponse();
        	fileRes.setFileName(file.getFileName());
        	fileRes.setFileId(file.getFileId());
        	
            String base64 = Base64.getEncoder().encodeToString(file.getFileData());
            String fileURL = "data:" + file.getContentType() + ";base64," + base64;
            
            fileRes.setFileUrl(fileURL);
            
            fileList.add(fileRes);
        }
        
        return fileList;
    }	
	
    public void deleteFile(int fileid) {
        fileMapper.deleteFile(fileid);
    }
    
    public void uploadFile(MultipartFile fileUpload, int userId) throws Exception {
        Files file = new Files();
        try {
        	
        	String fileNamePresent = fileMapper.getFileByName(fileUpload.getOriginalFilename(),userId);
        	
        	if (StringUtils.isNotBlank(fileNamePresent)) {
        		throw new Exception();
        	}
        	
            file.setContentType(fileUpload.getContentType());
            file.setFileData(fileUpload.getBytes());
            file.setFileName(fileUpload.getOriginalFilename());
            file.setFileSize(Long.toString(fileUpload.getSize()));
        } catch (Exception e) {
            throw e;
        }
        fileMapper.insertFile(file, userId);
    }
    
}
