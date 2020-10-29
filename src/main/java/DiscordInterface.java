import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DiscordInterface extends ListenerAdapter {

    MessageChannel channel;
    String user;
    CommandHandler commandHandler;

    public DiscordInterface(MessageChannel messageChannel, String iUser, CommandHandler inputCommandHandler)
    {
        channel = messageChannel;
        user = iUser;
        commandHandler = inputCommandHandler;
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
            String input = event.getMessage().getContentRaw();

            if(input.contains("quit"))
            {
                print("Thanks for playing");
            }

            if(commandHandler.doAction(input))
            {
                commandHandler.printRoom();
            }
        }
    }
}
