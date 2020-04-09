package com.carpediem.events;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Caibank extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent e){

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = new Date();

        String message[] = e.getMessage().getContentRaw().split(" ");
        String name = e.getMember().getUser().getName();
        String today = format.format(time);

        if(message[0].equalsIgnoreCase("$은행")){
            e.getChannel().sendMessage("```<카이뱅크 은행업무 명령어>\n"
                    + "\n 예금 업무: $예금 [고유번호] [닉네임] [예금액(억)(숫자만 입력)]"
                    + "```").queue();
        }

        else if(message[0].equalsIgnoreCase("$예금")){
            int depositNumber = Integer.parseInt(message[1]); // 고유번호
            String customer = message[2];
            int depositMount = Integer.parseInt(message[3]); // 예금액
            e.getChannel().sendMessage("```<카이뱅크 예금 / 출금 >\n"
                    + "\n닉네임: " + customer
                    + "\n고유번호: " + depositNumber
                    + "\n예금/출금일: " + today
                    + "\n예금/출금액: " + depositMount + "억"
                    + "\n담당자: " + name + "```").queue();
        }
    }
}
