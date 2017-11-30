import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SuitCalendar {

    private List<Event> eventList;
    private StorageManager storageManager;

    public SuitCalendar(StorageManager storageManager){
        this.eventList = new ArrayList<>();
        this.storageManager = storageManager;
    }

    public void loadEventsFromFile() throws IOException {
        eventList.addAll(storageManager.readAllEvents());
    }

    public void addEvent(Event event) throws IOException {
        eventList.add(event);
        storageManager.addEventToFile(event);
    }

    public Event getNearestEvent(){
        Event eventNearest = eventList.get(0);
        for (Event event : eventList) {
            if(eventNearest.getDate().isAfter(event.getDate())){
                eventNearest = event;
            }
        }
        return eventNearest;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void saveAll() throws IOException {
        storageManager.saveAllToFile(eventList);
    }
}
