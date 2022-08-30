package com.kkoz.komandortestapplication.controller;

import com.kkoz.komandortestapplication.Main;
import com.kkoz.komandortestapplication.model.tables.GoodsTable;
import com.kkoz.komandortestapplication.model.tables.ShoppingTable;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShopController {

    private List<GoodsTable> goods = new ArrayList<>();
    private List<GoodsTable> filteredGoods = new ArrayList<>();
    private List<ShoppingTable> cart = new ArrayList<>();

    @FXML
    private TextField searchField;

    @FXML
    private TableColumn<GoodsTable, String> productGoodsColumn;
    @FXML
    private TableColumn<GoodsTable, BigDecimal> costGoodsColumn;
    @FXML
    private TableView<GoodsTable> goodsList;

    @FXML
    private TableColumn<ShoppingTable, String> productShoppingColumn;
    @FXML
    private TableColumn<ShoppingTable, Integer> amountShoppingColumn;
    @FXML
    private TableColumn<ShoppingTable, BigDecimal> costShoppingColumn;
    @FXML
    private TableView<ShoppingTable> shoppingList;

    @FXML
    private Text sumField;

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
    public void onSearchFieldChanged() {
        filteredGoods.clear();
        filteredGoods.addAll(goods.stream().filter(g -> g.getProduct().contains(searchField.getText())).collect(Collectors.toList()));
        updateGoods();
    }

    @FXML
    public void onGoodsListClicked() {
        GoodsTable item = goodsList.getSelectionModel().getSelectedItem();
        addToCart(item);
        updateCart();
    }

    @FXML
    public void onShoppingListClicked() {
        ShoppingTable item = shoppingList.getSelectionModel().getSelectedItem();
        removeFromCart(item);
        updateCart();
    }

    @FXML
    public void onPayButtonAction() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("PaymentView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        fxmlLoader.<PaymentController>getController().setVars(this, sumField.getText());

        stage.setTitle("Payment");
        stage.setScene(scene);
        stage.show();
    }

    public void addToCart(GoodsTable item) {
        for (ShoppingTable shoppingElement:
             cart) {
            if (shoppingElement.equals(item)) {
                shoppingElement.incAmount();
                return;
            }
        }
        cart.add(new ShoppingTable(item));
    }

    public void removeFromCart(ShoppingTable item) {
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

    public void updateGoods() {
        goodsList.getItems().clear();
        goodsList.setItems(FXCollections.observableArrayList(filteredGoods));
    }

    public void updateCart() {
        shoppingList.getItems().clear();
        shoppingList.setItems(FXCollections.observableArrayList(cart));
        sumField.setText(cart.stream().map(ShoppingTable::getSumCost).reduce(BigDecimal.ZERO, BigDecimal::add).toString());
    }

    public void clearAll() {
        cart.clear();
        updateCart();
    }
}