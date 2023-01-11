package models;

public class ProcessQueue {
    public int simulatorIndex;
    public Computation[] queue;
    public int topIndex;
    public int length;

    public ProcessQueue(int simulatorIndex, int length) {
        this.simulatorIndex = simulatorIndex;
        this.length = length;
        this.queue = new Computation[length];
        this.topIndex = -1;

        for (int index = 0; index < length; index++) {
            Computation computation = new Computation(index);
            this.push(computation);
        }
    }

    public void push(Computation newEntry) {
        // 3 HIGH
        // 2 NORMAL
        // 1 LOW
        int priority = newEntry.process.getPriority();

        if (priority == 1) {
            queue[this.topIndex + 1] = newEntry;
        } else if (priority == 2) {
            Computation[] tempProcessQueue = new Computation[this.length];
            boolean isDone = false;

            for (int index = 0; index < this.topIndex + 1; index++) {

                if (isDone) {
                    tempProcessQueue[index + 1] = this.queue[index];
                } else if (priority > this.queue[index].process.getPriority()) {
                    tempProcessQueue[index] = newEntry;
                    tempProcessQueue[index + 1] = this.queue[index];
                    isDone = true;
                } else {
                    tempProcessQueue[index] = this.queue[index];
                }
            }

            if (!isDone) {
                tempProcessQueue[this.topIndex + 1] = newEntry;
            }

            this.queue = tempProcessQueue;
        } else {
            Computation[] tempProcessQueue = new Computation[length];
            boolean isDone = false;

            for (int index = 0; index < this.topIndex + 1; index++) {

                if (isDone) {
                    tempProcessQueue[index + 1] = this.queue[index];
                } else if (priority > this.queue[index].process.getPriority()) {
                    tempProcessQueue[index] = newEntry;
                    tempProcessQueue[index + 1] = this.queue[index];
                    isDone = true;
                } else {
                    tempProcessQueue[index] = this.queue[index];
                }
            }

            if (!isDone) {
                tempProcessQueue[this.topIndex + 1] = newEntry;
            }

            this.queue = tempProcessQueue;
        }
        this.topIndex++;
    }

    public Object pop() {
        Object popEntry = this.queue[0];
        this.queue[0] = null;
        for (int i = 0; i < this.topIndex; i++) {
            this.queue[i] = this.queue[i + 1];
        }
        this.topIndex--;
        return popEntry;
    }

    public String printProcessQueue() {
        String str = "";

        for (int index = 0; index < this.topIndex + 1; index++) {
            str += this.queue[index];
            if (index != this.topIndex) {
                str += " ← ";
            }
        }

        return str;
    }

    public void print() {
        int totalHighComputations = 0;
        int totalNormalComputations = 0;
        int totalLowComputations = 0;

        double totalWaitingTime = 0;
        double totalWaitingTimeHigh = 0;
        double totalWaitingTimeNormal = 0;
        double totalWaitingTimeLow = 0;

        for (int index = 0; index < this.topIndex + 1; index++) {
            // computation counts
            if (this.queue[index].process.getPriority() == 1) {
                totalLowComputations++;
            } else if (this.queue[index].process.getPriority() == 2) {
                totalNormalComputations++;
            } else if (this.queue[index].process.getPriority() == 3) {
                totalHighComputations++;
            }

            // computation waiting times
            if (index != this.topIndex) {
                totalWaitingTime += this.queue[index].occupation;

                if (this.queue[index].process.getPriority() == 1 && this.queue[index + 1].process.getPriority() == 1) {
                    totalWaitingTimeLow++;
                }
                if (this.queue[index].process.getPriority() == 2 && this.queue[index + 1].process.getPriority() == 2) {
                    totalWaitingTimeNormal++;
                }
                if (this.queue[index].process.getPriority() == 3 && this.queue[index + 1].process.getPriority() == 3) {
                    totalWaitingTimeHigh++;
                }
            }
        }

        if (totalWaitingTimeNormal != 0)
            totalWaitingTimeNormal += totalWaitingTimeHigh + 1;
        if (totalWaitingTimeLow != 0)
            totalWaitingTimeLow += totalWaitingTimeNormal + 1;

        System.out.println("Simulation Number: " + this.simulatorIndex);
        System.out.println("Computation Queue: " + this.printProcessQueue());
        System.out.println("Total numbers of computations: " + this.length);
        System.out.println();

        System.out.println("Total waiting time:   " + totalWaitingTime);
        System.out.println("Average waiting time: " + (totalWaitingTime / this.length));
        System.out.println();

        System.out.println("Total number of computations for High:   " + totalHighComputations);
        System.out.println("Total number of computations for Normal: " + totalNormalComputations);
        System.out.println("Total number of computations for Low:    " + totalLowComputations);
        System.out.println();

        System.out.println("Total waiting time for High:     " + totalWaitingTimeHigh);
        System.out.println("Average waiting time for High:   "
                + (totalHighComputations == 0 ? 0 : (totalWaitingTimeHigh / totalHighComputations)));
        System.out.println();
        System.out.println("Total waiting time for Normal:   " + totalWaitingTimeNormal);
        System.out.println("Average waiting time for Normal: "
                + (totalNormalComputations == 0 ? 0 : (totalWaitingTimeNormal / totalNormalComputations)));
        System.out.println();
        System.out.println("Total waiting time for Low:      " + totalWaitingTimeLow);
        System.out.println("Average waiting time for Low:    "
                + (totalLowComputations == 0 ? 0 : (totalWaitingTimeLow / totalLowComputations)));
        System.out.println();
        System.out.println();
    }
}
