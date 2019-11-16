package com.example.demo.consumer;

//import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Component
public class Payment {

    private String id;
    private String paymentType;
    private Double amount;

    public Payment() {
    }

    public Payment(String paymentType, Double amount) {

        this.paymentType = paymentType;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id='" + id + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", amount=" + amount +
                '}';
    }


}
