public class FreeList {
    private final int SIZE = 100;
    private static int hits = 0;
    private static int misses = 0;
    static FreeListEntry [] freeListEntries = new FreeListEntry[100];

    public FreeList(){
        clear();
    }

    public static void clear(){
        for(int i = 0; i < 100; i++){
            freeListEntries[i] = new FreeListEntry();
        }
        hits = 0;
        misses = 0;
    }

    public static void clear(int id){
        for(int i = 0; i < 100; i++){
            if(freeListEntries[i].getId() == id){
                freeListEntries[i] = new FreeListEntry();
            }
        }
    }

    public static int countFreeEntries(){
        int count = 0;
        for(int i = 0; i < 100; i++){
            if(freeListEntries[i].getId() == -1){
                count++;
            }
        }
        return count;
    }

    public static int firstAvailableEntry(){
        for(int i = 0; i < 100; i++){
            if(freeListEntries[i].getId() == -1){
                return i;
            }
        }
        return -1;
    }

    public static FreeListEntry getFreeListEntry(int i){
        return freeListEntries[i];
    }

    public static void setFreeListEntry(int i, FreeListEntry freeListEntry){
        freeListEntries[i] = freeListEntry;
    }

    public static void miss(){
        misses++;
    }

    public static void hits(){
        hits++;
    }

    public static int getMisses(){
        return misses;
    }

    public static int getHits(){
        return hits;
    }

}
