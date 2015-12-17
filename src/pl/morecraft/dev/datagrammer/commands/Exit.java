package pl.morecraft.dev.datagrammer.commands;


import pl.morecraft.dev.datagrammer.engine.Command;
import pl.morecraft.dev.datagrammer.misc.Consoler;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Exit extends Command {

    @SuppressWarnings("Duplicates")
    @Override
    public void setup() {
        addAlias("exit", true);
        addAlias("e", true);
        setArgsCount(0);
        setArgsInfo("");
        setShortInfo("Exit program");
        mustBeBound(false);
        mustBeConnected(false);
        mustBeNotClosed(false);
        mustBeNotNull(false);
    }

    @Override
    public DatagramSocket execute(DatagramSocket socket, Consoler out) throws UnknownHostException, SocketException {
        out.println("Exiting...");
        System.exit(0);
        return socket;
    }

}
