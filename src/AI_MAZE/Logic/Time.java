package AI_MAZE.Logic;

public class Time {
    
    private int sec;
    private int min;
    
    public Time(int _sec, int _min) {
        this.sec = _sec;
        this.min = _min;
    }
    
    public int getSec() {
        return this.sec;
    }
    
    public int getMin() {
        return this.min;
    }
    
     public void setSec(int _sec) {
        this.sec = _sec;
    }
    
    public void setMin(int _min) {
        this.min = _min;
    }
}
