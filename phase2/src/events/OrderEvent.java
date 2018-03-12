package events;

import restaurant.Order;
import restaurant.OrderImpl;
import restaurant.Restaurant;
import restaurant.Table;


class OrderEvent implements Event {
    private final Order order;
    private final Table table;

    OrderEvent(Table table, String orderString) {
        this.table = table;
        order = new OrderImpl(orderString);
        order.setTableId(table.getId());
    }

    @Override
    public EventType getType() {
        return EventType.ORDER;
    }

    @Override
    public void doEvent() {
        table.addOrder(order);
        Restaurant.addPlacedOrder(order);
        //System.out.println(order + "added to table.");
    }

    Order getOrder() {
        return order; //TODO: make a copy of this item for returning to avoid exposing internal private state
    }
}

