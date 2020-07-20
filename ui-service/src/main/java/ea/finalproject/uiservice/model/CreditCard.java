package ea.finalproject.uiservice.model;

public class CreditCard {


    private Long cardNumber;
    private String cardName;
    private Integer cvv;
    private String expiryDate;

    public CreditCard() {
    }

    public CreditCard(Long cardNumber, String cardName, Integer cvv, String expiryDate) {
        this.cardNumber = cardNumber;
        this.cardName = cardName;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}
