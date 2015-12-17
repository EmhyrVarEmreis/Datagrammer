package pl.morecraft.dev.datagrammer.commands;

import pl.morecraft.dev.datagrammer.engine.Command;
import pl.morecraft.dev.datagrammer.misc.Consoler;

import java.net.DatagramSocket;

import static pl.morecraft.dev.datagrammer.misc.Config.*;

public class Conf extends Command {

    @SuppressWarnings("Duplicates")
    @Override
    public void setup() {
        addAlias("conf", false);
        addAlias("cn", false);
        setArgsCount(1);
        setArgsInfo("[option]");
        setShortInfo("Shows configuration [option] (timeout, packetsize, charset)");
        mustBeBound(false);
        mustBeConnected(false);
        mustBeNotClosed(false);
        mustBeNotNull(false);
    }

    @Override
    public DatagramSocket execute(DatagramSocket socket, Consoler out) {
        switch (getArgs()[0]) {
            case "timeout":
                out.println("Timeout is set to " + DEFAULT_TIMEOUT + " ms");
                break;
            case "charset":
                out.println("Charset is set to '" + DEFAULT_CHARSET.displayName() + "'");
                break;
            case "packetsize":
                out.println("Packet size is set to " + DEFAULT_PACKET_SIZE + " bytes");
                break;
            default:
                out.println("Invalid config option '" + getArgs()[0] + "'");
                break;
        }
        return socket;
    }

}
