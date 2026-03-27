package model;

import java.util.*;

public class UserProfile {

    private final String userId;
    private double avgAmount;
    private String lastLocation;
    private final Set<String> knownDevices;
    private final List<Transaction> transactionHistory;

    public UserProfile(String userId) {
        this.userId = userId;
        this.avgAmount = 0.0;
        this.knownDevices = new HashSet<>();
        this.transactionHistory = new ArrayList<>();
    }

    public void addTransaction(Transaction txn) {
        transactionHistory.add(txn);
        updateAverage(txn.getAmount());
        this.lastLocation = txn.getLocation();
        this.knownDevices.add(txn.getDeviceId());
    }

    private void updateAverage(double newAmount) {
        int size = transactionHistory.size();
        avgAmount = ((avgAmount * (size - 1)) + newAmount) / size;
    }

    public String getUserId() { return userId; }
    public double getAvgAmount() { return avgAmount; }
    public String getLastLocation() { return lastLocation; }
    public Set<String> getKnownDevices() { return knownDevices; }
    public List<Transaction> getTransactionHistory() { return transactionHistory; }

    // ⭐ NEW METHOD
    public int getTransactionCount() {
        return transactionHistory.size();
    }
}