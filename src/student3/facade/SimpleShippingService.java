package student3.facade;

public class SimpleShippingService implements ShippingService {
    private int counter = 1000;

    @Override
    public String ship(String address) {
        String tracking = "TRK-" + (++counter);
        System.out.println("[Shipping] Parcel " + tracking + " is on its way to " + address);
        return tracking;
    }
}
