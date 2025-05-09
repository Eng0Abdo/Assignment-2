import java.util.Scanner;

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
