import java.util.Random;

public class Process {
    static int counter = 0;
    private int id;
    private int arrivalTime;
    private int size;
    private int lastPage;
    private int timeRemaining;


    private int[] potential_sizes = {5, 11, 17, 31};
    private int[] potentialTimes = {1,2,3,4,5};


    Random rand = new Random();

    Process(int arrivalTime){
        this.id = counter++;
        this.arrivalTime = arrivalTime;
        this.size = potential_sizes[rand.nextInt(4)];
        this.lastPage = -1;
        this.timeRemaining = potentialTimes[rand.nextInt(5)];
    }

    public void decrementTime(){
        this.timeRemaining--;
    }

    public void setLastPage(int lastPage){
        this.lastPage = lastPage;
    }

    public int getTimeRemaining(){
        return this.timeRemaining;
    }

    public int getId(){
        return this.id;
    }

    public static void resetCounter(){
        counter = 0;
    }

    public int generateNextPage(){
        int choice = rand.nextInt(12);
        if(this.lastPage == 0){
            if(choice <= 9){
                this.lastPage += rand.nextInt(1);
            } else{
                this.lastPage = rand.nextInt(this.size);
            }
            return this.lastPage;
        }

        if(choice >= 0 && choice <= 2){
            this.lastPage = this.lastPage - 1;
        } else if (choice >= 3 && choice <= 5){
            return this.lastPage;
        } else if (choice >= 6 && choice <= 9){
            this.lastPage = this.lastPage + 1;
        } else {
            this.lastPage = rand.nextInt(this.size);
        }
        return this.lastPage;
    }

}





