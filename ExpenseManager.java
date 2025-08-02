import java.util.*;

public class ExpenseManager {
    private ExpenseTracker tracker;

    public ExpenseManager(ExpenseTracker tracker) {
        this.tracker = tracker;
    }

    public void showCategorySummary() {
        Map<String, Double> categoryTotals = new HashMap<>();
        for (Expense e : tracker.getExpenseHistory()) {
            categoryTotals.put(e.getCategory(),
                categoryTotals.getOrDefault(e.getCategory(), 0.0) + e.getAmount());
        }

        System.out.println("\nCategory-wise Summary:");
        for (Map.Entry<String, Double> entry : categoryTotals.entrySet()) {
            System.out.printf("%s: ₹%.2f%n", entry.getKey(), entry.getValue());
        }
    }

    public void deleteExpenseByDescription(String desc) {
        List<Expense> toRemove = new ArrayList<>();
        for (Expense e : tracker.getExpenseHistory()) {
            if (e.getDescription().equalsIgnoreCase(desc)) {
                toRemove.add(e);
            }
        }

        if (toRemove.isEmpty()) {
            System.out.println("No expense found with that description.");
        } else {
            for (Expense e : toRemove) {
                tracker.removeExpense(e);
            }
            System.out.println(toRemove.size() + " expense(s) deleted.");
        }
    }

    public void showMonthlySummary(String month) {
        double total = 0.0;
        for (Expense e : tracker.getExpenseHistory()) {
            if (e.getDate().startsWith(month)) { // format yyyy-mm
                total += e.getAmount();
                System.out.println(e);
                System.out.println();
            }
        }
        System.out.printf("Total spent in %s: ₹%.2f%n", month, total);
    }
}
