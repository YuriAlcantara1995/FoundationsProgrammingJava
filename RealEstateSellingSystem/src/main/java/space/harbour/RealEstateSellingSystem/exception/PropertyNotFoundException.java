package space.harbour.RealEstateSellingSystem.exception;

public class PropertyNotFoundException extends Exception {

    public PropertyNotFoundException() {
        super("Property not found");
    }
}
