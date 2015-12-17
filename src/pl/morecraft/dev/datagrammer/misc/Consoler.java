package pl.morecraft.dev.datagrammer.misc;

import java.io.PrintStream;

public class Consoler {

    private PrintStream is;
    private String prefix = "";

    public Consoler(PrintStream is) {
        this.is = is;
    }

    public void print(String s) {
        this.print(s, true);
    }

    public void println(String s) {
        this.println(s, true);
    }

    public void print(String s, boolean addPrefix) {
        if (addPrefix) {
            this.is.print(this.getPrefix() + s);
        } else {
            this.is.print(s);
        }
    }

    public void println(String s, boolean addPrefix) {
        if (addPrefix) {
            this.is.println(this.getPrefix() + s);
        } else {
            this.is.println(s);
        }
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

}
