package ea.finalProject.paymentService.model;


import org.springframework.data.annotation.Id;

public class PaymentType {

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

    public PaymentType() {
    }

    public PaymentType( Long creditcardNumber, Integer cvv, String expiryDate, String name, String bankName, String accountNumber, String email, String password) {

        this.creditcardNumber = creditcardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
        this.name = name;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return Id;
    }

    public Long getCreditcardNumber() {
        return creditcardNumber;
    }

    public Integer getCvv() {
        return cvv;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getName() {
        return name;
    }

    public String getBankName() {
        return bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    @Override
    public String toString() {
        return "PaymentType{" +
                "id='" + Id + '\'' +
                ", creditcardNumber=" + creditcardNumber +
                ", cvv=" + cvv +
                ", expiryDate='" + expiryDate + '\'' +
                ", name='" + name + '\'' +
                ", bankName='" + bankName + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
