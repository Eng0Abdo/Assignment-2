# Investment Portfolio Manager 💰📊

A Java application for tracking investments, calculating zakat obligations, and managing linked bank accounts with OTP verification.

## 📁 Project Structure

### Core Classes
| File                | Description                                                                 |
|---------------------|-----------------------------------------------------------------------------|
| Main.java       | Entry point - Initializes databases and starts the application.                 |
| Application.java  | Main controller - Handles user menus and navigation.                          |
| Screen.java       | Authentication facade - Manages login/signup and bank account linking.        |
| Database.java     | Persistent storage for user accounts (serialized to `accounts.db`).           |
| BankAccountDatabase.java | Stores bank account details (serialized to `bank.db`).                 |

### Domain Models
| File                | Description                                                                 |
|---------------------|-----------------------------------------------------------------------------|
| Account.java       | User account with portfolio and linked bank accounts.                        |
| Portfolio.java    | Manages assets (stocks, real estate, etc.) and calculates zakat.              |
| Asset.java        | Represents an investment asset with tracking data.                            |
| BankAccount.java  | Stores bank card details with validation logic.                               |

### Features
| File                | Functionality                                                               |
|---------------------|-----------------------------------------------------------------------------|
| ConnectBank.java  | OTP-based bank account linking system.                                        |
| OTPService.java   | Generates and verifies 6-digit OTPs.                                          |

## 🛠️ Tools & Technologies
- **Java SE 17** - Core application logic
- **Object Serialization** - For persistent storage (`.db` files)
