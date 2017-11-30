import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class StorageManager {
    private File file;

    public StorageManager() throws IOException {
        file = new File(Config.FILE_PATH);

        //Sprawdzamy czy plik istnieje, jesli nie to tworzymy go
        if(!file.exists()){
            file.createNewFile();
        }
    }

    public void addEventToFile(Event event) throws IOException {
        String line = Utils.formatDateToString(event.getDate()) + ":" + event.getDescription() + "\r\n";
        Files.write(file.toPath(), line.getBytes(), StandardOpenOption.APPEND);
    }

    public void saveAllToFile(List<Event> eventList) throws IOException {
        String line;
        for (Event event : eventList) {
             line = Utils.formatDateToString(event.getDate()) + ":" + event.getDescription() + "\r\n";
             Files.write(file.toPath(), line.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
        }
    }

    public List<Event> readAllEvents() throws IOException {
        List<Event> eventList = new ArrayList<>();
        Event event;
            for (String s : Files.readAllLines(file.toPath())) {
                     String[] data = s.split(":");
                     event = new Event(data[0], data[1]);

                     eventList.add(event);
            }
            return eventList;
    }

}
