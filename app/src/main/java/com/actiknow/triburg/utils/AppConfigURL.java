package com.actiknow.triburg.utils;

public class AppConfigURL {

    public static String BASE_URL = "https://blood-connect-cammy92.c9users.io/api/v1/user";

    public static String URL_GETOTP = BASE_URL + "/otp";
    public static String URL_RESENDOTP = BASE_URL + "/otp/resend";
    public static String URL_VERIFYOTP = BASE_URL + "/otp/verify";
    public static String URL_REGISTER = BASE_URL + "/register";

    public static String URL_GETALLBLOODBANKS = BASE_URL + "/bloodbank";
    public static String URL_GETBLOODBANKDETAIL = BASE_URL + "/bloodbank_detail";

    public static String URL_SUBMITREQUESTFORBLOOD = BASE_URL + "/request/blood";

    public static String URL_GETALLBADGES = BASE_URL + "/badge";

    public static String URL_SUBMITUSERLOCATION = BASE_URL + "/insert_location";

    public static String URL_GETALLACTIVEREQUESTS = BASE_URL + "/active_request";
    public static String URL_GETACTIVEREQUESTDETAIL = BASE_URL + "/active_request";
    public static String URL_ACCEPTREQUEST = BASE_URL + "/accept_request";

    public static String URL_REQUESTBLOOD = BASE_URL + "/request_blood";

    public static String URL_GETALLHOSPITALS = BASE_URL + "/hospitals";

    public static String URL_GETNEARBYDONORS = BASE_URL + "/nearby_donors";

    public static String URL_GETMYREQUESTS = BASE_URL + "/my_request";

    public static String URL_GETMYREQUESTDETAIL = BASE_URL + "/my_request";
    public static String URL_ENDMYREQUEST = BASE_URL + "/my_request";

    public static String URL_GETMYDONATIONS = BASE_URL + "/my_donation";
    public static String URL_GETMYDONATIONDETAIL = BASE_URL + "/my_donation";

    public static String URL_GETBANNERS = BASE_URL + "/banners";

}
