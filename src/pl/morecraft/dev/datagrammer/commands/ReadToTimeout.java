package pl.morecraft.dev.datagrammer.commands;

import pl.morecraft.dev.datagrammer.engine.Command;
import pl.morecraft.dev.datagrammer.misc.Config;
import pl.morecraft.dev.datagrammer.misc.Consoler;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;

public class ReadToTimeout extends Command {

    @SuppressWarnings("Duplicates")
    @Override
    public void setup() {
        addAlias("read to timeout", true);
        addAlias("rtt", true);
        setArgsCount(0);
        setArgsInfo("");
        setShortInfo("Reads all available packets and prints output");
        mustBeBound(true);
        mustBeConnected(false);
        mustBeNotClosed(true);
        mustBeNotNull(true);
    }

    @Override
    public DatagramSocket execute(DatagramSocket socket, Consoler out) throws IOException {
        String msg = "";
        try {
            while (true) {
                byte[] buff = new byte[Config.DEFAULT_PACKET_SIZE];
                DatagramPacket packet = new DatagramPacket(buff, buff.length);
                socket.receive(packet);
                msg += new String(packet.getData(), Config.DEFAULT_CHARSET);
                if (packet.getLength() == 0) {
                    break;
                }
            }
        } catch (SocketTimeoutException ste) {
            out.println("Received: '" + msg + "'");
        }
        return socket;
    }

}
