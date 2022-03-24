package com.udacity.jwdnd.course1.cloudstorage.model;

public class Credentials {

    private Integer credId;
    private String url;
    private String username;
    private String key;
    private String password;
    private String decryptedPass;
    
	public Integer getCredId() {
		return credId;
	}
	public void setCredId(Integer credId) {
		this.credId = credId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDecryptedPass() {
		return decryptedPass;
	}
	public void setDecryptedPass(String decryptedPass) {
		this.decryptedPass = decryptedPass;
	}
}
