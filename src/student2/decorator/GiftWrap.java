package student2.decorator;

import common.CartItem;

public class GiftWrap extends ProductDecorator {
    public GiftWrap(CartItem item) {
        super(item);
    }

    @Override
    public double getPrice() {
        return item.getPrice() + 2000 * getQty();
    }

    @Override
    public String getDescription() {
        return item.getDescription() + " + Gift Wrap";
    }
}
