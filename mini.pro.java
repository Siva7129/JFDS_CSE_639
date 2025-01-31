// Vehicle class (Base class)
abstract class Vehicle {
    private String licenseNumber;

    public Vehicle(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    @Override
    public String toString() {
        return "License Number: " + licenseNumber;
    }
}

// Car class (Inherits from Vehicle)
class Car extends Vehicle {
    public Car(String licenseNumber) {
        super(licenseNumber);
    }

    @Override
    public String toString() {
        return "Car - " + super.toString();
    }
}

// Bike class (Inherits from Vehicle)
class Bike extends Vehicle {
    public Bike(String licenseNumber) {
        super(licenseNumber);
    }

    @Override
    public String toString() {
        return "Bike - " + super.toString();
    }
}

// Custom Exception for full parking lot
class ParkingLotFullException extends Exception {
    public ParkingLotFullException(String message) {
        super(message);
    }
}

// Custom Exception for vehicle not found
class VehicleNotFoundException extends Exception {
    public VehicleNotFoundException(String message) {
        super(message);
    }
}

// ParkingLot class
class ParkingLot {
    private Vehicle[] slots;
    private int capacity;
    private int currentCount;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.slots = new Vehicle[capacity];
        this.currentCount = 0;
    }

    // Method to add a vehicle to the parking lot
    public void parkVehicle(Vehicle vehicle) throws ParkingLotFullException {
        if (currentCount >= capacity) {
            throw new ParkingLotFullException("Parking lot is full!");
        }
        slots[currentCount++] = vehicle;
        System.out.println(vehicle + " parked successfully.");
    }

    // Method to remove a vehicle from the parking lot
    public void removeVehicle(String licenseNumber) throws VehicleNotFoundException {
        boolean found = false;
        for (int i = 0; i < currentCount; i++) {
            if (slots[i].getLicenseNumber().equals(licenseNumber)) {
                found = true;
                System.out.println(slots[i] + " removed successfully.");
                slots[i] = slots[--currentCount]; // Move last vehicle to the removed spot
                slots[currentCount] = null; // Free the last spot
                break;
            }
        }
        if (!found) {
            throw new VehicleNotFoundException("Vehicle with license number " + licenseNumber + " not found!");
        }
    }

    // Display all parked vehicles
    public void displayParkedVehicles() {
        if (currentCount == 0) {
            System.out.println("Parking lot is empty.");
        } else {
            System.out.println("Vehicles in the parking lot:");
            for (int i = 0; i < currentCount; i++) {
                System.out.println(slots[i]);
            }
        }
    }
}

// Main class
public class minipro {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(3); // Create a parking lot with 3 slots

        try {
            // Add vehicles
            parkingLot.parkVehicle(new Car("KA-01-1234"));
            parkingLot.parkVehicle(new Bike("KA-02-5678"));
            parkingLot.parkVehicle(new Car("KA-03-9012"));

            // Display parked vehicles
            parkingLot.displayParkedVehicles();

            // Try adding another vehicle (should throw ParkingLotFullException)
            parkingLot.parkVehicle(new Bike("KA-04-3456"));

        } catch (ParkingLotFullException e) {
            System.out.println(e.getMessage());
        }

        try {
            // Remove a vehicle
            parkingLot.removeVehicle("KA-01-1234");

            // Try removing a non-existing vehicle (should throw VehicleNotFoundException)
            parkingLot.removeVehicle("KA-05-7890");

        } catch (VehicleNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // Display remaining vehicles
        parkingLot.displayParkedVehicles();
    }
}