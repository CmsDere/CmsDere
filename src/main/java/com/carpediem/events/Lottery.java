package com.carpediem.events;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Lottery extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent e){
        // 닉네임:
        // 고유번호:
        // 추첨번호:
        // 구매일시:
        // 수취인:

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = new Date();

        String[] message = e.getMessage().getContentRaw().split(" ");
        String name = e.getMember().getUser().getName();
        String today = format.format(time);

        int saveNumber[] = new int[6];



        if(message[0].equalsIgnoreCase("$안내") && message.length == 1){
            e.getChannel().sendMessage("```\n< 명령어 사용 방법 >" +
                    "\n 수동 번호 입력 : $수동 [고유번호] [구매자] [번호] [번호] [번호] [번호] [번호] [번호]" +
                    "\n 자동 번호 생성 : $자동 [고유번호] [구매자] [한번에 추첨할 양]" +
                    "\n 공식 번호 생성 : $공식" +
                    "```").queue();
        }

        else if(message[0].equalsIgnoreCase("$수동")){
            int buyerNumber = Integer.parseInt(message[1]);
            String buyerName = message[2];
            int manualNumber1 = Integer.parseInt(message[3]);
            int manualNumber2 = Integer.parseInt(message[4]);
            int manualNumber3 = Integer.parseInt(message[5]);
            int manualNumber4 = Integer.parseInt(message[6]);
            int manualNumber5 = Integer.parseInt(message[7]);
            int manualNumber6 = Integer.parseInt(message[8]);

            e.getChannel().sendMessage("```\n<로또 수동 입력>\n" +
                    "\n닉네임: " + buyerName +
                    "\n고유번호: " + buyerNumber +
                    "\n추첨번호: " + manualNumber1 + ", " + manualNumber2 + ", " + manualNumber3 + ", " +
                    manualNumber4 + ", " + manualNumber5 + ", " + manualNumber6 +
                    "\n구매일시: " + today +
                    "\n수취인: " + name +
                    "```").queue();
        }

        else if(message[0].equalsIgnoreCase("$자동")){
            int buyerNumber = Integer.parseInt(message[1]);
            String buyerName = message[2];
            int count = Integer.parseInt(message[3]);
            for(int a = 1; a <= count; a++) {

                int lotto[] = new int[6];
                for (int i = 0; i < lotto.length; i++) {
                    lotto[i] = (int) (Math.random() * 45) + 1;
                    for (int j = 0; j < i; j++) {
                        if (lotto[i] == lotto[j]) {
                            i--;
                            break;
                        }
                    }
                }

                e.getChannel().sendMessage("```\n<로또 자동 추첨>\n" +
                        "\n닉네임: " + buyerName +
                        "\n고유번호: " + buyerNumber +
                        "\n추첨번호: " + lotto[0] + ", " + lotto[1] + ", " + lotto[2] + ", " + lotto[3] + ", " + lotto[4] + ", " + lotto[5] +
                        "\n구매일시: " + today +
                        "\n수취인: " + name +
                        "```").queue();

            }
        }

        else if(message[0].equalsIgnoreCase("$공식")){

            int lottoOfficial[] = new int[6];
            for(int i = 0; i < lottoOfficial.length; i++){
                lottoOfficial[i] = (int)(Math.random() * 45) + 1;
                for(int j = 0; j < i; j++){
                    if(lottoOfficial[i] == lottoOfficial[j]){
                        i--;
                        break;
                    }
                }
                saveNumber[i] = lottoOfficial[i];
            }

            e.getChannel().sendMessage("```\n<로또 공식 추첨>" +
                    "\n1번째 당첨번호: " + lottoOfficial[0] +
                    "\n2번째 당첨번호: " + lottoOfficial[1] +
                    "\n3번째 당첨번호: " + lottoOfficial[2] +
                    "\n4번째 당첨번호: " + lottoOfficial[3] +
                    "\n5번째 당첨번호: " + lottoOfficial[4] +
                    "\n6번째 당첨번호: " + lottoOfficial[5] +
                    "```").queue();
        }
    }
}
