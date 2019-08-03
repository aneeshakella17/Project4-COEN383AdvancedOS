public class MRU implements ScheduleAlgorithm {

    MRU(){}

    public void process(FreeListEntry freeListEntry, int time){
        int maxId = -1;
        int maxTimeVisited = -1;
        for(int i  = 0; i < 100; i++){

            if(FreeList.getFreeListEntry(i).getId() == freeListEntry.getId() &&
                    FreeList.getFreeListEntry(i).getPageNumber() == freeListEntry.getPageNumber()){

                FreeList.getFreeListEntry(i).setLastTimeVisited(time);
                FreeList.getFreeListEntry(i).incrementTimesVisited();
                FreeList.hits();
                return;
            }
            else if(FreeList.getFreeListEntry(i).getLastTimeVisited() > maxTimeVisited){
                maxId = i;
                maxTimeVisited = FreeList.getFreeListEntry(i).getLastTimeVisited();
            }
        }

        FreeList.miss();
        FreeList.setFreeListEntry(maxId, freeListEntry);
    }
}

