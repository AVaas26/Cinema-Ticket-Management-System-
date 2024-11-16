public class Ticket {
    private int row;
    private int seat;
    private Person person;
    private double price;

    // Constructor to initialize the Ticket object
    public Ticket(int row, int seat, Person person) {
        this.row = row;
        this.seat = seat;
        this.person = person;
        this.price = person.getPrice();
    }

    public int getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public Person getPerson() {
        return person;
    }

    public double getPrice() {
        return price;
    }
}