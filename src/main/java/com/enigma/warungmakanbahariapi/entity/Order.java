package com.enigma.warungmakanbahariapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "dining_table_id")
    private DiningTable diningTable;

    @ManyToOne
    @JoinColumn(name = "trans_type_id")
    private TransType transType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date transDate;
}
