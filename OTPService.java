/**
 * Generates and verifies OTPs for bank account linking
 * Simulates SMS delivery via console output
 *
 * @author Abdelrahman Emad, Mahmoud Mohamed, Peter Gerges
 * @version 1.0
 */

import java.util.Random;

class OTPService {

    /**
     * Generates a 6-digit OTP (100000-999999)
     *
     * @return OTP value (also prints to console)
     */

    private int otp;

    public void sendOTP() {
        otp = new Random().nextInt(900000) + 100000;
        System.out.println("OTP sent: " + otp); // simulate sending
    }

    /**
     * Validates user-entered OTP
     *
     * @param inputOtp 6-digit code from user
     * @return true if matches generated OTP
     */

    public boolean verifyOTP(int inputOtp) {
        return this.otp == inputOtp;
    }
}
