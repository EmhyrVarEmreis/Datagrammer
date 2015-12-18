package pl.morecraft.dev.datagrammer.commands;

import pl.morecraft.dev.datagrammer.engine.Command;
import pl.morecraft.dev.datagrammer.misc.Consoler;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

import static pl.morecraft.dev.datagrammer.misc.Config.DEFAULT_CHARSET;
import static pl.morecraft.dev.datagrammer.misc.Config.DEFAULT_PACKET_SIZE;

public class Send extends Command {

    @SuppressWarnings("Duplicates")
    @Override
    public void setup() {
        addAlias("send", false);
        addAlias("s", false);
        setArgsCount(-1);
        setArgsInfo("[text]");
        setShortInfo("Sends [text]");
        mustBeBound(true);
        mustBeConnected(true);
        mustBeNotClosed(true);
        mustBeNotNull(true);
    }

    @Override
    public DatagramSocket execute(DatagramSocket socket, Consoler out) throws IOException {

        String txt = getArgs()[0];
        byte[] data = txt.getBytes(DEFAULT_CHARSET);
        int count = (int) Math.ceil(1.0 * data.length / DEFAULT_PACKET_SIZE);
        for (int i = 0; i < count; i++) {
            byte[] buff = new byte[DEFAULT_PACKET_SIZE];
            buff = Arrays.copyOfRange(data, i * DEFAULT_PACKET_SIZE, Math.max((i + 1) * DEFAULT_PACKET_SIZE, buff.length));
            DatagramPacket packet = new DatagramPacket(buff, buff.length);
            socket.send(packet);
        }
        out.println("Sent: '" + new String(data, DEFAULT_CHARSET) + "' in " + (count) + " packets (" + data.length + "b) ");

        return socket;
    }

}
