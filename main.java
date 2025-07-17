import java.util.*;


class Booking {
    String userName;
    String roomType;
    int roomNo;
    long phoneNo;

    Booking(String userName, String roomType, int roomNo, long phoneNo) {
        this.userName = userName;
        this.roomType = roomType;
        this.roomNo = roomNo;
        this.phoneNo = phoneNo;
    }

    public String toString() {
        return "Name: " + userName + ", Phone: " + phoneNo + ", Room No: " + roomNo + ", Type: " + roomType;
    }
}

public class main {
    static ArrayList<Booking> bookings = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello! Welcome to our Room Service");
        int choose = 0;

        while (choose != 4) {
            System.out.println("\nChoose your option:\n1. Book Room\n2. Cancel Booking\n3. View My Booking\n4. Exit");
            choose = scan.nextInt();
            scan.nextLine(); 

            switch (choose) {
                case 1:
                    searchRooms();
                    break;
                case 2:
                    cancelBooking();
                    break;
                case 3:
                    viewMyBooking();
                    break;
                case 4:
                    System.out.println("Thank you for visiting our Hotel");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public static void searchRooms() {
        Scanner scan = new Scanner(System.in);
        String r_type;
        System.out.println("What type of room do you need?");
        System.out.println("\n1. Standard\n2. Deluxe\n3. Suite\nChoose one...");
        int type = scan.nextInt();

        switch (type) {
            case 1:
                System.out.println("Available Standard Rooms:\n201-205 at ₹2500");
                r_type = "Standard";
                break;
            case 2:
                System.out.println("Available Deluxe Rooms:\n206-210 at ₹3000");
                r_type = "Deluxe";
                break;
            case 3:
                System.out.println("Available Suite Rooms:\n211-215 at ₹3500");
                r_type = "Suite";
                break;
            default:
                System.out.println("Invalid type");
                return;
        }

        System.out.print("Enter room number you want to book: ");
        int select = scan.nextInt();
        scan.nextLine(); // clear buffer
        bookRooms(select, r_type);
    }

    public static void bookRooms(int select, String r_type) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scan.nextLine();

        System.out.print("Enter your phone number: ");
        long ph_no = scan.nextLong();
        scan.nextLine(); // clear buffer

        System.out.println("You selected: " + r_type + " Room, Room No: " + select);
        System.out.print("Enter check-in date [YYYY-MM-DD]: ");
        String ch_in = scan.nextLine();

        System.out.print("Enter check-out date [YYYY-MM-DD]: ");
        String ch_out = scan.nextLine();

        System.out.print("Proceed Payment (Y/N): ");
        char pay = scan.next().charAt(0);

        int amount = r_type.equals("Standard") ? 2500 : r_type.equals("Deluxe") ? 3000 : 3500;

        if (pay == 'y' || pay == 'Y') {
            System.out.println("You need to pay: ₹" + amount);
            System.out.print("Enter amount: ");
            int entered = scan.nextInt();

            if (entered >= amount) {
                System.out.println("________________________________________");
                System.out.println("Payment Successful!");
                if (entered > amount) {
                    System.out.println("Balance returned: ₹" + (entered - amount));
                }
                bookings.add(new Booking(name, r_type, select, ph_no));
                System.out.println("Booking Confirmed ");
                 System.out.println("________________________________________");
            } else {
                 System.out.println("________________________________________");
                System.out.println("Insufficient Payment ");
                 System.out.println("________________________________________");
            }
        } else {
             System.out.println("________________________________________");
            System.out.println("Payment Required. Booking not completed.");
             System.out.println("________________________________________");

        }
    }

    public static void cancelBooking() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your phone number to cancel booking: ");
        long phone = scan.nextLong();
        scan.nextLine(); // clear buffer

        boolean found = false;
        Iterator<Booking> iterator = bookings.iterator();

        while (iterator.hasNext()) {
            Booking b = iterator.next();
            if (b.phoneNo == phone) {
                System.out.println("Found Booking: " + b);
                System.out.print("Do you want to cancel this booking? (yes/no): ");
                String confirm = scan.nextLine();

                if (confirm.equalsIgnoreCase("yes")) {
                    iterator.remove();
                    System.out.println("Booking Cancelled Successfully ❌");
                } else {
                    System.out.println("Cancellation Aborted.");
                }
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("No booking found for phone number: " + phone);
        }
    }

    public static void viewMyBooking() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your phone number to view booking: ");
        long phone = scan.nextLong();
        boolean found = false;

        for (Booking b : bookings) {
            if (b.phoneNo == phone) {
                System.out.println("Your Booking: " + b);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No booking found for phone number: " + phone);
        }
    }
}