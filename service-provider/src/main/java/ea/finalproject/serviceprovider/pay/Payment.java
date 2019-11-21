package ea.finalproject.serviceprovider.pay;

public class Payment {

    private String bankName;
    private String accNum;
    private Double amount;
    private String creditCardNo;

    public Payment() {
    }

    public Payment(String bankName, String accNum, Double amount, String creditCardNo) {
        this.bankName = bankName;
        this.accNum = accNum;
        this.amount = amount;
        this.creditCardNo = creditCardNo;
    }

    public String getBankName() {
        return bankName;
    }

    public String getAccNum() {
        return accNum;
    }

    public Double getAmount() {
        return amount;
    }

    public String getCreditCardNo() {
        return creditCardNo;
    }


}
