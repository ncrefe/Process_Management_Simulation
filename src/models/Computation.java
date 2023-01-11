package models;

import java.util.Random;

public class Computation {
    public int id;
    public IProcess process;
    public int occupation;

    public Computation(int index) {
        this.id = index;

        Random random = new Random();
        int processType = random.nextInt(3) + 1;

        switch (processType) {
            case 1:
                this.process = new LowPriorityProcess();
                break;
            case 2:
                this.process = new NormalPriorityProcess();
                break;
            case 3:
                this.process = new HighPriorityProcess();
                break;
        }

        this.occupation = random.nextInt(10) + 1;
    }

    public String toString() {
        return "P" + id + "," + process.getType() + "," + occupation + "ns";
    }
}
