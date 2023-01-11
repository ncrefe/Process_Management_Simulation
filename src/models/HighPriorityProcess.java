package models;

public class HighPriorityProcess implements IProcess {

    @Override
    public String getType() {
        return "HIGH";
    }

    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    public String toString() {
        return "Type: HIGH, Priority: 3";
    }
}
