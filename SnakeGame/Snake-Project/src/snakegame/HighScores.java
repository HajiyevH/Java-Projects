package snakegame;

import java.sql.*;
import java.util.ArrayList;

public class HighScores {
    private final Connection connection;
    private final PreparedStatement insertStatement;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/leaderboard"; 
    private static final String USER = "root";
    private static final String PASSWORD = "qwerty"; 

    public HighScores() throws SQLException {
        connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);

        String createTableQuery = "CREATE TABLE IF NOT EXISTS leaderboard (" +
                                  "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                  "name VARCHAR(255) NOT NULL, " +
                                  "score INT NOT NULL, " +
                                  "timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
        Statement statement = connection.createStatement();
        statement.execute(createTableQuery);

        String insertQuery = "INSERT INTO leaderboard (name, score) VALUES (?, ?)";
        insertStatement = connection.prepareStatement(insertQuery);
    }

    public void saveScore(String name, int score) throws SQLException {
        insertStatement.setString(1, name);
        insertStatement.setInt(2, score);
        insertStatement.executeUpdate();
    }

    public ArrayList<String> getTopScores(int limit) throws SQLException {
        ArrayList<String> highScores = new ArrayList<>();
        String query = "SELECT name, score, timestamp FROM leaderboard ORDER BY score DESC LIMIT ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, limit);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int score = resultSet.getInt("score");
            String timestamp = resultSet.getString("timestamp");
            highScores.add(name + ": " + score + " (at " + timestamp + ")");
        }
        return highScores;
    }

    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}