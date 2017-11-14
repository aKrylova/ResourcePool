package org.suai.logaccess;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.suai.tourist.Tourist;

public class LogAccess {
    private int countComp;
    private LinkedList<Tourist> listTourist;
    private Map<String,Tourist> fillComputers;
    private QueueThread [] threads;
    private Logger log;

    public LogAccess(int comp, int tour){
        log = Logger.getLogger(QueueThread.class.getName());
        countComp = comp;
        listTourist = new LinkedList<>();
        fillComputers = new HashMap<>();
        threads = new QueueThread[countComp];
        for(int i = 0; i < tour; i++){
            listTourist.addLast(new Tourist(i, ThreadLocalRandom.current().nextInt(1,180/tour)));
        }
        Iterator<Tourist> it = listTourist.iterator();
        for (Integer j = 0; j < countComp; j++){
            Tourist cur = it.next();
            fillComputers.put("Comp"+j.toString(), cur);
            log.log(Level.INFO, "\n\nTourist " + cur.getId() + " is online.\n");
        }
        for(int i = 0; i < countComp; i++) {
            listTourist.removeFirst();
        }
        this.startPlaced();
    }

    private void startPlaced(){
        for(int i = 0; i < countComp; i++){
            threads[i] = new QueueThread(listTourist, fillComputers, fillComputers.get("Comp"+i));
        }
        for(int i = 0; i < countComp; i++){
            threads[i].start();
        }
        for(int i = 0; i <  countComp; i++){
            try {
                threads[i].join();
                log.log(Level.INFO, "{0}", threads[i].getResult().toString());
            } catch (InterruptedException e) {
                log.log(Level.SEVERE, "Exception: ", e);
                Thread.currentThread().interrupt();
            }
        }
        log.log(Level.INFO, "\n\nThe place is empty, let's close up and go to the beach!\n");
    }

}
