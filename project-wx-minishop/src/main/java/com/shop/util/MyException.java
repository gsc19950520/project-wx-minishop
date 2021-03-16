package com.shop.util;

import com.shop.enums.BusinessEnum;

public class MyException extends RuntimeException {
    private static final long serialVersionUID = -7419400618793645414L;
    private String code = "200";
    private String msg;

    public MyException() {
    }

    public MyException(String message) {
        super(message);
        this.msg = message;
    }

    public MyException(String code, String message) {
        super(message);
        this.code = code;
        this.msg = message;
    }

    public MyException(BusinessEnum businessEnum) {
        super(businessEnum.getValueInFact());
        this.code = businessEnum.getValue();
        this.msg = businessEnum.getValueInFact();
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public void setMsg(final String msg) {
        this.msg = msg;
    }
}
