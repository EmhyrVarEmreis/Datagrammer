package pl.morecraft.dev.datagrammer.misc;

import sun.nio.cs.US_ASCII;

import java.nio.charset.Charset;

public class Config {

    public static final Charset DEFAULT_CHARSET = new US_ASCII();
    public static Integer DEFAULT_PACKET_SIZE = 12;
    public static Integer DEFAULT_TIMEOUT = 1000;

}
