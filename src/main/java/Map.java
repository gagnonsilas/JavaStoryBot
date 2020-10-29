

public class Map {

    // Everything in the universe
    String[][][][] maps;
    {
        maps = new String[][][][]
                {
                        {
                                {
                                        {
                                                "You wake up as someone bangs on your door, wait no, that is not your door. \n" +
                                                        "Where are you? you look down at your body and realise that your body is gone \n" +
                                                        "and replaced by a pixelated something? It kind of looks like some sort of \n" +
                                                        "wizard from a video game or something. All of a sudden, text appears floating \n" +
                                                        "over your head that says \"PLAYER ONE - ONE TOKEN TO PLAY\". At once, \n" +
                                                        "you realise what just must have happened. You are inside of a video game. \n" +
                                                        "It seems to be one of those dungeon crawler games. Then the PLAYER ONE text starts \n" +
                                                        "flashing and you realise that the game is about to begin",
                                                "",
                                                "RIGHT"

                                        },
                                        {
                                                "Just an empty room with three doors",
                                                "",
                                                "UP   RIGHT   LEFT",
                                        },
                                        {
                                                "A empty room with only the door you came from",
                                                "",
                                                "LEFT"
                                        },
                                        {
                                                "",
                                                "",
                                                ""
                                        }
                                },
                                {
                                        {
                                                "Just a plain old room with two doors",
                                                "",
                                                "RIGHT   UP"

                                        },
                                        {
                                                "Another empty room",
                                                "",
                                                "RIGHT   LEFT   DOWN",
                                        },
                                        {
                                                "This room is different. One of the doors has a key hole.",
                                                "",
                                                "DOOR TO THE RIGHT   LEFT"
                                        },
                                        {
                                                "YOU WIN! right now this game is still in development and  \n" +
                                                        "once finished will have a lot more Puzzles and levels.  \n \n",
                                                "                  THANKS FOR PLAYING!",
                                                "                    QUIT OR RESTART"
                                        }

                                },
                                {
                                        {
                                                "One more empty plain room",
                                                "",
                                                "RIGHT   DOWN"

                                        },
                                        {
                                                "This room is the same as the last ones",
                                                "",
                                                "RIGHT   LEFT",
                                        },
                                        {
                                                "You the room expecting the same old thing but instead there is a sort of pedestal with a key on it",
                                                "KEY",
                                                "LEFT"
                                        },
                                        {
                                                "",
                                                "",
                                                ""
                                        }
                                }
                        }
                };
    }

    // Holds the current map you are in
    String[][][] map = {};


    // Has the string data to draw the room you are standing in
    String[][][] roomBuildingBlocks;
    {
        roomBuildingBlocks = new String[][][]{
                {
                        {
                                "UP",
                                "  ___| |___\n"
                        },
                        {
                                "JP",
                                "  ___|_|___\n"
                        },
                        {
                                "",
                                "  _________\n"
                        }
                },
                {
                        {
                                "LEFT",
                                "_|     ",
                                "_     "
                        },
                        {
                                "JEFT",
                                "_|     ",
                                "_|    "
                        },
                        {
                                "",
                                " |     ",
                                " |    "
                        }
                },
                {
                        {
                                "RIGHT",
                                "    |_\n",
                                "     _\n"
                        },
                        {
                                "JIGHT",
                                "    |_\n",
                                "    |_\n"
                        },
                        {
                                "",
                                "    | \n",
                                "    | \n"
                        }
                },
                {
                        {
                                "DOWN",
                                " |___   ___| \n",
                                "     | |     \n",
                        },
                        {
                                "jOWN",
                                " |_________| \n",
                                "     | |     \n",
                        },
                        {
                                "",
                                " |_________| \n",
                                "             \n",
                        }
                }
        };

    }

    DiscordInterface discordInterface;

    public Map(DiscordInterface discord)
    {
        loadMap(0);
        discordInterface = discord;
    }



    public void loadMap(int mapNum)
    {

        map = new String[maps[mapNum].length][maps[mapNum][0].length][3];
        for(int x = 0; x < maps[mapNum].length; x++)
        {
            for(int y = 0; y < maps[mapNum][x].length; y++)
            {
                System.arraycopy(maps[mapNum][x][y], 0, map[x][y], 0, maps[mapNum][x][y].length);
            }
        }
    }

    // Prints data from rooms array in a specific format when given room num input
    public void printRoom(int[] pos)
    {

        discordInterface.print(map[pos[0]][pos[1]][0] + "\n");

        discordInterface.print(buildRoom(pos));

        for(int i = 1; i < map[pos[0]][pos[1]].length; i ++)
        {
            discordInterface.print(map[pos[0]][pos[1]][i] + "\n");
        }


    }

    // Creates a text image of the room you are standing in an returns it as a string
    public String buildRoom(int[] pos)
    {
        discordInterface.print("ROOM: ");

        String directions = map[pos[0]][pos[1]][2];
        String items = map[pos[0]][pos[1]][1];
        int[] roomObjs = new int[roomBuildingBlocks.length];
        String room;

        String item = " ";

        if(items.contains("KEY"))
        {
            item = "K";
        }


        for(int i = 0;i < roomBuildingBlocks.length; i++)
        {
            for(int c = 0; c < roomBuildingBlocks[i].length; c++)
            {
                if(directions.contains(roomBuildingBlocks[i][c][0]))
                {
                    roomObjs[i] = c;
                    break;
                }
            }
        }

        room = "```" + roomBuildingBlocks[0][roomObjs[0]][1] +
                roomBuildingBlocks[1][roomObjs[1]][1] + roomBuildingBlocks[2][roomObjs[2]][1] +
                roomBuildingBlocks[1][roomObjs[1]][2] + item + roomBuildingBlocks[2][roomObjs[2]][2] +
                roomBuildingBlocks[3][roomObjs[3]][1] +
                roomBuildingBlocks[3][roomObjs[3]][2] + "```";

        return(room);
    }

    public String[][][] returnMap ()
    {
        return(map);
    }

    public void takeItem(int[] pos)
    {
        map[pos[0]][pos[1]][1] = "";
    }
}
