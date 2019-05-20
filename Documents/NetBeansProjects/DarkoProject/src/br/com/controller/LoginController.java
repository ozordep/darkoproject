/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controller;

import br.com.darko.Login;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.awt.Cursor;
import java.awt.Desktop;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextFormatter;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 *
 * @author jpped
 */
public class LoginController implements Initializable {

    @FXML
    private AnchorPane apEntrarNovamente;

    @FXML
    private Circle circFoto;

    @FXML
    private JFXButton btnEntrarRecente;

    @FXML
    private JFXTextField txtLogin;

    @FXML
    private JFXTextField txtSenha;

    @FXML
    private JFXButton btnLogar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        apEntrarNovamente.setDisable(true);
        preencherCirculoFoto();

        txtLogin.setTextFormatter(new TextFormatter<>((change) -> {
            change.setText(change.getText().toUpperCase());
            return change;
        }));

        btnLogar.setOnMouseDragOver((MouseEvent e) -> {

        });

    }

    public void preencherCirculoFoto() {

        Image im = new Image("/br/com/images/photoless.png", false);
        circFoto.setFill(new ImagePattern(im));
        circFoto.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));

    }

}
