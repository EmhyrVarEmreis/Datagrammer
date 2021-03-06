package pl.morecraft.dev.datagrammer;

import pl.morecraft.dev.datagrammer.commands.*;
import pl.morecraft.dev.datagrammer.engine.Command;
import pl.morecraft.dev.datagrammer.misc.Consoler;
import pl.morecraft.dev.datagrammer.misc.InvalidArgumentException;
import pl.morecraft.dev.datagrammer.misc.InvalidArgumentsCountException;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;
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

        commands.add(new Conf());
        commands.add(new Connect());
        commands.add(new Exit());
        commands.add(new Init());
        commands.add(new PacketSize());
        commands.add(new ReadPackets());
        commands.add(new ReadOnePacket());
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
                out.println("", false);
                for (Command c : commands) {
                    out.println(c.getShortInfo() + "\n", false);
                }
                continue;
            } else if (cmd.equalsIgnoreCase("forceexit")) {
                break;
            }
            for (Command c : commands) {
                if ((s = c.checkCommand(cmd)) != null) {
                    try {
                        s = cmd.substring(cmd.indexOf(s) + s.length()).trim();
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
                    } catch (SocketTimeoutException e) {
                        out.print(e.getMessage() + "\n");
                        executed = true;
                        break;
                    } catch (Exception e) {
                        out.print(e.getMessage() + "\n\n");
                        executed = true;
                        break;
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
