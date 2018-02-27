package com.nanoseat.api.rpc;

import java.util.List;

public class Chain extends BaseResponse {

    private List<String> blocks;

    public List<String> getBlocks() {
        return blocks;
    }
}
