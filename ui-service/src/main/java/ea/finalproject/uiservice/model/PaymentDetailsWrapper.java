package ea.finalproject.uiservice.model;

import java.time.LocalDate;

public class PaymentDetailsWrapper {

    private String Id;
    private String firstName;
    private String lastName;
    private String email;
    private String plan;
    private String serviceProvider;
    private Double amount;
    private String paymentType;
    private String period;
    private LocalDate subscriptionDate;
    private LocalDate expiryDate;
    private String status;
    private Long creditcardNumber;
    private Integer cvv;
    private String cardExpiryDate;
    private String name;
    private String bankName;
    private String accountNumber;
    private String payPalEmail;
    private String password;


    public PaymentDetailsWrapper() {
    }

    public PaymentDetailsWrapper(String id, String firstName, String lastName, String email, String plan, String serviceProvider, Double amount, String paymentType, String period, LocalDate subscriptionDate, LocalDate expiryDate, String status, Long creditcardNumber, Integer cvv, String cardExpiryDate, String name, String bankName, String accountNumber, String payPalEmail, String password) {
        Id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.plan = plan;
        this.serviceProvider = serviceProvider;
        this.amount = amount;
        this.paymentType = paymentType;
        this.period = period;
        this.subscriptionDate = subscriptionDate;
        this.expiryDate = expiryDate;
        this.status = status;
        this.creditcardNumber = creditcardNumber;
        this.cvv = cvv;
        this.cardExpiryDate = cardExpiryDate;
        this.name = name;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.payPalEmail = payPalEmail;
        this.password = password;
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

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreditcardNumber() {
        return creditcardNumber;
    }

    public void setCreditcardNumber(Long creditcardNumber) {
        this.creditcardNumber = creditcardNumber;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public String getCardExpiryDate() {
        return cardExpiryDate;
    }

    public void setCardExpiryDate(String cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPayPalEmail() {
        return payPalEmail;
    }

    public void setPayPalEmail(String payPalEmail) {
        this.payPalEmail = payPalEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "PaymentDetailsWrapper{" +
                "Id='" + Id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", plan='" + plan + '\'' +
                ", serviceProvider='" + serviceProvider + '\'' +
                ", amount=" + amount +
                ", paymentType='" + paymentType + '\'' +
                ", period='" + period + '\'' +
                ", subscriptionDate=" + subscriptionDate +
                ", expiryDate=" + expiryDate +
                ", status='" + status + '\'' +
                ", creditcardNumber=" + creditcardNumber +
                ", cvv=" + cvv +
                ", cardExpiryDate='" + cardExpiryDate + '\'' +
                ", name='" + name + '\'' +
                ", bankName='" + bankName + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", payPalEmail='" + payPalEmail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
