import java.util.ArrayList;
import java.util.Scanner;

class Train {
    String name;
    String time;
    int passengerStrength;
    int trainNumber;
    ArrayList<String> bookedSeats = new ArrayList<>();

    public Train(String name, String time, int passengerStrength, int trainNumber) {
        this.name = name;
        this.time = time;
        this.passengerStrength = passengerStrength;
        this.trainNumber = trainNumber;
    }

    public boolean bookSeat(String passengerName) {
        if (bookedSeats.size() < passengerStrength) {
            bookedSeats.add(passengerName);
            return true;
        }
        return false;
    }

    public boolean cancelSeat(String passengerName) {
        return bookedSeats.remove(passengerName);
    }

    public int getAvailableSeats() {
        return passengerStrength - bookedSeats.size();
    }

    public void displayBookedPassengers() {
        if (bookedSeats.isEmpty()) {
            System.out.println("No tickets booked yet.");
        } else {
            for (String passenger : bookedSeats) {
                System.out.println(passenger);
            }
        }
    }
}

class ReservationSystem {
    private ArrayList<Train> availableTrains = new ArrayList<>();

    public ReservationSystem() {
        availableTrains.add(new Train("Dhaka - Chottogram", "13:05", 50, 1010));
        availableTrains.add(new Train("Dhaka - Rongpur", "7:00", 50, 2013));
        availableTrains.add(new Train("Rajshahi - Dhaka", "10:00", 50, 3045));
    }

    public void displayAvailableTrains() {
        System.out.println("Available Trains:");
        System.out.println("Train Name\t\tTime\tPassenger Strength\tTrain Number");
        for (Train train : availableTrains) {
            System.out.println(train.name + "\t" + train.time + "\t" + train.passengerStrength + "\t\t\t" + train.trainNumber);
        }
    }

    public void checkSeatAvailability(int trainNumber) {
        Train train = findTrainByNumber(trainNumber);
        if (train != null) {
            System.out.println("Available seats on Train " + train.trainNumber + ": " + train.getAvailableSeats());
        } else {
            System.out.println("Train not found.");
        }
    }

    public void bookTicket(int trainNumber, String passengerName) {
        Train train = findTrainByNumber(trainNumber);
        if (train != null) {
            if (train.bookSeat(passengerName)) {
                System.out.println("Ticket booked successfully for " + passengerName);
            } else {
                System.out.println("Sorry, the train is fully booked.");
            }
        } else {
            System.out.println("Train not found.");
        }
    }

    public void cancelTicket(int trainNumber, String passengerName) {
        Train train = findTrainByNumber(trainNumber);
        if (train != null) {
            if (train.cancelSeat(passengerName)) {
                System.out.println("Ticket canceled successfully for " + passengerName);
            } else {
                System.out.println("Passenger not found or no booking exists for this passenger.");
            }
        } else {
            System.out.println("Train not found.");
        }
    }

    public void displayBookedTickets(int trainNumber) {
        Train train = findTrainByNumber(trainNumber);
        if (train != null) {
            System.out.println("Booked Tickets for Train " + train.trainNumber + ":");
            train.displayBookedPassengers();
        } else {
            System.out.println("Train not found.");
        }
    }

    private Train findTrainByNumber(int trainNumber) {
        for (Train train : availableTrains) {
            if (train.trainNumber == trainNumber) {
                return train;
            }
        }
        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        ReservationSystem reservationSystem = new ReservationSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nRailway Reservation System Menu:");
            System.out.println("1. Display Available Trains");
            System.out.println("2. Check Seat Availability");
            System.out.println("3. Book a Ticket");
            System.out.println("4. Cancel a Ticket");
            System.out.println("5. Display Booked Tickets");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    reservationSystem.displayAvailableTrains();
                    break;
                case 2:
                    System.out.print("Enter Train Number: ");
                    int checkTrainNumber = scanner.nextInt();
                    reservationSystem.checkSeatAvailability(checkTrainNumber);
                    break;
                case 3:
                    System.out.print("Enter Train Number: ");
                    int bookTrainNumber = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter Passenger Name: ");
                    String passengerName = scanner.nextLine();
                    reservationSystem.bookTicket(bookTrainNumber, passengerName);
                    break;
                case 4:
                    System.out.print("Enter Train Number: ");
                    int cancelTrainNumber = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter Passenger Name: ");
                    String cancelPassenger = scanner.nextLine();
                    reservationSystem.cancelTicket(cancelTrainNumber, cancelPassenger);
                    break;
                case 5:
                    System.out.print("Enter Train Number: ");
                    int displayTrainNumber = scanner.nextInt();
                    reservationSystem.displayBookedTickets(displayTrainNumber);
                    break;
                case 6:
                    System.out.println("Exiting Railway Reservation System. Thank you!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}