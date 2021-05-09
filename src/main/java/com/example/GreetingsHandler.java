package com.example;

import org.telegram.telegrambots.meta.api.objects.Update;

public class GreetingsHandler extends botmaker {
    public void HandleGreet(Update update) {
        String msg = update.getMessage().getText();
        if (msg.equalsIgnoreCase("/start")) {
            String replymsg = "Welcome to @" + getBotUsername()
                    + "\nBot Maker On Fire🔥\nCommand me master What can I do for you\nType /help to show all the Installed Commands.";
            // runmsg(update.getMessage().getChatId().toString(), replymsg);
            sendPhoto(update.getMessage().getChatId().toString(), "botmaker.jfif", replymsg);
        }
        if (msg.equalsIgnoreCase("/hello") || msg.equalsIgnoreCase("/hey") || msg.equalsIgnoreCase("/heya")
                || msg.equalsIgnoreCase("/hola")) {
            String replymsg = msg.replace("/", "") + "  " + update.getMessage().getFrom().getFirstName();
            runmsg(update.getMessage().getChatId().toString(), replymsg);
        }
        if (msg.equalsIgnoreCase("how are you?") || msg.equalsIgnoreCase("how are you")
                || msg.equalsIgnoreCase("wassup") || msg.equalsIgnoreCase("wassup?")) {
            String replymsg = "I'm Fine Master\nHealth Status : Good\nCurrent Branch : master\nAll System Functions Perfectly!";
            runmsg(update.getMessage().getChatId().toString(), replymsg);
        }
        if (msg.equalsIgnoreCase("/help") || msg.equalsIgnoreCase("/helpme") || msg.equalsIgnoreCase("/help me")) {
            int random = (int) (Math.random() * 3);
            String loc[] = { "help.png", "help1.jfif", "help2.jfif", "help3.jfif" };
            String caption = "Thank you for Choosing Bot Maker Services\nBot maker is under Development Contact the Developer https://t.me/Hellion_OP for Upcoming modules😇";
            sendPhoto(update.getMessage().getChatId().toString(),
                    "E:/Telegram_Bot_JAVA/botmaker/src/main/java/com/example/" + loc[random], caption);
        }
    }
}
