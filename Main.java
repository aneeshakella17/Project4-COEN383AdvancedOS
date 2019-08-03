import java.util.ArrayList;

public class Main {
    static ArrayList<Process> runningList = new ArrayList<>();
    static ArrayList<Process> waitingList = new ArrayList<>();
    static ArrayList<Process> completedList = new ArrayList<>();
    public static void main(String args[]){
        ScheduleAlgorithm lru = new LRU();
        System.out.println("LRU Hits/Misses:");
        System.out.println(getAverage(lru));
        System.out.println(" ");

        ScheduleAlgorithm random = new RandomReplacement();
        System.out.println(" ");
        System.out.println("Random Hits/Misses:");
        System.out.println(getAverage(random));

        ScheduleAlgorithm fifo = new FIFO();
        System.out.println(" ");
        System.out.println("FIFO Hits/Misses:");
        System.out.println(getAverage(fifo));

        ScheduleAlgorithm mfu = new MFU();
        System.out.println(" ");
        System.out.println("MFU Hits/Misses:");
        System.out.println(getAverage(mfu));

        ScheduleAlgorithm lfu = new LFU();
        System.out.println(" ");
        System.out.println("LFU Hits/Misses:");
        System.out.println(getAverage(lfu));
    }

    public static double getAverage(ScheduleAlgorithm algorithm) {
        double sum = 0;
        for(int i = 0; i < 5; i++){
            sum += start(algorithm);
        }
        return sum/5;
    }

    public static void setup(){
        int entryNum = 0;
        Process.resetCounter();
        for(int i = 0; i < 25; i++){
            Process p = new Process(0);
            for(int j = 0; j < 4 ; j++){
                p.setLastPage(0);
                FreeListEntry freeListEntry = new FreeListEntry(i, j, 0);
                FreeList.setFreeListEntry(entryNum, freeListEntry);
                entryNum++;
            }
            runningList.add(p);
        }
    }

    public static double start(ScheduleAlgorithm algorithm){
        for(int i = 0; i < 1000; i++) {

            if (i == 0) {
                setup();
            } else if (i < 125) {
                Process p = new Process(i);
                waitingList.add(p);
            }

            if(waitingList.size() > 0 && FreeList.countFreeEntries() >= 4){
                Process added_process = waitingList.remove(0);
                added_process.setLastPage(0);
                int index = FreeList.firstAvailableEntry();
                FreeListEntry fle = new FreeListEntry(added_process.getId(),0, i);
                FreeList.setFreeListEntry(index, fle);
                runningList.add(added_process);
            }

            ArrayList<Process> toRemove = new ArrayList<>();
            for (Process p : runningList) {
                p.decrementTime();
                int lastPage  = p.generateNextPage();
                FreeListEntry fle = new FreeListEntry(p.getId(), lastPage, i);
                p.setLastPage(lastPage);
                algorithm.process(fle, i);
                if (p.getTimeRemaining() == 0) {
                    FreeList.clear(p.getId());
                    toRemove.add(p);
                }
            }

            for (Process p: toRemove){
                runningList.remove(p);
                completedList.add(p);
            }
        }

        int hits = FreeList.getHits();
        int misses = FreeList.getMisses();
//        System.out.println(hits);
//        System.out.println(misses);
//        System.out.println(completedList.size());
        double toRet = (double) hits/misses;
        FreeList.clear();
        runningList.clear();
        waitingList.clear();
        completedList.clear();
        return toRet;
    }

}

