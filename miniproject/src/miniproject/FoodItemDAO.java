package miniproject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodItemDAO {
    // Create a new food item in the database
    public void createFoodItem(Food foodItem) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO food_items (name, price) VALUES (?, ?)");
        preparedStatement.setString(1, foodItem.getName());
        preparedStatement.setDouble(2, foodItem.getPrice());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }

    // Retrieve a food item by its ID
    public Food getFoodItemById(int id) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM food_items WHERE id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            Food foodItem = new Food();
            foodItem.setId(resultSet.getInt("id"));
            foodItem.setName(resultSet.getString("name"));
            foodItem.setPrice(resultSet.getDouble("price"));
            // Set other attributes as needed
            preparedStatement.close();
            connection.close();
            return foodItem;
        } else {
            preparedStatement.close();
            connection.close();
            return null;
        }
    }

    // Retrieve all food items from the database
    public List<Food> getAllFoodItems() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM food_items");
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Food> foodItems = new ArrayList<>();
        while (resultSet.next()) {
            Food foodItem = new Food();
            foodItem.setId(resultSet.getInt("id"));
            foodItem.setName(resultSet.getString("name"));
            foodItem.setPrice(resultSet.getDouble("price"));
            // Set other attributes as needed
            foodItems.add(foodItem);
        }
        preparedStatement.close();
        connection.close();
        return foodItems;
    }

    // Update an existing food item in the database
    public void updateFoodItem(Food foodItem) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE food_items SET name = ?, price = ? WHERE id = ?");
        preparedStatement.setString(1, foodItem.getName());
        preparedStatement.setDouble(2, foodItem.getPrice());
        preparedStatement.setInt(3, foodItem.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }

    // Delete a food item from the database by its ID
    public void deleteFoodItem(int id) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM food_items WHERE id = ?");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }
}

