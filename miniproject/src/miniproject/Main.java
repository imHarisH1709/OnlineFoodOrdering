package miniproject;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FoodItemDAO foodItemDAO = new FoodItemDAO();

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Add Food Item");
            System.out.println("2. View Food Item by ID");
            System.out.println("3. View All Food Items");
            System.out.println("4. Update Food Item");
            System.out.println("5. Delete Food Item");
            System.out.println("0. Exit");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            try {
                switch (option) {
                    case 1:
                        System.out.print("Enter Food Item Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Food Item Price: ");
                        double price = scanner.nextDouble();
                        scanner.nextLine(); // Consume the newline character

                        Food newFoodItem = new Food();
                        newFoodItem.setName(name);
                        newFoodItem.setPrice(price);
                        foodItemDAO.createFoodItem(newFoodItem);
                        System.out.println("Food Item added successfully!");
                        break;

                    case 2:
                        System.out.print("Enter Food Item ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character

                        Food retrievedFoodItem = foodItemDAO.getFoodItemById(id);
                        if (retrievedFoodItem != null) {
                            System.out.println(retrievedFoodItem);
                        } else {
                            System.out.println("Food Item not found!");
                        }
                        break;

                    case 3:
                        List<Food> allFoodItems = foodItemDAO.getAllFoodItems();
                        for (Food foodItem : allFoodItems) {
                            System.out.println(foodItem);
                            System.out.println("-------------------------");
                        }
                        break;

                    case 4:
                        System.out.print("Enter Food Item ID to update: ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character

                        Food updatedFoodItem = foodItemDAO.getFoodItemById(updateId);
                        if (updatedFoodItem != null) {
                            System.out.print("Enter new Food Item Name: ");
                            String newName = scanner.nextLine();
                            System.out.print("Enter new Food Item Price: ");
                            double newPrice = scanner.nextDouble();
                            scanner.nextLine(); // Consume the newline character

                            updatedFoodItem.setName(newName);
                            updatedFoodItem.setPrice(newPrice);
                            foodItemDAO.updateFoodItem(updatedFoodItem);
                            System.out.println("Food Item updated successfully!");
                        } else {
                            System.out.println("Food Item not found!");
                        }
                        break;

                    case 5:
                        System.out.print("Enter Food Item ID to delete: ");
                        int deleteId = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character
                        foodItemDAO.deleteFoodItem(deleteId);
                        System.out.println("Food Item deleted successfully!");
                        break;

                    case 0:
                        System.out.println("Exiting the application...");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }
}
