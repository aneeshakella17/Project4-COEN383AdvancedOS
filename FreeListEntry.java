public class FreeListEntry {
    private int id;
    private int pageNumber;
    private int firstTimeVisited;
    private int lastTimeVisited;
    private int timesVisited;


    FreeListEntry(){
        this.id = -1;
        this.pageNumber = -1;
        this.firstTimeVisited = -1;
        this.lastTimeVisited = -1;
        this.timesVisited = 0;
    }

    FreeListEntry(int id, int pageNumber, int timeVisited){
        this.id = id;
        this.pageNumber = pageNumber;
        this.firstTimeVisited = timeVisited;
        this.lastTimeVisited = timeVisited;
        this.timesVisited = 0;
    }

    public void setLastTimeVisited(int timeVisited){
        this.lastTimeVisited = timeVisited;
    }

    public int getFirstTimeVisited(){
        return this.lastTimeVisited;
    }

    public int getLastTimeVisited(){
        return this.lastTimeVisited;
    }

    public int getId(){
        return this.id;
    }

    public int getPageNumber(){
        return this.pageNumber;
    }

    public void incrementTimesVisited(){
        this.timesVisited += 1;
    }

    public int getTimesVisited(){
        return timesVisited;
    }
}
