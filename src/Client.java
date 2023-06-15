import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    
    public Database db = new Database();

    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Welcome to Lyfter! Please enter your name to begin: ");
            String name = scanner.nextLine();
            Rider rider = new Rider(name);
            System.out.println("Hello " + rider.getName() + ", where are you located? (Please provide and x and y cooridate between 1-1000 with a space between)");
            String[] parts = scanner.nextLine().split(" ");
            if (((Integer.valueOf(parts[0]) < 1 || (Integer.valueOf(parts[0]) > 1000)) || (Integer.valueOf(parts[1]) < 1 || (Integer.valueOf(parts[1]) > 1000)))) {
                System.out.println("Invalid location input");
                System.exit(0);
            }
            rider.changeX(Integer.valueOf(parts[0]));
            rider.changeY(Integer.valueOf(parts[1]));
            System.out.println("So, you are located at " + rider.getX() + "," + rider.getY() + " correct?");
            String decision = scanner.nextLine().toLowerCase();
            if (decision.equals("Yes")) {
                System.out.println("Looking for the driver closest to you: ");
            } else if (decision.equals("No")) {
                System.out.println("Please review your information and try again.");
                System.exit(0);
            } else {
                System.out.println("Invalid input (Yes or No required)");
                start();
            }
            db.populateNames();
            db.populateDB();
            Driver yourDriver = db.getClosest(rider.getX(), rider.getY());
            int distance = db.calculateMin(rider.getX(), yourDriver.getX(), rider.getY(), yourDriver.getY());
            System.out.println(yourDriver.getName() + " at a position of " + yourDriver.getX() + "," + yourDriver.getY() + " at a distance of " + distance + ", or in miles " + distance/25);
            System.out.println("Please enter your destination with a space in between: ");
            String[] destination = scanner.nextLine().split(" ");
            distance = db.calculateMin(rider.getX(), Integer.valueOf(destination[0]), rider.getY(), Integer.valueOf(destination[1]));
            System.out.println("The price of the ride is $" + db.getPrice(distance) + ", with a distance of " + distance/50 + " miles.");
            System.out.println("Do you confirm this ride?");
            decision = scanner.nextLine().toLowerCase();
            if (decision.equals("Yes")) {
                System.out.println("Thank you for using Lyfter, enjoy your ride!");
            } else if (decision.equals("No")) {
                System.out.println("Sorry to hear that, enjoy your day!");
            } else {
                System.out.println("Invalid input (Yes or No required)");
                start();
            }
        } 
        
        catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
