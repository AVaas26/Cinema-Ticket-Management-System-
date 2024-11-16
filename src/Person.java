public class Person {
    private String name;
    private String surname;
    private String email;
    private double price;

    // Constructor to initialize the Person object
    public Person(String name, String surname, String email, double price) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public double getPrice() {
        return price;
    }
}