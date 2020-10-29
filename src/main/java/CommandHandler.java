
import java.util.ArrayList;
import java.util.List;

public class CommandHandler {

    // Character Position on the map

    DiscordInterface discordInterface;

    Map mapHandler;

    int[] pos = {0, 0};

    String[][][] map;

    public CommandHandler(DiscordInterface discord)
    {
        discordInterface = discord;

        mapHandler = new Map(discordInterface);

        map = mapHandler.returnMap();
    }



    List<String> inventory = new ArrayList<String>();

    // Takes user request checks for commands it has then returns if the command was successful
    public Boolean doAction(String input)
    {

        if(input.contains("help"))
        {
            discordInterface.react("\uD83E\uDD26");
            return(true);
        }
        else if(input.contains("go"))
        {
            return(go(input));
        }

        else if(input.contains("take"))
        {
            return(take(input));
        }
        else if(input.contains("restart"))
        {
            pos[0] = 0;
            pos[1] = 0;
            inventory = new ArrayList<String>();
            mapHandler.loadMap(0);
            return(true);
        }
        else{
            discordInterface.react("U+1F62B");
            discordInterface.print("Sorry I don't recognize that command\n\n");
            return(false);
        }
    }

    // Try's to remove whatever item user requested from room and add to their inventory
    public Boolean take(String input)
    {

        if (input.contains(map[this.pos[0]][this.pos[1]][1].toLowerCase()) && !map[this.pos[0]][this.pos[1]][1].toLowerCase().equals("")){
            inventory.add(map[this.pos[0]][this.pos[1]][1]);
            discordInterface.print(map[this.pos[0]][this.pos[1]][1].toLowerCase() + " taken");
            mapHandler.takeItem(this.pos);
            map = mapHandler.returnMap();
            return(true);
        }
        else
        {
            discordInterface.print("That item is not in this room at the moment");
            return(false);
        }
    }

    // Takes user input and try's to go in the direction they requested
    public Boolean go(String input)
    {
        String directions = map[pos[0]][pos[1]][2];

        if(directions.contains("DOOR") && !inventory.contains("KEY"))
        {
            directions = directions.substring(0, directions.indexOf("DOOR TO THE") + 12) + "J" +
                    directions.substring(directions.indexOf("DOOR TO THE") + 13);

        }


        if(input.contains("go"))
        {
            if(input.contains("up") && directions.contains("UP"))
            {
                pos[0] ++;
            }
            else if(input.contains("down") && directions.contains("DOWN"))
            {
                pos[0] --;
            }
            else if(input.contains("right") && directions.contains("RIGHT"))
            {
                pos[1] ++;
            }
            else if(input.contains("left") && directions.contains("LEFT"))
            {
                pos[1] --;
            }
            else if(directions.contains("DOOR"))
            {
                discordInterface.print("The door is locked. try to find a key for it \n");
                return(false);
            }
            else
            {
                discordInterface.print("You can't go that way right now \n");
                return(false);
            }
            return(true);
        }
        return(false);
    }

    public void printRoom()
    {
        mapHandler.printRoom(pos);
    }
}
