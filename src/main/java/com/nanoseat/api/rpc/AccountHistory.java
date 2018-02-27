package com.nanoseat.api.rpc;

import java.util.List;

public class AccountHistory extends BaseResponse {

    private List<History> history;

    public List<History> getHistory() {
        return history;
    }
}
