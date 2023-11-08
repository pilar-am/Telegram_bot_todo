package com.pilar.SpringBootBotTelegram.Handle;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class HandleTesting extends TelegramLongPollingBot {


    private String telegramToken = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";

    private final List<String> todos = new ArrayList<>();

    @Override
    public String getBotUsername() {
        return "pili_todo_bot";
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }
    @Override
    public String getBotToken(){
        return telegramToken;
    }
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            String text = update.getMessage().getText();

            if (text.startsWith("/addtodo")) {
                String todo = text.substring("/addtodo".length()).trim();
                if (!todo.isEmpty()) {
                    todos.add(todo);
                    sendResponse(chatId, "Tarea agregada: " + todo);
                }
            } else if (text.equals("/listtodos")) {
                StringBuilder todoList = new StringBuilder("Tareas pendientes:\n");
                for (String todo : todos) {
                    todoList.append("- ").append(todo).append("\n");
                }
                sendResponse(chatId, todoList.toString());
            } else if (text.startsWith("/deltodo")) {
                String result = text.substring("/deltodo".length()).trim();
                if (!result.isEmpty()) {
                    for(String todo:todos){
                        if(todo.equalsIgnoreCase(result)){
                            todos.remove(result);
                            sendResponse(chatId, "Tarea eliminada: " + todo);
                        }
                    }
                }
            }
        }
    }

    private void sendResponse(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        try {
            execute(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
