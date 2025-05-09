import java.io.Serializable;

public class BankAccount implements Serializable {

    private String bankName;
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;

    public BankAccount(String bankName, String cardNumber, String cardHolderName, String expiryDate, String cvv) {
        this.bankName = bankName;
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    public String getBankName() {
        return bankName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public boolean validateCard() {
        return cardNumber.length() == 16 && cvv.length() >= 3;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public String getCvv() {
        return cvv;
    }
}
