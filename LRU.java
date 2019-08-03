public class LRU implements ScheduleAlgorithm {

    LRU(){}

    public void process(FreeListEntry freeListEntry, int time){
        int minId = -1;
        int minTimeVisited = 100000000;
        for(int i  = 0; i < 100; i++){

            if(FreeList.getFreeListEntry(i).getId() == freeListEntry.getId() &&
                    FreeList.getFreeListEntry(i).getPageNumber() == freeListEntry.getPageNumber()){

                FreeList.getFreeListEntry(i).setLastTimeVisited(time);
                FreeList.getFreeListEntry(i).incrementTimesVisited();
                FreeList.hits();
                return;
            }
            else if(FreeList.getFreeListEntry(i).getLastTimeVisited() <= minTimeVisited){
                minId = i;
                minTimeVisited = FreeList.getFreeListEntry(i).getLastTimeVisited();
            }
        }

        FreeList.miss();
        FreeList.setFreeListEntry(minId, freeListEntry);
    }
}
