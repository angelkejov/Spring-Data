package com.company.Exercise;

import java.sql.*;
import java.util.Properties;

public class GetVillainsNames {

    public static void main(String[] args) throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "1234");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        PreparedStatement statement = connection.prepareStatement("SELECT name, COUNT(distinct mv.minion_id) as minion_count from villains as v\n" +
                "                JOIN minions_villains as mv on mv.villain_id = v.id\n" +
                "                GROUP BY mv.villain_id\n" +
                "                HAVING minion_count > 15\n" +
                "                ORDER BY minion_count DESC;");

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String dbVillainName = resultSet.getString("name");
            int dbNumberOfMinions = resultSet.getInt("minion_count");

            System.out.printf("%s %d", dbVillainName, dbNumberOfMinions);
        }
    }
}
