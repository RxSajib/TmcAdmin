package com.tmc.tmc_admin;

public class user_info {

    private String email_address, name, uri;

    public user_info(){

    }

    public user_info(String email_address, String name, String uri) {
        this.email_address = email_address;
        this.name = name;
        this.uri = uri;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
