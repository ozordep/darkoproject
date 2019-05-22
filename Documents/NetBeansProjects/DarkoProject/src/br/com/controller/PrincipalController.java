/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controller;

import br.com.darko.Principal;
import br.com.jdbc.ConnectionFactory;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 *
 * @author jpped
 */
public class PrincipalController implements Initializable {

    @FXML
    private Pane paneProdutos;

    @FXML
    private Pane paneClientes;

    @FXML
    private Pane paneVendas;

    @FXML
    private Pane paneHome;

    @FXML
    private AnchorPane paneSide;

    @FXML
    private JFXButton btnHome;

    @FXML
    private JFXButton btnProdutos;

    @FXML
    private JFXButton btnClientes;

    @FXML
    private JFXButton btnVendas;

    @FXML
    private JFXButton btnSair;

    @FXML
    private FontAwesomeIconView icnHome;

    @FXML
    private Label lblNome;

    @FXML
    private Circle circFoto;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnSair.setFocusTraversable(false);
        carregarInformacoesUsuarioLogado();

        btnHome.setOnMouseClicked((MouseEvent e) -> {
            paneHome.toFront();
            paneSide.toFront();
        });

        btnProdutos.setOnMouseClicked((MouseEvent e) -> {
            paneProdutos.toFront();
            paneSide.toFront();
        });

        btnClientes.setOnMouseClicked((MouseEvent e) -> {
            paneClientes.toFront();
            paneSide.toFront();
        });

        btnVendas.setOnMouseClicked((MouseEvent e) -> {
            paneVendas.toFront();
            paneSide.toFront();
        });

        btnSair.setOnMouseClicked((MouseEvent e) -> {
            fecharTelaPrincipal();
        });

    }

    private void carregarInformacoesUsuarioLogado() {

        Connection con = ConnectionFactory.conector();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select * from usuario where ultimoLogado= true";

        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {

                File file = new File(rs.getString("img"));
                System.out.println(rs.getString("img"));
                Image image = new Image(file.toURI().toString());
                circFoto.setFill(new ImagePattern(image));

                lblNome.setText(rs.getString("nome"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void fecharTelaPrincipal() {

        Principal p = new Principal();

        p.getStage().close();

    }

}
