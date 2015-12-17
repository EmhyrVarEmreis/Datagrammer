package pl.morecraft.dev.datagrammer.commands;

import pl.morecraft.dev.datagrammer.engine.Command;
import pl.morecraft.dev.datagrammer.misc.Consoler;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Objects;

public class Connect extends Command {

    @SuppressWarnings("Duplicates")
    @Override
    public void setup() {
        addAlias("connect", false);
        addAlias("c", false);
        setArgsCount(2);
        setArgsInfo("[host] [port]");
        setShortInfo("Connects socket to [host]:[port]");
        mustBeBound(true);
        mustBeConnected(false);
        mustBeNotClosed(true);
        mustBeNotNull(true);
    }

    @Override
    public DatagramSocket execute(DatagramSocket socket, Consoler out) throws IOException {
        String[] s = getArgs();
        s[0] = Objects.equals(s[0], "l") ? "localhost" : s[0];
        socket.connect(InetAddress.getByName(s[0]), Integer.parseInt(s[1]));
        out.println("Connected to " + s[0] + ":" + s[1]);
        return socket;
    }

}
