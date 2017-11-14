package org.suai.logaccess;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.suai.tourist.Tourist;

public class QueueThread extends Thread{
    private final LinkedList<Tourist> listTourist;
    private Map<String,Tourist> fillComputers;
    private Tourist curT;
    private Logger log;

    QueueThread(List<Tourist> list, Map<String,Tourist> comps, Tourist curTor){
        listTourist = (LinkedList<Tourist>) list;
        fillComputers = comps;
        curT = curTor;
        log = Logger.getLogger(QueueThread.class.getName());
    }

    @Override
    public void run(){
        try {
            TimeUnit.SECONDS.sleep(curT.getTime());
            log.log(Level.INFO, "\n\nTourist " + curT.getId() + " is done, having spent " + curT.getTime()  + " minutes online.\n");
            synchronized (listTourist){
                if(!listTourist.isEmpty()){
                    for (Object o : fillComputers.entrySet()) {
                        Map.Entry entry = (Map.Entry) o;
                        if (entry.getValue() == curT) {
                            curT = listTourist.getFirst();
                            fillComputers.put(entry.getKey().toString(), curT);
                            log.log(Level.INFO, "\n\nTourist " + curT.getId() + " is online.\n");
                            break;
                        }
                    }
                }
                if(!listTourist.isEmpty()) {
                    listTourist.removeFirst();
                    this.run();
                }
                //todo syncronized blocks vs method
            }

        } catch (InterruptedException e) {
            log.log(Level.SEVERE, "Exception: ", e);
            Thread.currentThread().interrupt();
        }
    }

    List<Tourist> getResult(){
        return listTourist;
    }
}
