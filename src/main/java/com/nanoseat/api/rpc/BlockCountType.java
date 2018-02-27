package com.nanoseat.api.rpc;

public class BlockCountType extends BaseResponse {

    private String send;
    private String receive;
    private String open;
    private String change;

    public String getSend() {
        return send;
    }

    public String getReceive() {
        return receive;
    }

    public String getOpen() {
        return open;
    }

    public String getChange() {
        return change;
    }
}
