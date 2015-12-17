package pl.morecraft.dev.datagrammer;

import pl.morecraft.dev.datagrammer.commands.*;
import pl.morecraft.dev.datagrammer.engine.Command;
import pl.morecraft.dev.datagrammer.misc.Consoler;
import pl.morecraft.dev.datagrammer.misc.InvalidArgumentException;
import pl.morecraft.dev.datagrammer.misc.InvalidArgumentsCountException;

import java.io.IOException;
import java.net.DatagramSocket;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Datagrammer {

    public static void main(String[] args) throws IOException, InvalidArgumentException, InvalidArgumentsCountException {
        DatagramSocket socket = null;
        Scanner scanner = new Scanner(System.in);
        List<Command> commands = new LinkedList<>();
        Consoler out = new Consoler(System.out);
        boolean executed = false;
        String s;

        commands.add(new Connect());
        commands.add(new Exit());
        commands.add(new Init());
        commands.add(new PacketSize());
        commands.add(new Read());
        commands.add(new ReadPacket());
        commands.add(new ReadToTimeout());
        commands.add(new Send());
        commands.add(new Timeout());

        out.setPrefix("~$ ");

        out.println("Welcome in Datagrammer!", false);
        out.println("It is developed under Apache License 2.0", false);
        out.println("Type 'help' to view list of available commands", false);

        while (true) {
            String cmd = scanner.nextLine();
            if (cmd.equalsIgnoreCase("help")) {
                for (Command c : commands) {
                    out.println(c.getShortInfo(), false);
                }
                continue;
            } else if (cmd.equalsIgnoreCase("forceexit")) {
                break;
            }
            for (Command c : commands) {
                if ((s = c.checkCommand(cmd)) != null) {
                    try {
                        s = cmd.substring(s.indexOf(s) + 1).trim();
                        if (c.validateArgs(s)) {
                            if (c.parseArgs(s)) {
                                if (c.checkPre(socket)) {
                                    socket = c.execute(socket, out);
                                }
                            }
                        } else {
                            throw new InvalidArgumentsCountException("Invalid argument count");
                        }
                        executed = true;
                        break;
                    } catch (Exception e) {
                        out.print(e.getMessage() + "\n\n");
                        executed = true;
                    }
                }
            }
            if (!executed) {
                out.println("Command not found");
            }
            executed = false;
        }

        out.println("App exited", false);
    }

}
