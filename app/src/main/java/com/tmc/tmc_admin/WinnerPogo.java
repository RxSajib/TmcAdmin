package com.tmc.tmc_admin;

public class WinnerPogo {

    private String Bank_ID,Bkash_ID, Easypisa_ID, GooglePay_ID, Mobile_Recharge, Paypal_ID, Paytm_ID,Roket_ID;
    private String coinget, username, location, coin, uri, email_address;

    public WinnerPogo(){

    }

    public WinnerPogo(String bank_ID, String bkash_ID, String easypisa_ID, String googlePay_ID, String mobile_Recharge, String paypal_ID, String paytm_ID, String roket_ID, String coinget, String username, String location, String coin, String uri, String email_address) {
        Bank_ID = bank_ID;
        Bkash_ID = bkash_ID;
        Easypisa_ID = easypisa_ID;
        GooglePay_ID = googlePay_ID;
        Mobile_Recharge = mobile_Recharge;
        Paypal_ID = paypal_ID;
        Paytm_ID = paytm_ID;
        Roket_ID = roket_ID;
        this.coinget = coinget;
        this.username = username;
        this.location = location;
        this.coin = coin;
        this.uri = uri;
        this.email_address = email_address;
    }

    public String getBank_ID() {
        return Bank_ID;
    }

    public void setBank_ID(String bank_ID) {
        Bank_ID = bank_ID;
    }

    public String getBkash_ID() {
        return Bkash_ID;
    }

    public void setBkash_ID(String bkash_ID) {
        Bkash_ID = bkash_ID;
    }

    public String getEasypisa_ID() {
        return Easypisa_ID;
    }

    public void setEasypisa_ID(String easypisa_ID) {
        Easypisa_ID = easypisa_ID;
    }

    public String getGooglePay_ID() {
        return GooglePay_ID;
    }

    public void setGooglePay_ID(String googlePay_ID) {
        GooglePay_ID = googlePay_ID;
    }

    public String getMobile_Recharge() {
        return Mobile_Recharge;
    }

    public void setMobile_Recharge(String mobile_Recharge) {
        Mobile_Recharge = mobile_Recharge;
    }

    public String getPaypal_ID() {
        return Paypal_ID;
    }

    public void setPaypal_ID(String paypal_ID) {
        Paypal_ID = paypal_ID;
    }

    public String getPaytm_ID() {
        return Paytm_ID;
    }

    public void setPaytm_ID(String paytm_ID) {
        Paytm_ID = paytm_ID;
    }

    public String getRoket_ID() {
        return Roket_ID;
    }

    public void setRoket_ID(String roket_ID) {
        Roket_ID = roket_ID;
    }

    public String getCoinget() {
        return coinget;
    }

    public void setCoinget(String coinget) {
        this.coinget = coinget;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }
}
