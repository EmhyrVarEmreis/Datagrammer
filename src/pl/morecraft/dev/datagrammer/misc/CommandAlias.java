package pl.morecraft.dev.datagrammer.misc;

public class CommandAlias {

    private String value;
    private boolean isEqual;

    public CommandAlias(String value, boolean isEqual) {
        this.setValue(value);
        this.setEqual(isEqual);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isEqual() {
        return isEqual;
    }

    public void setEqual(boolean equal) {
        isEqual = equal;
    }

    @Override
    public String toString() {
        return value;
    }

}
