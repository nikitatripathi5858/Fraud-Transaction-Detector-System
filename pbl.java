package JAVAprogram;
import java.util.Scanner;
import java.util.ArrayList;
//Rule Interface Module
interface Rule {
 boolean applyRule(Transaction t);
 int getRiskScore();
 String getRuleName();
}

//Transaction Class
class Transaction {
 double amount;
 String location;
 int transactionsLastHour;
 String deviceId;

 public Transaction(double amount, String location, int transactionsLastHour, String deviceId) {
     this.amount = amount;
     this.location = location;
     this.transactionsLastHour = transactionsLastHour;
     this.deviceId = deviceId;
 }
}

//Amount Rule
class AmountRule implements Rule {
 public boolean applyRule(Transaction t) {
     return t.amount > 10000;
 }

 public int getRiskScore() {
     return 30;
 }

 public String getRuleName() {
     return "Amount Rule Triggered";
 }
}

//Location Rule
class LocationRule implements Rule {
 public boolean applyRule(Transaction t) {
     return !t.location.equalsIgnoreCase("India");
 }
 
 

 public int getRiskScore() {
     return 25;
 }

 public String getRuleName() {
     return "Location Rule Triggered";
 }
}

//Velocity Rule
class VelocityRule implements Rule {
 public boolean applyRule(Transaction t) {
     return t.transactionsLastHour > 5;
 }
 

 public int getRiskScore() {
     return 20;
 }

 public String getRuleName() {
     return "Velocity Rule Triggered";
 }
}

//Device Rule
class DeviceRule implements Rule {
 public boolean applyRule(Transaction t) {
     return !t.deviceId.equals("TRUSTED_DEVICE");
 }

 public int getRiskScore() {
     return 25;
 }

 public String getRuleName() {
     return "Device Rule Triggered";
 }
}


//Rule Engine Module
class RuleEngine {
 ArrayList<Rule> rules = new ArrayList<>();
 ArrayList<String> triggeredRules = new ArrayList<>();

 public RuleEngine() {
     rules.add(new AmountRule());
     rules.add(new LocationRule());
     rules.add(new VelocityRule());
     rules.add(new DeviceRule());
 }

 public int executeRules(Transaction t) {
     int totalRisk = 0;

     for (Rule r : rules) {
         if (r.applyRule(t)) {
             triggeredRules.add(r.getRuleName());
             totalRisk += r.getRiskScore();
         }
     }

     return totalRisk;
 }

 public void showTriggeredRules() {
     for (String rule : triggeredRules) {
         System.out.println(rule);
     }
 }
}


//Risk Scoring Module (Main)
public class pbl {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Amount");
	     double amount = sc.nextDouble();
	     
	     System.out.println("Enter location");
	     String location = sc.nextLine();
	     
	     sc.nextLine();
	     
	     System.out.println("Enter transaction lately!");
	     int transactionsLastHour = sc.nextInt();
	     sc.nextLine();
	     System.out.println("Enter DeviceId!");
	     String deviceId = sc.nextLine();
	     sc.nextLine();
	     
	     
	     Transaction t = new Transaction(amount, location,transactionsLastHour , deviceId);

	     RuleEngine engine = new RuleEngine();
	     int riskScore = engine.executeRules(t);

	     System.out.println("Triggered Rules:");
	     engine.showTriggeredRules();

	     System.out.println("Total Risk Score: " + riskScore);

	     if (riskScore > 50) {
	         System.out.println("Transaction is FRAUD");
	     } else {
	         System.out.println("Transaction is SAFE");
	     }
	 }
	
}
