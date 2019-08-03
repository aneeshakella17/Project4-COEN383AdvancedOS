public class FIFO implements ScheduleAlgorithm {

    FIFO(){}

    public void process(FreeListEntry freeListEntry, int time){
        int minId = -1;
        int minTimeVisited = 100000000;
        for(int i  = 0; i < 100; i++){
            if(FreeList.getFreeListEntry(i).getId() == freeListEntry.getId() &&
                    FreeList.getFreeListEntry(i).getPageNumber() == freeListEntry.getPageNumber()){
                FreeList.getFreeListEntry(i).setLastTimeVisited(time);
                FreeList.hits();
                return;
            }
            else if(FreeList.getFreeListEntry(i).getFirstTimeVisited() < minTimeVisited){
                minId = i;
                minTimeVisited = FreeList.getFreeListEntry(i).getFirstTimeVisited();
            }
        }

        FreeList.miss();
        FreeList.setFreeListEntry(minId, freeListEntry);
    }
}
