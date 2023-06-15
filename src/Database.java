import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {
    
    private ArrayList<Driver> drivers;
    private ArrayList<String> names; 
    private ArrayList<Rider> riders;

    public Database() {
        drivers = new ArrayList<>();
        names = new ArrayList<>();
        riders = new ArrayList<>();
    }
    
    public void populateNames() {
        try (Scanner scanner = new Scanner(Paths.get("src/Names.txt"))) {
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                names.add(row);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void populateDB() {
        for (int i = 0; i < names.size(); i++) {
            drivers.add(new Driver(randomNumber(), randomNumber(), names.get(i)));
        }
    }

    public int randomNumber() {
        return (int) (Math.random()*1000 + 1);
    }

    public void printDB() {
        for (int i = 0; i < drivers.size(); i++) {
            System.out.println(drivers.get(i));        
        }
    }

    public Driver getClosest(int x, int y) {
        int min = Integer.MAX_VALUE;
        Driver minDistance = drivers.get(0); 

        for(int i = 0; i < drivers.size(); i++) {
            if (min > calculateMin(x, drivers.get(i).getX(), y, drivers.get(i).getY())) {
                min = calculateMin(x, drivers.get(i).getX(), y, drivers.get(i).getY());
                minDistance = drivers.get(i);
            }
        }

        return minDistance;
    }

    public int calculateMin(int x1, int x2, int y1, int y2) {
        int x = x2-x1;
        int y = y2-y1;
        return (int)Math.sqrt((x * x) + (y * y));
    }

    public int getMin(int x, int y) {
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < drivers.size(); i++) {
            if (min > calculateMin(x, drivers.get(i).getX(), y, drivers.get(i).getY())) {
                min = calculateMin(x, drivers.get(i).getX(), y, drivers.get(i).getY());
            }
        }
        
        return min;
    }

    public double getPrice(int distance) {
        return (distance/50 * 0.75) + 1.50;
    }

    public void populateRiderList() {
        int increment = 0; 
        while (riders.size() != 100) {
            riders.add(new Rider(randomNumber(), randomNumber(), "Rider" + increment));
            increment++;
        }
    }

    public int getDistance(int x1, int x2, int y1, int y2) {
        int x = x2 - x1;
        int y = y2 - y1;
        return (int)Math.sqrt((x*x) + (y*y));
    }

    public boolean distanceChecker(int x1, int x2, int y1, int y2) {
        if (getDistance(x1,x2,y1,y2) <= 100) {
            return true; 
        }
        return false;
    }

    public int count = 100;  

    public void minDriverTest() {
        for (int i = 0; i < riders.size(); i++) {
            for (int j = 0; j < drivers.size(); j++) {
                if (distanceChecker(riders.get(i).getX(),drivers.get(j).getX(),riders.get(i).getY(),drivers.get(j).getY())) {
                    riders.remove(i);
                    drivers.remove(j);
                    break;
                }
            }
            count++;
            drivers.add(new Driver(randomNumber(), randomNumber(), "extraDriver"));
        }
    }

    public void testing() {
        populateNames();
        populateDB();  
        populateRiderList();
        System.out.println("The average minimum number of drivers needed across 1,000,000 trials is " + getAverage() + ".");
    }

    public int getAverage() {
        int average = 0; 
        for (int i = 0; i < 1000000; i++) {
            while (riders.size() != 0) {
                minDriverTest();    
            }
            average += count;
        }
        return average/1000000;
    }
}
