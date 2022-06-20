package pizza.ua.pizza_mania.entities;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class OrderStatus {
    private Integer orderStatusId;
    private String statusDescription;

    public OrderStatus(Integer orderId, String statusDescription) {
        this.orderStatusId = orderId;
        this.statusDescription = statusDescription;
    }

    public OrderStatus(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public Integer getOrderId() {
        return orderStatusId;
    }

    public void setOrderId(Integer orderId) {
        this.orderStatusId = orderId;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderStatus that = (OrderStatus) o;
        return Objects.equals(orderStatusId, that.orderStatusId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderStatusId);
    }

    @Override
    public String toString() {
        return "OrderStatus{" +
                "orderStatusId=" + orderStatusId +
                ", statusDescription='" + statusDescription + '\'' +
                '}';
    }
}
