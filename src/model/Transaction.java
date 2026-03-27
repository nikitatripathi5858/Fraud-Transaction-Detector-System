package model;

import java.time.LocalDateTime;

public class Transaction {

    private final String transactionId;
    private final String userId;
    private final double amount;
    private final String location;
    private final String deviceId;
    private final LocalDateTime timestamp;

    public Transaction(String transactionId, String userId, double amount,
                       String location, String deviceId, LocalDateTime timestamp) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.amount = amount;
        this.location = location;
        this.deviceId = deviceId;
        this.timestamp = timestamp;
    }

    public String getTransactionId() { return transactionId; }
    public String getUserId() { return userId; }
    public double getAmount() { return amount; }
    public String getLocation() { return location; }
    public String getDeviceId() { return deviceId; }
    public LocalDateTime getTimestamp() { return timestamp; }
}