package org.example;


import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/sampledb";
        String username = "root";
        String password = "Roman2022!";

        System.out.println("Loading driver...");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }

        Connection connection = DriverManager.getConnection(url, username, password);

        String sql = "INSERT INTO Users (username, password, fullname, email) VALUES (?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "bill");
        statement.setString(2, "secretpass");
        statement.setString(3, "Bill Gates");
        statement.setString(4, "bill.gates@microsoft.com");

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new user was inserted successfully!");
        }

        String sql2 = "SELECT * FROM Users";

        Statement statement2 = connection.createStatement();
        ResultSet result = statement.executeQuery(sql2);

        int count = 0;

        while (result.next()) {
            String name = result.getString(2);
            String pass = result.getString(3);
            String fullname = result.getString("fullname");
            String email = result.getString("email");

            String output = "User #%d: %s - %s - %s - %s";
            System.out.println(String.format(output, ++count, name, pass, fullname, email));
        }


    }
}
