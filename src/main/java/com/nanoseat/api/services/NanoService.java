package com.nanoseat.api.services;

import com.nanoseat.api.entity.User;
import com.nanoseat.api.repositories.UserRepo;
import com.nanoseat.api.rpc.NanoClient;
import com.nanoseat.api.web.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class NanoService {
    @Autowired
    private NanoClient client;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EventHandler eventHandler;

    @Scheduled(fixedDelay=30000)
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
}
