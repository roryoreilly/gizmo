package com.nanoseat.api.rpc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AccountsPending extends BaseResponse {

    private Map<String, List<String>> blocks;

    public List<AccountPending> getAccounts() {
        List<AccountPending> output = new ArrayList<>();
        for (String address : blocks.keySet()) {
            output.add(new AccountPending(address, blocks.get(address)));
        }
        return output;
    }
}
