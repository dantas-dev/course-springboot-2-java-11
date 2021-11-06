package com.educandoweb.course.entities;

import com.educandoweb.course.entities.enums.OrderStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;


import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "tb_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:ss'Z'", timezone = "GMT")
    private Instant moment;

    private Integer orderStatusEnum;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>();

    public Order() {

    }

    public Order(Integer id, Instant moment, OrderStatusEnum orderStatusEnum, User client) {
        this.id = id;
        this.moment = moment;
        setOrderStatusEnum(orderStatusEnum);
        this.client = client;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public OrderStatusEnum getOrderStatusEnum() {
        return OrderStatusEnum.valueOf(orderStatusEnum);
    }

    public void setOrderStatusEnum(OrderStatusEnum orderStatusEnum) {
        if (orderStatusEnum != null) {
            this.orderStatusEnum = orderStatusEnum.getCode();
        }
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
