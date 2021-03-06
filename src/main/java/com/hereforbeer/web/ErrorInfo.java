package com.hereforbeer.web;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorInfo {

    //BAD REQUEST EXCEPTION
    USER_DUPLICATED(4001, "User already exists"),
    USER_NOT_FOUND(4002, "User does not exist"),
    WRONG_PASSWORD(4003, "Password does not match"),
    OFFER_NOT_FOUND(4004, "Offer does not exist"),

    BAD_LOCATION(4103, "Bad location"),
    RIDE_NOT_FOUND(4104, "Ride not found"),

    //Other exceptions
    CONFLICT(4999, "Conflict");

    private final int errCode;
    private final String errDescr;

    ErrorInfo(int errCode, String errDescr) {
        this.errCode = errCode;
        this.errDescr = errDescr;
    }

    public int getErrCode() {
        return errCode;
    }

    public String getErrDescr() {
        return errDescr;
    }
}
