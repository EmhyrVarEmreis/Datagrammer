package pl.morecraft.dev.datagrammer.commands;


import pl.morecraft.dev.datagrammer.engine.Command;
import pl.morecraft.dev.datagrammer.misc.Consoler;

import java.io.IOException;
import java.net.DatagramSocket;

import static pl.morecraft.dev.datagrammer.misc.Config.DEFAULT_TIMEOUT;

public class Timeout extends Command {

    @SuppressWarnings("Duplicates")
    @Override
    public void setup() {
        addAlias("timeout", false);
        addAlias("t", false);
        setArgsCount(1);
        setArgsInfo("[timeout]");
        setShortInfo("Sets socket timeout to [timeout] ms");
        mustBeBound(false);
        mustBeConnected(false);
        mustBeNotClosed(false);
        mustBeNotNull(false);
    }

    @Override
    public DatagramSocket execute(DatagramSocket socket, Consoler out) throws IOException {
        DEFAULT_TIMEOUT = Integer.valueOf(getArgs()[0]);
        if (socket != null) {
            socket.setSoTimeout(DEFAULT_TIMEOUT);
        }
        out.println("Timeout of socket set to " + DEFAULT_TIMEOUT + " ms");
        return socket;
    }

}
