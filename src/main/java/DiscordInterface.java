import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DiscordInterface extends ListenerAdapter {

    MessageChannel channel;
    String user;
    String response = "";

    public DiscordInterface(MessageChannel messageChannel, String iUser)
    {
        channel = messageChannel;
        user = iUser;
    }

    public void print(String message)
    {
        try
        {
            channel.sendMessage(message).queue();
        } catch (IllegalStateException e) {
            System.out.println("Catch!!!");
        }

    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        System.out.println("message");
        if(event.getAuthor().getName().equals(user) && event.getChannel() == channel)
        {
            System.out.println("correct");
            response = event.getMessage().getContentRaw();
            System.out.println(response);
        }
    }

    public String getInput()
    {
        System.out.println("getInput");
        String localResponse = "";

        response = "";

        return(localResponse);
    }
}
