package models;

public class NormalPriorityProcess implements IProcess {

    @Override
    public String getType() {
        return "NORMAL";
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public String toString() {
        return "Type: NORMAL, Priority: 2";
    }
}
