import java.util.Objects;

public class Expense {
    private String description;
    private double amount;
    private String category;
    private String date;

    // Constructor
    public Expense(String description, double amount, String category, String date) {
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    // Getters
    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    // Override toString() for a better output format
    @Override
    public String toString() {
        return "Description: " + description + "\nAmount: " + amount + "\nCategory: " + category + "\nDate: " + date;
    }

    // Override equals and hashCode to compare expenses based on description, amount, and category
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Expense expense = (Expense) obj;
        return Double.compare(expense.amount, amount) == 0 &&
               Objects.equals(description, expense.description) &&
               Objects.equals(category, expense.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, amount, category);
    }
}
