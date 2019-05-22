/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controller;

import br.com.darko.Login;
import br.com.darko.Principal;
import br.com.jdbc.ConnectionFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextFormatter;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

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
    private JFXPasswordField txtSenha;

    @FXML
    private JFXButton btnLogar;

    static int iduser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        apEntrarNovamente.setDisable(true);
        preencherCirculoFoto();
        definirUpperCaseTextField();

        btnLogar.setOnMouseClicked((MouseEvent e) -> {
            logar();
        });

    }

    public void preencherCirculoFoto() {

        Image im = new Image("/br/com/images/photoless.png", false);
        circFoto.setFill(new ImagePattern(im));
        circFoto.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));

    }

    public void definirUpperCaseTextField() {

        txtLogin.setTextFormatter(new TextFormatter<>((change) -> {
            change.setText(change.getText().toUpperCase());
            return change;
        }));

    }

    public void logar() {

        Connection con = ConnectionFactory.conector();
        PreparedStatement pst = null;
        ResultSet rs = null;

        String login = txtLogin.getText();
        String senha = txtSenha.getText();
        String sql = "select * from usuario where login = ? and senha = ?";

        if (!txtLogin.getText().isEmpty() && !txtSenha.getText().isEmpty()) {

            try {

                pst = con.prepareStatement(sql);
                pst.setString(1, login.toLowerCase());
                pst.setString(2, senha.toLowerCase());
                rs = pst.executeQuery();

                if (rs.next()) {
                    String sql2 = "call Definir_UltimoLogado(" + rs.getString("id_usuario") + ")";

                    try {

                        PreparedStatement pst2;
                        pst2 = con.prepareStatement(sql2);
                        pst2.executeUpdate();

                    } catch (SQLException e) {
                        System.out.println(e);
                    }

                }

            } catch (SQLException e) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Não foi possível realizar uma ação:");
                alert.setContentText(e.toString());
                alert.showAndWait();
            }
        }
        logarListarClientes();
    }

    public void fecharTelaLogin() {

        Login login = new Login();
        login.getStage().close();

    }

    private void logarListarClientes() {
        Principal p = new Principal();
        try {
            p.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        fecharTelaLogin();
    }

}
