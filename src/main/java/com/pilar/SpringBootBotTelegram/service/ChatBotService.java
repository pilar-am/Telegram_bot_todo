package com.pilar.SpringBootBotTelegram.service;

import com.pilar.SpringBootBotTelegram.Handle.HandleTesting;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Service
public class ChatBotService {

    public ChatBotService(){
        try{
            HandleTesting handleTesting = new HandleTesting();
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(handleTesting);
        }catch(Exception e){
            e.printStackTrace();
        }
    }



}
