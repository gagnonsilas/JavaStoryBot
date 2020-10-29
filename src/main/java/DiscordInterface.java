import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DiscordInterface extends ListenerAdapter {

    MessageChannel channel;
    String user;
    public CommandHandler commandHandler;
    Message lastMessage;

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

    public void react(String reaction)
    {
        lastMessage.addReaction(reaction).queue();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if(event.getAuthor().getName().equals(user) && event.getChannel() == channel)
        {
            String input = event.getMessage().getContentRaw();

            lastMessage = event.getMessage();

            if(input.contains("quit"))
            {
                react("\uD83D\uDE22");
                print("Thanks for playing");
            }
            else if(commandHandler.doAction(input))
            {
                commandHandler.printRoom();
            }
        }
    }
}
