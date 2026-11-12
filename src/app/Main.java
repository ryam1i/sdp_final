package app;

import catalog.ClothingFactory;
import catalog.ElectronicsFactory;
import catalog.ProductFactory;
import common.Product;
import student2.BasicCartItem;
import student2.Cart;
import student2.decorator.ExtendedWarranty;
import student2.decorator.GiftWrap;
import student2.strategy.PercentageDiscount;
import student2.strategy.ThresholdDiscount;
import student3.facade.CheckoutFacade;
import student3.facade.SimplePaymentService;
import student3.facade.SimpleShippingService;
import student3.observer.AdminDashboard;
import student3.observer.Inventory;
import student3.observer.OutOfStockException;

public class Main {
    public static void main(String[] args) {
        step("1. FACTORY METHOD + BUILDER: create products");
        ElectronicsFactory electronics = new ElectronicsFactory();
        ProductFactory clothing = new ClothingFactory();
        Product laptop = electronics.createLaptop("L1", 16, 512, "Silver");
        Product tShirt = clothing.getProduct("T1");

        Inventory inventory = new Inventory();
        inventory.register(laptop, 4);
        inventory.register(tShirt, 10);
        Cart cart = new Cart();
        AdminDashboard dashboard = new AdminDashboard();
        inventory.subscribe(cart);
        inventory.subscribe(dashboard);
        for (Product p : new Product[]{laptop, tShirt}) {
            dashboard.track(p.getId(), inventory.getPrice(p.getId()), inventory.getStock(p.getId()));
        }

        step("2. DECORATOR: add to cart with Gift Wrap + Extended Warranty");
        cart.addItem(new ExtendedWarranty(new GiftWrap(new BasicCartItem(laptop, 1))));
        cart.addItem(new BasicCartItem(tShirt, 2));
        cart.printCart();

        step("3. STRATEGY: 10% discount");
        cart.setDiscountStrategy(new PercentageDiscount(10));
        cart.printCart();
        step("3b. STRATEGY: swap to threshold discount at runtime");
        cart.setDiscountStrategy(new ThresholdDiscount());
        cart.printCart();

        step("4. OBSERVER: price changes in inventory, cart updates itself");
        inventory.updatePrice("L1", 280000);
        cart.printCart();

        step("5. FACADE: placeOrder()");
        CheckoutFacade checkout = new CheckoutFacade(
                inventory, new SimplePaymentService(), new SimpleShippingService());
        checkout.placeOrder(cart, "Astana, Kabanbay Batyr 53");
        dashboard.printSnapshot();

        step("6. FACADE: order exceeds stock");
        Cart bigCart = new Cart();
        bigCart.addItem(new BasicCartItem(laptop, 10));
        try {
            checkout.placeOrder(bigCart, "Almaty");
        } catch (OutOfStockException e) {
            System.out.println("Order rejected: " + e.getMessage());
        }
    }

    private static void step(String title) {
        System.out.println("\n--- " + title + " ---");
    }
}
