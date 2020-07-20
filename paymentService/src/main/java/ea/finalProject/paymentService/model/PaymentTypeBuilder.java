package ea.finalProject.paymentService.model;


import org.springframework.data.annotation.Id;

public class PaymentTypeBuilder {

    @Id
    private String Id;
    private Long creditcardNumber;
    private Integer cvv;
    private String expiryDate;
    private String name;
    private String bankName;
    private String accountNumber;
    private String email;
    private String password;

    public PaymentTypeBuilder setId(String id) {
        this.Id = Id;
        return  this;
    }

    public PaymentTypeBuilder setCreditcardNumber(Long creditcardNumber) {
        this.creditcardNumber = creditcardNumber;
        return this;
    }

    public PaymentTypeBuilder setCvv(Integer cvv) {
        this.cvv = cvv;
        return this;
    }

    public PaymentTypeBuilder setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }

    public PaymentTypeBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PaymentTypeBuilder setBankName(String bankName) {
        this.bankName = bankName;
        return this;
    }

    public PaymentTypeBuilder setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public PaymentTypeBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public PaymentTypeBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public PaymentType buildPaymentType(){
        return new PaymentType(creditcardNumber, cvv, expiryDate, email, bankName, name, accountNumber, password);
    }


}
