package com.kkoz.komandortestapplication.controller;

import com.kkoz.komandortestapplication.Main;
import com.kkoz.komandortestapplication.model.tables.GoodsTable;
import com.kkoz.komandortestapplication.model.tables.ShoppingTable;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentController {

    private BigDecimal sumFromGoods;

    private ShopController shopController;

    @FXML
    private TextField sumField;

    public void setVars(ShopController shopController, String sum) {
        this.shopController = shopController;
        sumFromGoods = new BigDecimal(sum);
        sumField.setText("0");
    }

    @FXML
    protected void onOkButtonAction() throws IOException {
        if (new BigDecimal(sumField.getText()).equals(sumFromGoods)) {
            shopController.clearAll();
        }
        Stage stage = (Stage) sumField.getScene().getWindow();
        stage.close();
    }
}