package student3.facade;

import common.CartItem;
import student2.Cart;
import student3.observer.Inventory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Facade pattern: one simple call, placeOrder(), hides the checkout workflow
 * across several subsystems (Inventory, PaymentService, ShippingService).
 */
public class CheckoutFacade {
    private final Inventory inventory;
    private final PaymentService payment;
    private final ShippingService shipping;

    public CheckoutFacade(Inventory inventory, PaymentService payment, ShippingService shipping) {
        this.inventory = inventory;
        this.payment = payment;
        this.shipping = shipping;
    }

    public OrderReceipt placeOrder(Cart cart, String address) {
        if (cart.getItems().isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }
        Map<String, Integer> required = requiredQuantities(cart);
        checkStock(required);

        if (!payment.charge(cart.getTotal())) {
            throw new IllegalStateException("Payment declined");
        }
        required.forEach(inventory::removeStock);
        String tracking = shipping.ship(address);

        OrderReceipt receipt = new OrderReceipt(
                describeItems(cart), cart.getSubtotal(), cart.getTotal(), tracking);
        System.out.println(receipt);
        return receipt;
    }

    private Map<String, Integer> requiredQuantities(Cart cart) {
        Map<String, Integer> required = new LinkedHashMap<>();
        for (CartItem item : cart.getItems()) {
            required.merge(item.getProduct().getId(), item.getQty(), Integer::sum);
        }
        return required;
    }

    private void checkStock(Map<String, Integer> required) {
        required.forEach((id, qty) -> {
            if (!inventory.isAvailable(id, qty)) {
                throw new student3.observer.OutOfStockException(id, qty, inventory.getStock(id));
            }
        });
    }

    private List<String> describeItems(Cart cart) {
        List<String> lines = new ArrayList<>();
        for (CartItem item : cart.getItems()) {
            lines.add(String.format(Locale.US, "%s x%d: %.2f KZT",
                    item.getDescription(), item.getQty(), item.getPrice()));
        }
        return lines;
    }
}
