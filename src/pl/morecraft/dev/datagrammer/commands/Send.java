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
        txt = "";
        int count = 0;
        for (int i = 0; i < (data.length / DEFAULT_PACKET_SIZE) + 1; i++) {
            byte[] buff = new byte[DEFAULT_PACKET_SIZE];
            buff = Arrays.copyOfRange(data, i * DEFAULT_PACKET_SIZE, Math.max((i + 1) * DEFAULT_PACKET_SIZE - 1, buff.length - 1));
            DatagramPacket packet = new DatagramPacket(buff, buff.length);
            txt += new String(packet.getData(), DEFAULT_CHARSET);
            socket.send(packet);
            count++;
        }
        out.println("Sent: '" + txt + "' in " + (count) + " packets");

        return socket;
    }

}
