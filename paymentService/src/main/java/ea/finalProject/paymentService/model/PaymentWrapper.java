package ea.finalProject.paymentService.model;

import org.springframework.data.annotation.Id;

public class PaymentWrapper {

    @Id
    private String Id;
    private String firstName;
    private String lastName;
    private String email;
    private String plan;
    private String serviceProvider;
    private Double amount;
    private String paymentType;
    private String subscriptionDate;
    private String expiryDate;

    public PaymentWrapper() {
    }

    public PaymentWrapper(String id, String firstName, String lastName, String email, String plan, String serviceProvider, Double amount, String paymentType, String subscriptionDate, String expiryDate) {
        Id = id;
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

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(String subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return "PaymentWrapper{" +
                "Id='" + Id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", plan='" + plan + '\'' +
                ", serviceProvider='" + serviceProvider + '\'' +
                ", amount=" + amount +
                ", paymentType='" + paymentType + '\'' +
                ", subscriptionDate='" + subscriptionDate + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                '}';
    }
}
