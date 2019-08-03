public class LFU implements ScheduleAlgorithm {

    LFU(){}

    public void process(FreeListEntry freeListEntry, int time){
        int minId = -1;
        int minTimeVisited = 1000000;
        for(int i  = 0; i < 100; i++){

            if(FreeList.getFreeListEntry(i).getId() == freeListEntry.getId() &&
                    FreeList.getFreeListEntry(i).getPageNumber() == freeListEntry.getPageNumber()){

                FreeList.getFreeListEntry(i).setLastTimeVisited(time);
                FreeList.getFreeListEntry(i).incrementTimesVisited();
                FreeList.hits();
                return;
            }
            else if(FreeList.getFreeListEntry(i).getTimesVisited() < minTimeVisited){
                minId = i;
                minTimeVisited = FreeList.getFreeListEntry(i).getTimesVisited();
            }
        }

        FreeList.miss();
        FreeList.setFreeListEntry(minId, freeListEntry);
    }
}
