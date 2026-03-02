public class Event {

    private String eventId;
    private String name;
    private double price;
    private int availableTickets;
    private boolean isVirtual;

    public Event(String eventId, String name,
                 double price, int tickets, boolean isVirtual) {
        this.eventId = eventId;
        this.name = name;
        this.price = price;
        this.availableTickets = tickets;
        this.isVirtual = isVirtual;
    }

    public String getEventId() { return eventId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public boolean isVirtual() { return isVirtual; }
    public int getAvailableTickets() { return availableTickets; }

    public boolean purchase(int quantity) {
        if (quantity > 0 && quantity <= availableTickets) {
            availableTickets -= quantity;
            return true;
        }
        return false;
    }
}
