package com.nanoseat.api.rpc;

import java.util.List;

public class AccountsCreate extends BaseResponse {

    private List<String> accounts;

    public List<String> getAccounts() {
        return accounts;
    }
}
