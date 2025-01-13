package com.example.portfolio.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Trade {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

    private String tradeType; // Buy or Sell

    private Integer quantity;

    private Double priceAtTrade;


        @Setter
        @Getter
        @Column(nullable = false)
        private Long userId;

        @Setter
        @Getter
        @Column(nullable = false)
        private Long stockId;

        @Getter
        @Column(nullable = false)
        private String type; // Buy or Sell



        @Getter
        @Column(nullable = false)
        private double price;

        // Getters and Setters

//    public void setType(String type) {
//            this.type = type;
//        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

//    public void setPrice(double price) {
//            this.price = price;
//        }
    }
