package pizza.ua.pizza_mania.entities;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class OrderCheck {
    private Integer orderCheckId;
    private Integer user_id;
    private Integer orderNumber;
    private Date orderDate;
    private Time orderTime;
    private Integer totalUserCheck;

    public OrderCheck(Integer orderCheckId, Integer user_id, Integer orderNumber, Date orderDate, Time orderTime, Integer totalUserCheck) {
        this.orderCheckId = orderCheckId;
        this.user_id = user_id;
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.totalUserCheck = totalUserCheck;
    }

    public OrderCheck(Integer user_id, Integer orderNumber, Date orderDate, Time orderTime, Integer totalUserCheck) {
        this.user_id = user_id;
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.totalUserCheck = totalUserCheck;
    }

    public Integer getOrderCheckId() {
        return orderCheckId;
    }

    public void setOrderCheckId(Integer orderCheckId) {
        this.orderCheckId = orderCheckId;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Time getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Time orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getTotalUserCheck() {
        return totalUserCheck;
    }

    public void setTotalUserCheck(Integer totalUserCheck) {
        this.totalUserCheck = totalUserCheck;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderCheck that = (OrderCheck) o;
        return Objects.equals(orderCheckId, that.orderCheckId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderCheckId);
    }

    @Override
    public String toString() {
        return "OrderCheck{" +
                "orderCheckId=" + orderCheckId +
                ", user_id=" + user_id +
                ", orderNumber=" + orderNumber +
                ", orderDate=" + orderDate +
                ", orderTime=" + orderTime +
                ", totalUserCheck=" + totalUserCheck +
                '}';
    }
}

