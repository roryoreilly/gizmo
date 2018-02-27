package com.nanoseat.api.rpc;

abstract class BaseResponse {

    private String error;

    boolean isSuccess() {
        return error == null;
    }

    String getError() {
        return error;
    }
}
