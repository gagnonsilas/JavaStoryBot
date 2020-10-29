

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageChannel;

import java.util.Scanner;

public class JavaStoryGame {

    Scanner scan = new Scanner(System.in);

    DiscordInterface discordInterface;

    CommandHandler commandHandler;

    public JavaStoryGame(String user, MessageChannel channel, JDA jda)
    {
        System.out.println(user);
        System.out.println(channel);

        jda.addEventListener(discordInterface = new DiscordInterface(channel, user, commandHandler));
        commandHandler = new CommandHandler(discordInterface);

        runGame();

    }

    // Runs Game Loop
    public void runGame()
    {

        commandHandler.printRoom();

        discordInterface.print("Thanks for playing");
    }
}