package com.nanoseat.api.services;

import com.nanoseat.api.web.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;


@Service
public class GameService {

    @Autowired
    private EventHandler eventHandler;


    @Scheduled(fixedDelay = 10000)
    public void timerComplete() {
        eventHandler.timerUpdate(LocalDateTime.now().toString());
    }
}
