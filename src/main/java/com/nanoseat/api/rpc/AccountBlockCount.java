package com.nanoseat.api.rpc;

public class AccountBlockCount extends BaseResponse {

    private String blockCount;

    public String getBlockCount() {
        return blockCount;
    }

    public void setBlockCount(String blockCount) {
        this.blockCount = blockCount;
    }
}
