package hw2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {
    private Car createTestCar() {
        return new Car("Ford", "Fusion", 2006);
    }

    private Motorcycle createTestMotorcycle() {
        return new Motorcycle("Jawa", "125 Dandy", 1998);
    }

    @Test
    void carIdentificationDataIsAvailable() {
        var testee = new Car("Ford", "Ka", 1996);

        assertEquals("Ford", testee.getCompany());
        assertEquals("Ka", testee.getModel());
        assertEquals(1996, testee.getYearRelease());
    }

    @Test
    void carIsVehicle() {
        assertInstanceOf(Vehicle.class, createTestCar());
    }

    @Test
    void carHas4Wheels() {
        assertEquals(4, createTestCar().getNumWheels());
    }

    @Test
    void carHasSpeed60InTestDrive() {
        var testee = createTestCar();
        assertEquals(0, testee.getSpeed());

        testee.testDrive();
        assertEquals(60, testee.getSpeed());
    }

    @Test
    void carHasZeroSpeedOnParking() {
        var testee = createTestCar();
        testee.testDrive();

        testee.park();
        assertEquals(0, testee.getSpeed());
    }

    @Test
    void motorcycleIsVehicle() {
        assertInstanceOf(Vehicle.class, createTestMotorcycle());
    }

    @Test
    void motorcycleIdentificationDataIsAvailable() {
        var testee = new Motorcycle("Jawa", "250 Travel", 2007);

        assertEquals("Jawa", testee.getCompany());
        assertEquals("250 Travel", testee.getModel());
        assertEquals(2007, testee.getYearRelease());
    }

    @Test
    void motorcycleHas2Wheels() {
        assertEquals(2, createTestMotorcycle().getNumWheels());
    }

    @Test
    void motorcycleHasSpeed75InTestDrive() {
        var testee = createTestMotorcycle();
        assertEquals(0, testee.getSpeed());

        testee.testDrive();
        assertEquals(75, testee.getSpeed());
    }

    @Test
    void motorcycleHasZeroSpeedOnParking() {
        var testee = createTestMotorcycle();
        testee.testDrive();

        testee.park();
        assertEquals(0, testee.getSpeed());
    }
}