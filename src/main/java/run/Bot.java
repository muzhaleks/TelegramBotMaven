package run;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.io.*;
import java.util.Properties;

public class Bot extends TelegramLongPollingBot {

    PageOne pageOne = new PageOne();
    PageTwo pageTwo = new PageTwo();

    private String getToken() {
        FileInputStream fis;
        Properties botProperties = new Properties();
        try {
            fis = new FileInputStream("src/main/java/configurations/config");
            botProperties.load(fis);

            String token = botProperties.getProperty("TOKEN");
            return token;

        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }


    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            pageOne.setButton(sendMessage);
            execute(sendMessage);
            onClosing();
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void setNewKeyboard(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            pageTwo.setButton(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод приёма сообщений
     */
    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {

            switch (message.getText()) {
                case "/start":
                    sendMsg(message, "Начнём!");
                    break;
                case "Помощь":
                    sendMsg(message, "Чем я могу помочь?");
                    break;
                case "Свечи":
                    setNewKeyboard(message, "Давйте что-то выберем!");
                    break;
                case "Амулеты":
                    sendMsg(message, "Сейчас в наличии - здесь будет список");
                    break;
                case "Связаться с мастером":
                    sendMsg(message,"text" );


            }
        }

    }


    @Override
    public String getBotUsername() {
        return "CandleArtifactBot";
    }

    @Override
    public String getBotToken() {
        return getToken();
    }

}
