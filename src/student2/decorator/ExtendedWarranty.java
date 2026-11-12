package student2.decorator;

import common.CartItem;

public class ExtendedWarranty extends ProductDecorator {
    public ExtendedWarranty(CartItem item) {
        super(item);
    }

    @Override
    public double getPrice() {
        return item.getPrice() + 15000 * getQty();
    }

    @Override
    public String getDescription() {
        return item.getDescription() + " + Extended Warranty";
    }
}
