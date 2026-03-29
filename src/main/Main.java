package main;
import engine.DecisionEngine;
import engine.RiskLevel;
import java.time.LocalDateTime;
import java.util.*;
import model.Transaction;
import model.UserProfile;
import service.AlertService;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        //  MULTIPLE USERS STORAGE
        Map<String, UserProfile> users = new HashMap<>();

        DecisionEngine engine = new DecisionEngine();
        AlertService alert = new AlertService();

        while (true) {

            System.out.println("\n1. New Transaction");
            System.out.println("2. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 2) break;

            System.out.print("Enter Transaction ID: ");
            String txnId = sc.nextLine();

            System.out.print("Enter User ID: ");
            String userId = sc.nextLine();

            System.out.print("Enter Amount: ");
            double amount = sc.nextDouble();
            sc.nextLine();

            System.out.print("Enter Location: ");
            String location = sc.nextLine();

            System.out.print("Enter Device ID: ");
            String deviceId = sc.nextLine();

            Transaction txn = new Transaction(
                    txnId, userId, amount, location, deviceId, LocalDateTime.now()
            );

            //  MULTI-USER LOGIC
            users.putIfAbsent(userId, new UserProfile(userId));
            UserProfile user = users.get(userId);

            user.addTransaction(txn);

            int score = engine.calculateRiskScore(txn, user);
            RiskLevel level = engine.getRiskLevel(score);

            alert.sendAlert(userId, level, score);
        }

        sc.close();
    }
}
