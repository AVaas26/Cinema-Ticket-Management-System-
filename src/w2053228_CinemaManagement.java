import java.util.InputMismatchException;
import java.util.Scanner;

// Main class for Cinema Management System
public class w2053228_CinemaManagement {
    public static Scanner scanner = new Scanner(System.in);

    // Array to store tickets
    public static Ticket[] tickets = new Ticket[48];
    public static int ticketCount = 0;

    public static void main(String[] args) {
        int option;

        System.out.println("Welcome to the The London Lumiere");
        // 2D array to represent the seating plan
        int[][] seats = new int[3][16];

        // Display the menu option
        do {
            System.out.println("---------------------------------------------");
            System.out.println("Please select and option");
            System.out.println("---------------------------------------------");
            System.out.println("    1) Buy a seat ");
            System.out.println("    2) Cancel a seat ");
            System.out.println("    3) Show sealing plan ");
            System.out.println("    4) Find first available seat ");
            System.out.println("    5) Print tickets information and total sales ");
            System.out.println("    6) Search ticket ");
            System.out.println("    7) Sort tickets by price ");
            System.out.println("    0) Exit ");
            System.out.println("--------------------------------------------- \n");
            System.out.println("Select an option:");

            // Get valid option from user
            do {
                try {
                    option = scanner.nextInt();
                    if (option >= 0 && option < 8) {
                        break;
                    } else {
                        System.out.println("Please enter a number between (0-8) ");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a valid option ");
                    scanner.nextLine();
                }
            }

            while (true);

            // Handle user's option
            switch (option) {
                case 1: {
                    System.out.println("Buy a Ticket....");
                    buy_ticket(seats);
                    break;
                }
                case 2: {
                    System.out.println("Cancel a Ticket....");
                    cancel_ticket(seats);
                    break;
                }
                case 3: {
                    System.out.println("Show sealing plan....");
                    show_seating_plan(seats);
                    break;
                }
                case 4: {
                    System.out.println("Find first available seat....");
                    Find_FirstAvailable_Seat(seats);
                    break;
                }
                case 5: {
                    System.out.println("Print tickets information and total sales....");
                    printTicketsInformation(seats);
                    break;
                }
                case 6: {
                    System.out.println("Search ticket....");
                    searchTicket(seats);
                    break;
                }
                case 7: {
                    System.out.println("Sort tickets by price....");
                    sort_tickets(seats);
                    break;
                }
            }
        } while (option != 0);
        System.out.println("Goodbye....!");
        System.exit(0);
    }

    // Method to buy a ticket
    public static void buy_ticket(int[][] seats) {
        int row = getValidRow();
        int seat = getValidSeat();
        double price;

        if (seats[row - 1][seat - 1] == 0) {
            seats[row - 1][seat - 1] = 1;
            if (row == 1) {
                price = 12;
            } else if (row == 2) {
                price = 10;
            } else {
                price = 8;
            }
            System.out.println("Please enter your name :");
            String name = scanner.next();

            System.out.println("Please enter your surname :");
            String surname = scanner.next();

            System.out.println("Please enter your email :");
            String email = scanner.next();

            System.out.println("Row " + row + " Seat "+ seat + " has been booked. ");

            Person person = new Person(name, surname, email, price);
            Ticket ticket = new Ticket(row, seat, person);
            tickets[ticketCount] = ticket;
            ticketCount++;

        } else {
            System.out.println("This seat is not available. Please choose another seat.");
        }
    }

    // Method to cancel a ticket
    private static void cancel_ticket(int[][] seats) {
        int row_num;
        int seat_num;

        try {
            row_num = getValidRow();
            seat_num = getValidSeat();

            if (seats[row_num - 1][seat_num - 1] == 1) {
                seats[row_num - 1][seat_num - 1] = 0;
                System.out.println("Row " + row_num + " Seat " + seat_num + " has been cancelled.");

            } else {
                System.out.println("This seat is already available.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter numeric values.");
            scanner.next();
        }
    }

    // Method to show the seating plan
    public static void show_seating_plan(int[][] seats) {
        System.out.println("*********************************");
        System.out.println("             Screen              ");
        System.out.println("*********************************");
        for (int[] seat : seats) {
            for (int i : seat) {
                if (i == 0) {
                    System.out.print("O ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }

    // Method to find the first available seat
    private static void Find_FirstAvailable_Seat(int[][] seats) {
        String row = null;
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == 0) {
                    if (i == 0) {
                        row = "1";
                    } else if (i == 1) {
                        row = "2";
                    } else if (i == 2) {
                        row = "3";
                    }
                    System.out.println("The first available seat is: Row  " + row + ", Seat " + (j + 1));
                    return;
                }
            }
        }
        System.out.println("There are no available seats at the moment.");
    }

    // Method to print tickets information and total sales
    private static void printTicketsInformation(int[][] seats) {

        int candi_count = 1;
        double totalRevenue = 0;

        for (Ticket ticket : tickets) {
            if (ticket != null) {
                System.out.println("personal Details Candidate Count: " + candi_count);
                System.out.println("seat No:" + "Row " + ticket.getRow() + " Seat " + ticket.getSeat());
                System.out.println("Full Name: " + ticket.getPerson().getName() + " " + ticket.getPerson().getSurname());
                System.out.println("Email:" + ticket.getPerson().getEmail());
                System.out.println("Price: £" + ticket.getPerson().getPrice());
                System.out.println();
                totalRevenue += ticket.getPrice();
                candi_count++;
            }
        }
        System.out.println("Total sales revenue: £" + totalRevenue);
    }

    // Method to search for a ticket
    private static void searchTicket(int[][] seats) {
        int row = getValidRow();
        int seat = getValidSeat();

        for (Ticket ticket : tickets) {
            if (ticket != null && ticket.getRow() == row && ticket.getSeat() == seat) {
                // Display ticket information if found
                System.out.println("Ticket Information:\n" +
                        "Name: " + ticket.getPerson().getName() + "\n" +
                        "Surname: " + ticket.getPerson().getSurname() + "\n" +
                        "Email: " + ticket.getPerson().getEmail() + "\n" +
                        "Price: £" + ticket.getPrice());
                return;
            }
        }
        if (seats[row - 1][seat - 1] == 1) {
            System.out.println("Ticket Information: Row " + row + " Seat " + seat);
        } else {
            System.out.println("This seat is already available.");
        }
    }

    // Method to sort tickets by price
    private static void sort_tickets(int[][] seats){
        int value = 0;
        for (int x=0; x<48; x++){
            if(tickets[x] != null){
                int index = x;
                for(int y=x+1; y<48; y++){
                    if(tickets[y] != null){
                        if(tickets[y].getPrice() < tickets[index].getPrice()){
                            index = y;
                        }
                    }
                }
                Ticket temp = tickets[value];
                tickets[value] = tickets[index];
                tickets[index] = temp;
                value++;
            }
        }

        int count = 1;
        for(Ticket ticket : tickets){
            if(ticket != null){
                System.out.println("Personal Details Candidate Count: " + count);
                System.out.println("Seat no: " + "Row " + ticket.getRow() + " Seat " + ticket.getSeat());
                System.out.println("Full name: " + ticket.getPerson().getName() + " " + ticket.getPerson().getSurname());
                System.out.println("Email: " + ticket.getPerson().getEmail());
                System.out.println("Price: £" + ticket.getPerson().getPrice());
                System.out.println();
                count++;
            }
        }
    }



    // Method to get a valid row number from user
    private static int getValidRow() {
        int row;

        while (true) {
            try {
                System.out.println("Enter row number (1-3):");
                row = scanner.nextInt();
                if (row >= 1 && row <= 3) {
                    break;
                } else {
                    System.out.println("Invalid row number. Please enter a row number between 1 and 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter numeric values.");
                scanner.next();
            }
        }
        return row;
    }

    // Method to get a valid seat number from user
    private static int getValidSeat() {
        int seat;

        while (true) {
            try {
                System.out.println("Enter seat number (1-16):");
                seat = scanner.nextInt();
                if (seat >= 1 && seat <= 16) {
                    break;
                } else {
                    System.out.println("Invalid seat number. Please enter a seat number between 1 and 16.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter numeric values.");
                scanner.next();
            }
        }
        return seat;
    }
}
