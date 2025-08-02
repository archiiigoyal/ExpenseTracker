import java.util.*;

public class ExpenseTracker {

    private List<Expense> expenseHistory;
    private Set<Expense> expenseSet;
    private PriorityQueue<Expense> priorityQueue;

    public ExpenseTracker() {
        expenseHistory = new ArrayList<>();
        expenseSet = new HashSet<>();
        priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(Expense::getAmount).reversed());
    }

    public void addExpense(String description, double amount, String category, String date) {
        Expense newExpense = new Expense(description, amount, category, date);

        if (!expenseSet.contains(newExpense)) {
            expenseHistory.add(newExpense);
            expenseSet.add(newExpense);
            priorityQueue.add(newExpense);
            System.out.println("Expense added successfully!");
        } else {
            System.out.println("Duplicate expense. Not added.");
        }
    }

    public Expense getMostExpensiveExpense() {
        return priorityQueue.peek();
    }

    public void displayExpenses() {
        if (expenseHistory.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }

        for (Expense e : expenseHistory) {
            System.out.println(e);
            System.out.println("-----------------------------");
        }
    }

    public void displayTopExpenses(int n) {
        if (priorityQueue.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }

        System.out.println("Top " + n + " Most Expensive Expenses:\n");
        PriorityQueue<Expense> tempQueue = new PriorityQueue<>(priorityQueue);
        for (int i = 0; i < n && !tempQueue.isEmpty(); i++) {
            System.out.println(tempQueue.poll());
            System.out.println();
        }
    }

    public void clearAllExpenses() {
        expenseHistory.clear();
        expenseSet.clear();
        priorityQueue.clear();
        System.out.println("All expenses cleared.");
    }

    public List<Expense> getExpenseHistory() {
        return new ArrayList<>(expenseHistory);
    }

    public void removeExpense(Expense e) {
        expenseHistory.remove(e);
        expenseSet.remove(e);
        priorityQueue.remove(e);
    }
}
