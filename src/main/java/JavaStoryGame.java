

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

        jda.addEventListener(discordInterface = new DiscordInterface(channel, user));
        commandHandler = new CommandHandler(discordInterface);

        runGame();

    }

    // Runs Game Loop
    public void runGame()
    {

        commandHandler.printRoom();

        while(true)
        {

            //  Get user choice
            String input = discordInterface.getInput();



            if(input.contains("quit"))
            {
                break;
            }

            if(!input.equals(""))
            {
                if(commandHandler.doAction(input))
                {
                    commandHandler.printRoom();
                }
            }
        }
        discordInterface.print("Thanks for playing");
    }
}