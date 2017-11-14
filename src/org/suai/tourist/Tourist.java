package org.suai.tourist;

public class Tourist {
    private int id;
    private int time;

    public Tourist(int i, int t){
        id = i;
        time = t;
    }

    public int getId(){
        return id;
    }

    public int getTime(){
        return time;
    }

    public boolean equals(Object obj){
        if (!(obj instanceof Tourist)) {
            return false;
        }
        Tourist t = (Tourist) obj;
        return ((this.getId() == t.getId()) && (this.getTime() == t.getTime()));
    }

    @Override
    public int hashCode() { return 0; }
}
