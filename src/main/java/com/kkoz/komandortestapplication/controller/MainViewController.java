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
import java.util.stream.Collectors;

public class MainViewController {

    List<GoodsTable> goods = new ArrayList<>();
    List<GoodsTable> filteredGoods = new ArrayList<>();
    List<ShoppingTable> cart = new ArrayList<>();

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
    public void initialize() {
        goods.add(new GoodsTable("Test1", BigDecimal.valueOf(150)));
        goods.add(new GoodsTable("Test2", BigDecimal.valueOf(150.99)));
        goods.add(new GoodsTable("Test3", BigDecimal.valueOf(50.99)));
        goods.add(new GoodsTable("Test4", BigDecimal.valueOf(1550)));

        filteredGoods.addAll(goods);

        productGoodsColumn.setCellValueFactory(new PropertyValueFactory<GoodsTable, String>("product"));
        costGoodsColumn.setCellValueFactory(new PropertyValueFactory<GoodsTable, BigDecimal>("cost"));

        goodsList.setItems(FXCollections.observableArrayList(filteredGoods));

        productShoppingColumn.setCellValueFactory(new PropertyValueFactory<ShoppingTable, String>("product"));
        amountShoppingColumn.setCellValueFactory(new PropertyValueFactory<ShoppingTable, Integer>("amount"));
        costShoppingColumn.setCellValueFactory(new PropertyValueFactory<ShoppingTable, BigDecimal>("sumCost"));

        shoppingList.setItems(FXCollections.observableArrayList(cart));
    }

    @FXML
    protected void onSearchFieldChanged() {
        filteredGoods.clear();
        filteredGoods.addAll(goods.stream().filter(g -> g.getProduct().contains(searchField.getText())).collect(Collectors.toList()));
        updateGoods();
    }

    @FXML
    protected void onGoodsListClicked() {
        GoodsTable item = goodsList.getSelectionModel().getSelectedItem();
        addToCart(item);
        updateCart();
    }

    @FXML
    protected void onShoppingListClicked() {
        ShoppingTable item = shoppingList.getSelectionModel().getSelectedItem();
        removeFromCart(item);
        updateCart();
    }

    @FXML
    protected void onPayButtonAction() {

    }

    private void addToCart(GoodsTable item) {
        for (ShoppingTable shoppingElement:
             cart) {
            if (shoppingElement.equals(item)) {
                shoppingElement.incAmount();
                return;
            }
        }
        cart.add(new ShoppingTable(item));
    }

    private void removeFromCart(ShoppingTable item) {
        for (ShoppingTable shoppingElement:
                cart) {
            if (shoppingElement.equals(item)) {
                if (shoppingElement.decAmount()) {
                    cart.remove(shoppingElement);
                }
                return;
            }
        }
    }

    private void updateGoods() {
        goodsList.getItems().clear();
        goodsList.setItems(FXCollections.observableArrayList(filteredGoods));
    }

    private void updateCart() {
        shoppingList.getItems().clear();
        shoppingList.setItems(FXCollections.observableArrayList(cart));
        sumField.setText(cart.stream().map(ShoppingTable::getSumCost).reduce(BigDecimal.ZERO, BigDecimal::add).toString());
    }
}