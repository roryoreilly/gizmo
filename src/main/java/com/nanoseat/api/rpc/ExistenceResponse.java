package com.nanoseat.api.rpc;

class ExistenceResponse extends BaseResponse {

    private String exists;

    public boolean exists() {
        return "1".equals(exists);
    }
}
