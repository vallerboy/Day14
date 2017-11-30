import java.io.IOException;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private SuitCalendar suitCalendar;

    public Menu(){
        this.scanner = new Scanner(System.in);

        createStorage();
        loadEvents();
    }

    private void createStorage() {
        //Tworzymy storage manager w pliku
        StorageManager storageManager = null;
        try {
            storageManager = new StorageManager();
        } catch (IOException e) {
            System.out.println("Błąd podczas tworzenia pliku");
            System.exit(0);
        }

        this.suitCalendar = new SuitCalendar(storageManager);
    }

    private void loadEvents() {
        try {
            suitCalendar.loadEventsFromFile();
        } catch (IOException e) {
            System.out.println("Błąd podczas ładowania z pliku");
        }
    }

    public void start(){
        while (true) {
            printMenu();
        }
    }

    private void printMenu() {
            System.out.println("1. Dodaj datę");
            System.out.println("2. Pokaż najbliższe wydarzenie");
            System.out.println("3. Pokaż wszystkie");
            System.out.println("4. Wyjście");

            System.out.print("Wybór: ");
            String answer = scanner.nextLine();

            parseAnswer(answer);
    }

    private void parseAnswer(String answer) {
        switch (answer) {
            case "1":
                addDate();
                break;
            case "2":
                printNearestEvent();
                break;
            case "3":
                printAllEvents();
                break;
            case "4":
                System.exit(0);
                break;
            default:
                System.out.println("Brak takiej komendy!");
        }
    }

    private void printAllEvents() {
        for (Event event : suitCalendar.getEventList()) {
            System.out.println(event);
        }
    }

    private void printNearestEvent() {
        Event event = suitCalendar.getNearestEvent();

        System.out.println("Nadchodzace wydarzenie to: ");
        System.out.println(event.getDescription());
        System.out.println("---- " + Utils.formatDateToString(event.getDate()) + " -----");
    }

    private void addDate() {
        System.out.print("Podaj date <rrrr.mm.dd>: ");
        String date = scanner.nextLine();
        System.out.println("Podaj opis: ");
        String desc = scanner.nextLine();

        if(!Utils.isDateValid(date)) {
            System.out.println("Błędny format daty!");
        }
        try {
            suitCalendar.addEvent(new Event(date, desc));
        } catch (IOException e) {
            System.out.println("Błąd podczas zapisu");
        }
    }
}
