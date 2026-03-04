public class Event {

    private String eventId;
    private String name;
    private double price;
    private int availableTickets;
    private boolean isVirtual;
    private String location;
    private String accessCode;
    private EventCategory category;
    private User creator;

    public Event(String name, double price, int tickets, boolean isVirtual,
                 String location, EventCategory category, User creator) {

        this.eventId = IdGenerator.generateEventId();
        this.name = name;
        this.price = price;
        this.availableTickets = tickets;
        this.isVirtual = isVirtual;
        this.location = location;
        this.category = category;
        this.creator = creator;

        if (isVirtual) {
            this.accessCode = IdGenerator.generateAccessCode();
        }
    }

    public boolean purchase(int quantity) {
        if (quantity > 0 && quantity <= availableTickets) {
            availableTickets -= quantity;
            return true;
        }
        return false;
    }

    public String getEventId() { return eventId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getAvailableTickets() { return availableTickets; }
    public boolean isVirtual() { return isVirtual; }
    public String getLocation() { return location; }
    public String getAccessCode() { return accessCode; }
    public EventCategory getCategory() { return category; }
    public User getCreator() { return creator; }
}
