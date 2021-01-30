import java.util.*;

class TrackAndTrace { 
    // DEPENDENCY: overlap
    public static boolean testOverlap(int startTime, int endTime, int customerArrivalTime, int customerDepartureTime) {
        if (((customerArrivalTime > startTime) && (customerArrivalTime < endTime)) || ((customerDepartureTime > startTime) && (customerDepartureTime < endTime)) || ((startTime > customerArrivalTime) && (endTime < customerDepartureTime))) {
            return true;
        } else {
            return false;
        }
    }
    
    // DEPENDENCY: testOverlap, getOverlaps
    public static boolean overlap(int startTime, int endTime, int customerArrivalTime, int customerDepartureTime) {
        if (customerArrivalTime > customerDepartureTime) {
            customerDepartureTime = customerDepartureTime + 24;
        }  
        if (startTime > endTime) {
            endTime = endTime + 24;
        }
        
        return (testOverlap(startTime, endTime, customerArrivalTime, customerDepartureTime));
    }

    // DEPENDENCY: overlap, main
    public static int getOverlaps(int startTime, int endTime) {    
        // VARIABLES & INPUTS:
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the number of customers: ");
        int customersNumber = scan.nextInt();

        String customerName = "";
        int customerArrivalTime;
        int customerDepartureTime;
        boolean testOverlap;
        int testNeed = 0;

        // LOOP FOR CUSTOMER DATA COLLECTION (NEW METHOD NEEDED):
        for (int i = 0; i < customersNumber; i++) {
            // INPUTS:
            System.out.println("Enter the customer's name: ");
            customerName = scan.next();
            
            System.out.println("Enter the arrival time: ");
            customerArrivalTime = scan.nextInt();
            
            System.out.println("Enter the departure time: ");
            customerDepartureTime = scan.nextInt();
            
            // PROCESSING:
            testOverlap = overlap(startTime, endTime, customerArrivalTime, customerDepartureTime);
            // OUTPUT:
            if (testOverlap == true) {
                System.out.println((customerName) + " needs a test.");
                testNeed++;
            } else {
                System.out.println((customerName) + " does not need a test.");
            }
        }
        scan.close();
        return testNeed;
}

    // DEPENDENCY: getOverlaps
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // VARIABLES & INPUTS:
        System.out.println("Enter the start time: ");
        int startTime = scan.nextInt();
        System.out.println("Enter the end time: ");
        int endTime = scan.nextInt();

        int numberOfTests = getOverlaps(startTime, endTime);

        System.out.println("There are " + numberOfTests + " tests needed.");

        scan.close();
    }
}
