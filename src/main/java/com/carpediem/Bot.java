package com.carpediem;

        import com.carpediem.events.Caibank;
        import com.carpediem.events.Lottery;
        import net.dv8tion.jda.api.JDA;
        import net.dv8tion.jda.api.JDABuilder;

public class Bot {
    public static void main(String[] args) throws Exception{

        JDA jda = new JDABuilder("Njk0OTMyMjcwNDE4NjI0NjQz.Xo3ekw.NV7eC7wlB9ZaoZSgfjEbNiXGbKc").build();

        jda.addEventListener(new Lottery());
        jda.addEventListener(new Caibank());

    }
}
