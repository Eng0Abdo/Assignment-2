import java.util.Scanner;
import java.util.Random;

 class BankAccount {
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

    public boolean validateCard() {
        return cardNumber != null && cardNumber.length() == 16 && cvv != null && cvv.length() >= 3;
    }

    // Getters
    public String getBankName() {
        return bankName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getCvv() {
        return cvv;
    }
}



class OTPService {
    private int otp;

    public void sendOTP() {
        otp = new Random().nextInt(900000) + 100000;
        System.out.println("🔐 OTP sent: " + otp); // Simulate sending the OTP
    }

    public boolean verifyOTP(int inputOtp) {
        return this.otp == inputOtp;
    }
}

public class ConnectBank {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Bank Name: ");
        String bank = scanner.nextLine();

        System.out.print("Enter Card Number (16 digits): ");
        String cardNumber = scanner.nextLine();

        System.out.print("Enter Card Holder Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Expiry Date (MM/YY): ");
        String expiry = scanner.nextLine();

        System.out.print("Enter CVV: ");
        String cvv = scanner.nextLine();

        BankAccount account = new BankAccount(bank, cardNumber, name, expiry, cvv);

        if (!account.validateCard()) {
            System.out.println("Invalid card details!");
            return;
        }
        OTPService otpService = new OTPService();
        otpService.sendOTP();

        System.out.print("Enter OTP: ");
        int inputOtp = scanner.nextInt();

        if (otpService.verifyOTP(inputOtp)) {
            System.out.println("Bank account linked successfully!");
        } else {
            System.out.println("Invalid OTP. Bank linking failed.");
        }

        scanner.close();
    }
}
