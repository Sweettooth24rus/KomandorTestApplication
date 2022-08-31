package com.kkoz.komandortestapplication.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.math.BigDecimal;

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
    protected void onOkButtonAction() {
        if (new BigDecimal(sumField.getText()).equals(sumFromGoods)) {
            shopController.addCheck();
            shopController.clearAll();
        }
        Stage stage = (Stage) sumField.getScene().getWindow();
        stage.close();
    }
}