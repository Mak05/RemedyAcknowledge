package com.mak.configuration;

public interface SecurityService {
	
    String findLoggedInUsername();

    void autoLogin(String userName, String password);
}