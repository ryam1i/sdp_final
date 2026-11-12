package student3.facade;

import java.util.Locale;

public class SimplePaymentService implements PaymentService {
    @Override
    public boolean charge(double amount) {
        System.out.printf(Locale.US, "[Payment] Charged %.2f KZT%n", amount);
        return amount >= 0;
    }
}
