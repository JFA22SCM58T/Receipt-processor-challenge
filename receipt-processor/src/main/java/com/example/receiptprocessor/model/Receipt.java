package com.example.receiptprocessor.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String retailer;

    private String purchaseDate;

    private String purchaseTime;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ITEM_ID")
    private List<Items> items;

    private String total;

}
