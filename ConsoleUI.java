import java.util.Scanner;

public class ConsoleUI {
    private ExpenseTracker tracker;
    private ExpenseManager manager;
    private Scanner scanner;

    public ConsoleUI() {
        tracker = new ExpenseTracker();
        manager = new ExpenseManager(tracker);
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            printMenu();
            int choice = getInt("Select an option: ");

            switch (choice) {
                case 1 -> addExpense();
                case 2 -> tracker.displayExpenses();
                case 3 -> tracker.displayTopExpenses(getInt("Top how many? "));
                case 4 -> viewMostExpensive();
                case 5 -> manager.showCategorySummary();
                case 6 -> manager.showMonthlySummary(getString("Enter month (yyyy-mm): "));
                case 7 -> manager.deleteExpenseByDescription(getString("Enter description to delete: "));
                case 8 -> clearAll();
                case 9 -> exit();
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\nExpense Tracker Menu:");
        System.out.println("1. Add Expense");
        System.out.println("2. Show All Expenses");
        System.out.println("3. Show Top N Expenses");
        System.out.println("4. View Most Expensive");
        System.out.println("5. Category Summary");
        System.out.println("6. Monthly Report");
        System.out.println("7. Delete by Description");
        System.out.println("8. Clear All Expenses");
        System.out.println("9. Exit");
    }

    private void addExpense() {
        String desc = getString("Enter description: ");
        double amount = getDouble("Enter amount: ");
        String category = getString("Enter category: ");
        String date = getString("Enter date (yyyy-mm-dd): ");
        tracker.addExpense(desc, amount, category, date);
    }

    private void viewMostExpensive() {
        Expense top = tracker.getMostExpensiveExpense();
        if (top != null) {
            System.out.println("\nMost Expensive Expense:\n" + top);
        } else {
            System.out.println("No expenses recorded.");
        }
    }

    private void clearAll() {
        String confirm = getString("Are you sure? (yes/no): ");
        if (confirm.equalsIgnoreCase("yes") || confirm.equalsIgnoreCase("y")) {
            tracker.clearAllExpenses();
            System.out.println("All expenses cleared.");
        } else {
            System.out.println("Cancelled.");
        }
    }

    private void exit() {
        System.out.println("Exiting. Goodbye!");
        scanner.close();
        System.exit(0);
    }

    // Utility Input Methods
    private String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private int getInt(String prompt) {
        System.out.print(prompt);
        return Integer.parseInt(scanner.nextLine());
    }

    private double getDouble(String prompt) {
        System.out.print(prompt);
        return Double.parseDouble(scanner.nextLine());
    }
}
