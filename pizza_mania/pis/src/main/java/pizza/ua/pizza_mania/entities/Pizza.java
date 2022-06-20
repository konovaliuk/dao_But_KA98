package pizza.ua.pizza_mania.entities;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class Pizza {
    private Integer pizzaId;
    private String pizzaName;
    private String pizzaIngridients;
    private Integer size;
    private Integer price;

    public Pizza(Integer pizzaId, String pizzaName, String pizzaIngridients, Integer size, Integer price) {
        this.pizzaId = pizzaId;
        this.pizzaName = pizzaName;
        this.pizzaIngridients = pizzaIngridients;
        this.size = size;
        this.price = price;
    }

    public Pizza(String pizzaName, String pizzaIngridients, Integer size, Integer price) {
        this.pizzaName = pizzaName;
        this.pizzaIngridients = pizzaIngridients;
        this.size = size;
        this.price = price;
    }

    public Integer getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(Integer pizzaId) {
        this.pizzaId = pizzaId;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public String getPizzaIngridients() {
        return pizzaIngridients;
    }

    public void setPizzaIngridients(String pizzaIngridients) {
        this.pizzaIngridients = pizzaIngridients;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza that = (Pizza) o;
        return Objects.equals(pizzaId, that.pizzaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pizzaId);
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "pizzaId=" + pizzaId +
                ", pizzaName='" + pizzaName + '\'' +
                ", pizzaIngridients='" + pizzaIngridients + '\'' +
                ", size=" + size +
                ", price=" + price +
                '}';
    }
}
