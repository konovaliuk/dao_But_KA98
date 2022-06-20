package pizza.ua.pizza_mania.entities;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class Order {
    private Integer orderId;
    private Integer orderNumber;
    private Integer pizzaId;
    private Integer amount;
    private Integer total_price;
    private Integer orderStatusId;

    public Order(Integer orderId, Integer orderNumber, Integer pizzaId, Integer amount, Integer total_price, Integer orderStatusId) {
        this.orderId = orderId;
        this.orderNumber = orderNumber;
        this.pizzaId = pizzaId;
        this.amount = amount;
        this.total_price = total_price;
        this.orderStatusId = orderStatusId;
    }

    public Order(Integer orderNumber, Integer pizzaId, Integer amount, Integer total_price, Integer orderStatusId) {
        this.orderNumber = orderNumber;
        this.pizzaId = pizzaId;
        this.amount = amount;
        this.total_price = total_price;
        this.orderStatusId = orderStatusId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(Integer pizzaId) {
        this.pizzaId = pizzaId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Integer total_price) {
        this.total_price = total_price;
    }

    public Integer getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(Integer orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order that = (Order) o;
        return Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderNumber=" + orderNumber +
                ", pizzaId=" + pizzaId +
                ", amount=" + amount +
                ", total_price=" + total_price +
                ", orderStatusId=" + orderStatusId +
                '}';
    }
}
