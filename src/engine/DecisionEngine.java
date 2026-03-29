package engine;

import model.Transaction;
import model.UserProfile;

public class DecisionEngine {

    public int calculateRiskScore(Transaction txn, UserProfile user) {

        int score = 0;

        // High amount
        if (txn.getAmount() > 50000) {
            score += 50;
        }

        // Location change
        if (user.getLastLocation() != null &&
            !txn.getLocation().equals(user.getLastLocation())) {
            score += 20;
        }

        // New device
        if (!user.getKnownDevices().contains(txn.getDeviceId())) {
            score += 30;
        }

        // Frequent transactions
        if (user.getTransactionCount() > 5) {
            score += 10;
        }

        return score;
    }

    public RiskLevel getRiskLevel(int score) {
        if (score >= 80) return RiskLevel.BLOCKED;
        if (score >= 50) return RiskLevel.HIGH;
        if (score >= 30) return RiskLevel.MEDIUM;
        return RiskLevel.LOW;
    }
}