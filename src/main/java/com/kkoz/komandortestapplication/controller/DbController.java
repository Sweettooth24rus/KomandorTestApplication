package com.kkoz.komandortestapplication.controller;

import com.kkoz.komandortestapplication.model.tables.GoodsTable;
import com.kkoz.komandortestapplication.model.tables.ShoppingTable;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DbController {

    /**
     * Правильнее будет через переменные среды делать
     */

    private final String url = "jdbc:postgresql://localhost:5432/coffee";
    private final String user = "db_user";
    private final String password = "db_pwd";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public List<GoodsTable> getGoods() {
        List<GoodsTable> goods = new ArrayList<>();

        String SQL = "SELECT * FROM cashtest.goods";

        try {
            ResultSet resultSet = connect().createStatement().executeQuery(SQL);

            while (resultSet.next()) {
                goods.add(new GoodsTable(resultSet.getString("name"), (UUID)resultSet.getObject("id"), resultSet.getBigDecimal("cost")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return goods;
    }

    public void addCheck(List<ShoppingTable> cart, BigDecimal sum) {

        String SQL = "INSERT INTO cashtest.checks (id, date, time, sum) VALUES (?, ?, ?, ?)";

        UUID checkUUID = UUID.randomUUID();

        try {
            PreparedStatement preparedStatement = connect().prepareStatement(SQL);

            preparedStatement.setObject(1, checkUUID);
            preparedStatement.setDate(2, Date.valueOf(LocalDate.now()));
            preparedStatement.setTime(3, Time.valueOf(LocalTime.now()));
            preparedStatement.setBigDecimal(4, sum);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }

        int index = 0;
        SQL = "INSERT INTO cashtest.checklines (id, check_id, row_number, product_id, amount, row_sum) VALUES (?, ?, ?, ?, ?, ?)";

        for (ShoppingTable shoppingTable:
             cart) {
            try {
                PreparedStatement preparedStatement = connect().prepareStatement(SQL);

                preparedStatement.setObject(1, UUID.randomUUID());
                preparedStatement.setObject(2, checkUUID);
                preparedStatement.setInt(3, index);
                preparedStatement.setObject(4, shoppingTable.getProductId());
                preparedStatement.setInt(5, shoppingTable.getAmount());
                preparedStatement.setBigDecimal(6, shoppingTable.getSumCost());

                preparedStatement.executeUpdate();

                index++;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
