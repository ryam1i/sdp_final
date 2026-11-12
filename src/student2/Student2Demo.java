package student2;

import catalog.ElectronicsFactory;
import common.CartItem;
import common.Product;
import student2.decorator.ExtendedWarranty;
import student2.decorator.GiftWrap;
import student2.strategy.FixedDiscount;
import student2.strategy.PercentageDiscount;
import student2.strategy.ThresholdDiscount;

public class Student2Demo {
    public static void main(String[] args) {
        Product laptop = new ElectronicsFactory().getProduct("L1");
        BasicCartItem basicItem = new BasicCartItem(laptop, 1);
        CartItem decoratedItem = new ExtendedWarranty(new GiftWrap(basicItem));
        Cart cart = new Cart();
        cart.addItem(decoratedItem);

        System.out.println("\n1. DECORATOR");
        cart.printCart();

        System.out.println("\n2. DISCOUNT: 10%");
        cart.setDiscountStrategy(new PercentageDiscount(10));
        cart.printCart();

        System.out.println("\n3. DISCOUNT: 20000");
        cart.setDiscountStrategy(new FixedDiscount(20000));
        cart.printCart();

        System.out.println("\n4. THRESHOLD DISCOUNT");
        cart.setDiscountStrategy(new ThresholdDiscount());
        cart.printCart();

        System.out.println("\n5. MANUAL INVENTORY CALLBACKS");
        cart.onPriceChanged("L1", 280000);
        cart.onStockChanged("L1", 5);
        cart.printCart();
        System.out.println("Known stock: " + basicItem.getKnownStock());
    }
}
