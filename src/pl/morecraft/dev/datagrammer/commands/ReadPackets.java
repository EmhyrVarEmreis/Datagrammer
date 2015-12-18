package pl.morecraft.dev.datagrammer.commands;

import pl.morecraft.dev.datagrammer.engine.Command;
import pl.morecraft.dev.datagrammer.misc.Config;
import pl.morecraft.dev.datagrammer.misc.Consoler;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReadPackets extends Command {

    @SuppressWarnings("Duplicates")
    @Override
    public void setup() {
        addAlias("read packets", false);
        addAlias("rp", false);
        setArgsCount(1);
        setArgsInfo("[count]");
        setShortInfo("Reads [count] packet and prints output");
        mustBeBound(true);
        mustBeConnected(false);
        mustBeNotClosed(true);
        mustBeNotNull(true);
    }

    @Override
    public DatagramSocket execute(DatagramSocket socket, Consoler out) throws IOException {
        int i = Integer.parseInt(getArgs()[0]);
        String msg = "";
        int count = 0;
        while (i-- > 0) {
            byte[] buff = new byte[Config.DEFAULT_PACKET_SIZE];
            DatagramPacket packet = new DatagramPacket(buff, buff.length);
            socket.receive(packet);
            msg += new String(packet.getData(), Config.DEFAULT_CHARSET);
            count++;
        }
        out.println("Received: '" + msg + "' in " + count + " packets");
        return socket;
    }

}
