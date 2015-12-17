package pl.morecraft.dev.datagrammer.commands;

import pl.morecraft.dev.datagrammer.engine.Command;
import pl.morecraft.dev.datagrammer.misc.Config;
import pl.morecraft.dev.datagrammer.misc.Consoler;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReadOnePacket extends Command {

    @SuppressWarnings("Duplicates")
    @Override
    public void setup() {
        addAlias("read single packet", true);
        addAlias("rsp", true);
        setArgsCount(0);
        setArgsInfo("");
        setShortInfo("Reads single packet and prints output");
        mustBeBound(true);
        mustBeConnected(true);
        mustBeNotClosed(true);
        mustBeNotNull(true);
    }

    @Override
    public DatagramSocket execute(DatagramSocket socket, Consoler out) throws IOException {
        byte[] buff = new byte[Config.DEFAULT_PACKET_SIZE];
        DatagramPacket packet = new DatagramPacket(buff, buff.length);
        socket.receive(packet);
        out.println("Received: '" + new String(packet.getData(), Config.DEFAULT_CHARSET) + "'");
        return socket;
    }

}
