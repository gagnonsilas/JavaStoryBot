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
        jda = JDABuilder.createDefault("Token")
                .setActivity(Activity.playing("!playstorygame"))
                .build();

        jda.addEventListener(new DiscordHandler());

        System.out.println(jda.getGuilds());
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if(event.getMessage().getContentRaw().contains("!playstorygame"))
        {
            new JavaStoryGame(event.getAuthor().getName(), event.getChannel(), jda);
        }
    }
}
