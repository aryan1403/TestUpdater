package com.example;

import java.io.File;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class botmaker extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            GreetingsHandler g = new GreetingsHandler();
            g.HandleGreet(update);
            updater u = new updater();
            u.isUpdate(update);
        }
    }

    public String getCurrentChatId(Update update) {
        return update.getMessage().getChatId().toString();
    }

    public void runmsg(String id, String msg) {
        SendMessage smsg = new SendMessage(id, msg);
        try {
            execute(smsg);
        } catch (TelegramApiException e) {
            CustomException(id);
            e.printStackTrace();
        }
    }

    public void CustomException(String id) {
        SendMessage smsg = new SendMessage(id, "Warning\nSome functions are not Working Properly!");
        try {
            execute(smsg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendPhoto(String chatId, String picloc, String caption) {
        File f = new File(picloc);
        InputFile file = new InputFile(f);
        SendPhoto pic = new SendPhoto(chatId, file);
        pic.setCaption(caption);

        try {
            execute(pic);
        } catch (TelegramApiException e) {
            CustomException(chatId);
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "Enter your bot username here";
    }

    @Override
    public String getBotToken() {
        return "Enter your Bot token here";
    }
}
