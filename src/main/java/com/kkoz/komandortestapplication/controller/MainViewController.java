package com.kkoz.komandortestapplication.controller;

import com.kkoz.komandortestapplication.model.tables.GoodsTable;
import com.kkoz.komandortestapplication.model.tables.ShoppingTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MainViewController {
    @FXML
    private TextField searchField;

    @FXML
    public TableColumn<GoodsTable, String> productGoodsColumn;
    @FXML
    public TableColumn<GoodsTable, BigDecimal> costGoodsColumn;
    @FXML
    private TableView<GoodsTable> goodsList;

    @FXML
    public TableColumn<ShoppingTable, String> productShoppingColumn;
    @FXML
    public TableColumn<ShoppingTable, Integer> amountShoppingColumn;
    @FXML
    public TableColumn<ShoppingTable, BigDecimal> costShoppingColumn;
    @FXML
    private TableView<ShoppingTable> shoppingList;

    @FXML
    private Text sumField;

    @FXML
    private Button payButton;

    @FXML
    protected void onSearchFieldChanged() {

    }

    @FXML
    protected void onGoodsListClicked() {
        List<GoodsTable> test = new ArrayList<>();
        test.add(new GoodsTable("Test1", BigDecimal.valueOf(150)));
        test.add(new GoodsTable("Test3", BigDecimal.valueOf(150.99)));
        ObservableList<GoodsTable> ttt = FXCollections.observableArrayList(test);

        productGoodsColumn.setCellValueFactory(new PropertyValueFactory<GoodsTable, String>("product"));
        costGoodsColumn.setCellValueFactory(new PropertyValueFactory<GoodsTable, BigDecimal>("cost"));
        goodsList.setItems(ttt);
    }

    @FXML
    protected void onShoppingListClicked() {
        List<GoodsTable> test = new ArrayList<>();
        test.add(new GoodsTable("Test1", BigDecimal.valueOf(150)));
        test.add(new GoodsTable("Test3", BigDecimal.valueOf(150.99)));
        ObservableList<GoodsTable> ttt = FXCollections.observableArrayList(test);

        productGoodsColumn.setCellValueFactory(new PropertyValueFactory<GoodsTable, String>("product"));
        costGoodsColumn.setCellValueFactory(new PropertyValueFactory<GoodsTable, BigDecimal>("cost"));
        goodsList.setItems(ttt);
    }

    @FXML
    protected void onPayButtonAction() {
        List<GoodsTable> test = new ArrayList<>();
        test.add(new GoodsTable("Test1", BigDecimal.valueOf(150)));
        test.add(new GoodsTable("Test3", BigDecimal.valueOf(150.99)));
        ObservableList<GoodsTable> ttt = FXCollections.observableArrayList(test);

        productGoodsColumn.setCellValueFactory(new PropertyValueFactory<GoodsTable, String>("product"));
        costGoodsColumn.setCellValueFactory(new PropertyValueFactory<GoodsTable, BigDecimal>("cost"));
        goodsList.setItems(ttt);
    }
}