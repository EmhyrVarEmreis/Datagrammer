package pl.morecraft.dev.datagrammer.commands;

import pl.morecraft.dev.datagrammer.engine.Command;
import pl.morecraft.dev.datagrammer.misc.Consoler;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Objects;

public class Init extends Command {

    @SuppressWarnings("Duplicates")
    @Override
    public void setup() {
        addAlias("init", false);
        addAlias("i", false);
        setArgsCount(2);
        setArgsInfo("[host] [port]");
        setShortInfo("Bounds socket to [host]:[port]");
        mustBeBound(false);
        mustBeConnected(false);
        mustBeNotClosed(false);
        mustBeNotNull(false);
    }

    @Override
    public DatagramSocket execute(DatagramSocket socket, Consoler out) throws UnknownHostException, SocketException {
        String[] s = getArgs();
        s[0] = Objects.equals(s[0], "l") ? "localhost" : s[0];
        socket = new DatagramSocket(Integer.parseInt(s[1]), InetAddress.getByName(s[0]));
        out.println("Bound to " + s[0] + ":" + s[1]);
        return socket;
    }

}
