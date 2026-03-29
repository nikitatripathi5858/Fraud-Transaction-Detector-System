package service;

import engine.RiskLevel;

public class AlertService {

    public void sendAlert(String userId, RiskLevel level, int score) {

        System.out.println("\n===============================");
        System.out.println(" FRAUD ALERT ");
        System.out.println("User ID: " + userId);
        System.out.println("Risk Level: " + level);
        System.out.println("Risk Score: " + score);
        System.out.println("===============================\n");
    }
}