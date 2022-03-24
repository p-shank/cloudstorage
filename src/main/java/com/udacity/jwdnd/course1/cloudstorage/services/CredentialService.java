package com.udacity.jwdnd.course1.cloudstorage.services;

import java.util.List;
import java.util.Random;
import java.util.random.RandomGeneratorFactory;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;

@Service
public class CredentialService {
    @Autowired
    private CredentialMapper credentialsMapper;

    @Autowired
    private EncryptionService encryptionService;

    private Credentials encryptPass(Credentials credential) {
        String key = RandomStringUtils.random(16, true, true);
        credential.setKey(key);
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), key));
        return credential;
    }

    public Credentials decryptPass(Credentials credential) {
        credential.setDecryptedPass(encryptionService.decryptValue(credential.getPassword(), credential.getKey()));
        return credential;
    }

    public List<Credentials> getCredList(int userId) throws Exception {
        List<Credentials> credentials = credentialsMapper.getByUserId(userId);
        for ( Credentials cred: credentials) {
        	decryptPass(cred);
        }
        return credentials;	
    }

    public void addCred(Credentials credential, int userId) {
        credentialsMapper.insertCred(encryptPass(credential), userId);
    }

    public void updateCred(Credentials credential) {
        credentialsMapper.updateCred(encryptPass(credential));
    }

    public void deleteCred(int credId) {
        credentialsMapper.deleteCred(credId);
    }

}
