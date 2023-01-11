package models;

public class LowPriorityProcess implements IProcess {

    @Override
    public String getType() {
        return "LOW";
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public String toString() {
        return "Type: LOW, Priority: 1";
    }
}
