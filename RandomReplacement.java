import java.util.Random;
public class RandomReplacement implements ScheduleAlgorithm {

    RandomReplacement(){}

    public void process(FreeListEntry freeListEntry, int time){
        Random rand = new Random();
        for(int i  = 0; i < 100; i++){
            if(FreeList.getFreeListEntry(i).getId() == freeListEntry.getId() &&
                    FreeList.getFreeListEntry(i).getPageNumber() == freeListEntry.getPageNumber()){

                FreeList.getFreeListEntry(i).setLastTimeVisited(time);
                FreeList.getFreeListEntry(i).incrementTimesVisited();
                FreeList.hits();
                return;
            }
        }

        FreeList.miss();
        FreeList.setFreeListEntry(rand.nextInt(100), freeListEntry);
    }
}

