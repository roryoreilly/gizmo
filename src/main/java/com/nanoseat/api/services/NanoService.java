package com.nanoseat.api.services;

import com.nanoseat.api.entity.User;
import com.nanoseat.api.repositories.UserRepo;
import com.nanoseat.api.rpc.AccountHistory;
import com.nanoseat.api.rpc.History;
import com.nanoseat.api.rpc.NanoClient;
import com.nanoseat.api.web.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NanoService {
    @Autowired
    private NanoClient client;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EventHandler eventHandler;

    private String baseAccount = "xrb_34tzxgtrxpkwxxp7bq7thdnk4mgqopy3bhnu8kopnfby7ofh4mowfxucj19o";
    private String baseAmount = "100";
    private String baseType = "send";

//    @Scheduled(fixedDelay=30000)
    public void updateBalances() {
        for (User user : userRepo.findAll()) {
            String balance = client.getAccountBalance(user.getAccount()).getBalance();
            if (!balance.equals(user.getBalance())) {
                user.setBalance(balance);
                userRepo.save(user);
            }
        }
        eventHandler.updateBalance();
    }

    public void checkUsersForSentBalance() {
        for (User user : userRepo.getUsersToSendBalance()) {
            List<History> history = client.getAccountHistory(user.getAccount(), 10).getHistory();
            for(History h : history) {
                if (h.getHash().equals(user.getMostRecentSendHash()))
                    break;
                if (userSentToPlay(h)) {
                    user.setMoneySent(true);
                    break;
                }
            }
            if (history.size() != 0) {
                user.setMostRecentSendHash(history.get(0).getHash());
                userRepo.save(user);
            }
        }
    }

    private boolean userSentToPlay(History h) {

        String transactionAccount = h.getAccount();
        String transactionAmount = client.kraiFromRaw(h.getAmount());
        String transactionType = h.getType();
        return transactionAccount.equals(baseAccount)
                && transactionAmount.equals(baseAmount)
                && transactionType.equals(baseType);
    }

}
