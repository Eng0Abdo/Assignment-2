import java.util.Random;

class OTPService {
    private int otp;

    public void sendOTP() {
        otp = new Random().nextInt(900000) + 100000;
        System.out.println("OTP sent: " + otp); // simulate sending
    }

    public boolean verifyOTP(int inputOtp) {
        return this.otp == inputOtp;
    }
}
