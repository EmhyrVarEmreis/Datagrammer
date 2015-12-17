package pl.morecraft.dev.datagrammer.commands;


import pl.morecraft.dev.datagrammer.engine.Command;
import pl.morecraft.dev.datagrammer.misc.Consoler;

import java.io.IOException;
import java.net.DatagramSocket;

import static pl.morecraft.dev.datagrammer.misc.Config.DEFAULT_PACKET_SIZE;

public class PacketSize extends Command {

    @SuppressWarnings("Duplicates")
    @Override
    public void setup() {
        addAlias("packet size", false);
        addAlias("ps", false);
        setArgsCount(1);
        setArgsInfo("[packetsize]");
        setShortInfo("Sets socket packet size to [packetsize] bytes");
        mustBeBound(false);
        mustBeConnected(false);
        mustBeNotClosed(false);
        mustBeNotNull(false);
    }

    @Override
    public DatagramSocket execute(DatagramSocket socket, Consoler out) throws IOException {
        DEFAULT_PACKET_SIZE = Integer.valueOf(getArgs()[0]);
        out.println("Default size of packet set to " + DEFAULT_PACKET_SIZE + " bytes");
        return socket;
    }

}
