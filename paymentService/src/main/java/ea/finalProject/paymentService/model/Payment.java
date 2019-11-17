package ea.finalProject.paymentService.model;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

public class Payment {

    @Id
    private String Id;
    private String firstName;
    private String lastName;
    private String email;
    private String plan;
    private String serviceProvider;
    private Double amount;
    private PaymentType paymentType;
    private LocalDate subscriptionDate;
    private LocalDate expiryDate;

    public Payment() {
    }

    public Payment(String firstName, String lastName, String email, String plan, String serviceProvider, Double amount, PaymentType paymentType, LocalDate subscriptionDate, LocalDate expiryDate) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.plan = plan;
        this.serviceProvider = serviceProvider;
        this.amount = amount;
        this.paymentType = paymentType;
        this.subscriptionDate = subscriptionDate;
        this.expiryDate = expiryDate;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(String serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public LocalDate getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(LocalDate subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
}
