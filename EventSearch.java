import java.util.ArrayList;

public class EventSearch {

    private ArrayList<Event> events;

    public EventSearch(ArrayList<Event> events) {
        this.events = events;
    }

    public ArrayList<Event> searchByCategory(EventCategory category) {
        ArrayList<Event> results = new ArrayList<>();
        for (Event event : events) {
            if (event.getCategory() == category) {
                results.add(event);
            }
        }
        return results;
    }

    public void displayTable(ArrayList<Event> eventList) {
        if (eventList.isEmpty()) {
            System.out.println("No events found.");
            return;
        }

        System.out.println("---------------------------------------------------------------------");
        System.out.printf("%-8s %-15s %-12s %-10s %-12s %-10s%n",
                "ID", "NAME", "CATEGORY", "PRICE", "TICKETS", "TYPE");
        System.out.println("---------------------------------------------------------------------");

        for (Event event : eventList) {
            String type = event.isVirtual() ? "Virtual" : "Physical";
            System.out.printf("%-8s %-15s %-12s ₦%-9.2f %-12d %-10s%n",
                    event.getEventId(), event.getName(), event.getCategory(),
                    event.getPrice(), event.getAvailableTickets(), type);
        }
        System.out.println("---------------------------------------------------------------------");
    }
}
