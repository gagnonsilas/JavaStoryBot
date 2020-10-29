import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class DiscordHandler extends ListenerAdapter {

    static JDA jda;

    public static void main(String[] args) throws LoginException
    {
        jda = new JDABuilder("NzcwMzIyNjYyNTc1ODMzMDg4.X5b44g.OV38xcgpX4WH-UkEY2i7AjP6txI")
                .setActivity(Activity.playing("!playstorygame"))
                .build();

        jda.addEventListener(new DiscordHandler());

        System.out.println(jda.getTextChannels());
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        System.out.println("message");

        if(event.getMessage().getContentRaw().equals("!playstorygame"))
        {
            new JavaStoryGame(event.getAuthor().getName(), event.getChannel(), jda);
        }
    }
}
