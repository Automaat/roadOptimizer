package com.hereforbeer.web;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorInfo {

    //BAD REQUEST EXCEPTION
    BAD_LOCATION(4103, "Bad location"),

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
