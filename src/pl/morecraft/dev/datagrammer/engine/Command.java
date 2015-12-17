package pl.morecraft.dev.datagrammer.engine;

import pl.morecraft.dev.datagrammer.misc.CommandAlias;
import pl.morecraft.dev.datagrammer.misc.Consoler;
import pl.morecraft.dev.datagrammer.misc.InvalidArgumentException;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public abstract class Command {

    private List<CommandAlias> aliases = new ArrayList<>();
    private int argsCount = 0;
    private String shortInfo = "";
    private String argsInfo = "";
    private String[] args;
    private boolean mustBeConnected = false;
    private boolean mustBeBound = false;
    private boolean mustBeNotClosed = false;
    private boolean mustBeNotNull = false;

    public Command() {
        setup();
    }

    public boolean checkPre(DatagramSocket socket) throws SocketException, NullPointerException {
        if (mustBeNotNull()) {
            if (socket == null) {
                throw new NullPointerException("Socket must be not null to perform this operation");
            }
        }
        if (mustBeConnected()) {
            if (!socket.isConnected()) {
                throw new SocketException("Socket must be connected to perform this operation");
            }
        }
        if (mustBeBound()) {
            if (!socket.isBound()) {
                throw new SocketException("Socket must be bound to perform this operation");
            }
        }
        if (mustBeNotClosed()) {
            if (socket.isClosed()) {
                throw new SocketException("Socket must be not closed to perform this operation");
            }
        }
        return true;
    }

    public String checkCommand(String cmd) {
        for (CommandAlias cm : getAliases()) {
            if (cm.isEqual()) {
                if (cmd.equalsIgnoreCase(cm.getValue())) {
                    return cm.getValue();
                }
            } else {
                if (cmd.startsWith(cm.getValue())) {
                    return cm.getValue();
                }
            }
        }
        return null;
    }

    public boolean validateArgs(String cmd) {
        if (cmd.split("\\s+").length == getArgsCount() || (cmd.split("\\s+").length > 0 && getArgsCount() == -1)) {
            return true;
        }
        return true;
    }

    public boolean parseArgs(String args) throws InvalidArgumentException {
        String[] s;
        if (getArgsCount() == -1) {
            s = new String[1];
            s[0] = args;
        } else if (args.trim().isEmpty()) {
            s = null;
            if (getArgsCount() != 0) {
                throw new InvalidArgumentException("Expected " + getArgsCount() + " arguments instead of " + 0);
            }
        } else {
            s = args.split("\\s+");
            if (s.length != getArgsCount()) {
                throw new InvalidArgumentException("Expected " + getArgsCount() + " arguments instead of " + s.length);
            }
        }
        this.args = s;
        return true;
    }

    public String getShortInfo() {
        String s = "";
        for (CommandAlias cm : aliases) {
            s += cm.getValue() + " | ";
        }
        s = s.substring(0, s.length() - 3);
        s += " " + getArgsInfo();
        s += "\n\t" + shortInfo;
        return s;
    }

    public void addAlias(CommandAlias cm) {
        aliases.add(cm);
    }

    public void addAlias(String value, boolean isEqual) {
        this.addAlias(new CommandAlias(value, isEqual));
    }

    public void setShortInfo(String shortInfo) {
        this.shortInfo = shortInfo;
    }

    public String getArgsInfo() {
        return argsInfo;
    }

    public void setArgsInfo(String argsInfo) {
        this.argsInfo = argsInfo;
    }

    public List<CommandAlias> getAliases() {
        return aliases;
    }

    public int getArgsCount() {
        return argsCount;
    }

    public void setArgsCount(int argsCount) {
        this.argsCount = argsCount;
    }

    public String[] getArgs() {
        return args;
    }

    public boolean mustBeNotNull() {
        return mustBeNotNull;
    }

    public void mustBeNotNull(boolean canBeNull) {
        this.mustBeNotNull = canBeNull;
    }

    public boolean mustBeConnected() {
        return mustBeConnected;
    }

    public void mustBeConnected(boolean mustBeConnected) {
        this.mustBeConnected = mustBeConnected;
    }

    public boolean mustBeBound() {
        return mustBeBound;
    }

    public void mustBeBound(boolean mustBeBound) {
        this.mustBeBound = mustBeBound;
    }

    public boolean mustBeNotClosed() {
        return mustBeNotClosed;
    }

    public void mustBeNotClosed(boolean mustBeNotClosed) {
        this.mustBeNotClosed = mustBeNotClosed;
    }

    public abstract void setup();

    public abstract DatagramSocket execute(DatagramSocket socket, Consoler out) throws IOException;

}

