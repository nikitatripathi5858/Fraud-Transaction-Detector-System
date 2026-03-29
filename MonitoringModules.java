import java.util.*;
import java.time.*;

class DeviceMonitor {
    public boolean isNewDevice(String device, Set<String> knownDevices) {
        return !knownDevices.contains(device);
    }
}

class VelocityChecker {
    public boolean isHighVelocity(List<Transaction> history, LocalDateTime currentTime) {
        int count = 0;
        for (Transaction t : history) {
            Duration diff = Duration.between(t.time, currentTime);
            if (diff.toMinutes() <= 1) {
                count++;
            }
        }
        return count > 2;
    }
}

class TransactionHistory {
    private Map<Integer, List<Transaction>> historyMap = new HashMap<>();

    public void addTransaction(Transaction t) {
        historyMap.computeIfAbsent(t.userId, k -> new ArrayList<>()).add(t);
    }

    public List<Transaction> getUserTransactions(int userId) {
        return historyMap.getOrDefault(userId, new ArrayList<>());
    }
}
