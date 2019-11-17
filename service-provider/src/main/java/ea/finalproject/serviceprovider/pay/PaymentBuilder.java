package ea.finalproject.serviceprovider.pay;

public class PaymentBuilder {

    private String bankName;
    private String accNum;
    private Double amount;
    private String creditCardNo;

    public PaymentBuilder setBankName(String bankName) {
        this.bankName = bankName;
        return this;
    }

    public PaymentBuilder setAccNum(String accNum) {
        this.accNum = accNum;
        return this;
    }

    public PaymentBuilder setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public PaymentBuilder setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
        return this;
    }

    public Payment build() {
        return new Payment(bankName, accNum, amount, creditCardNo);
    }

}
