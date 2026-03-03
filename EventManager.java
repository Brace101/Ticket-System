import java.util.ArrayList;

public class EventManager {

    private ArrayList<Event> events = new ArrayList<>();

    public ArrayList<Event> getEvents() { return events; }

    public void createEvent(User user, String name, double price, int tickets,
                            boolean isVirtual, String location, EventCategory category) {

        if (!user.getRole().equals("CREATOR")) {
            System.out.println("\nAccess Denied: Only creators can create events.");
            return;
        }

        Event event = new Event(name, price, tickets, isVirtual, location, category, user);
        events.add(event);
        System.out.println("\nEvent Created Successfully. Event ID: " + event.getEventId());
    }

    public void deleteEvent(User user, String eventId) {

        if (!user.getRole().equals("CREATOR")) {
            System.out.println("\nAccess Denied: Only creators can delete events.");
            return;
        }

        Event toDelete = null;
        for (Event event : events) {
            if (event.getEventId().equalsIgnoreCase(eventId)) {
                toDelete = event;
                break;
            }
        }

        if (toDelete != null) {
            events.remove(toDelete);
            System.out.println("\nEvent deleted successfully.");
        } else {
            System.out.println("\nEvent not found.");
        }
    }
}
