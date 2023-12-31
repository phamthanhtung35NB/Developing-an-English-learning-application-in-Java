package com.example.baitaplon;

//import com.example.baitaplon.Game.*;
//import javafx.scene.control.*;

import animatefx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class HomeController {
    public static boolean isLoadData = false;
    public static boolean isLoadDataOfSoTuCaNhan = false;

    @FXML
    public SplitPane setupGame;
    @FXML
    public Slider difficultySlider;
    // centerBorderPane laf view chinh cua BorderPane
    @FXML
    protected BorderPane centerBorderPane;
    @FXML
    private VBox vbox;
    @FXML
    private VBox vBoxMoTa;
    @FXML
    private VBox vBoxGame;
    @FXML
    private VBox vboxRight;
    @FXML
    private HBox hBoxButton;
    @FXML
    private ToggleButton isLockUnlockSetLeft;
    @FXML
    private Label title;
    private boolean isLeft = false;
    private boolean isSettingLock = false;
    private boolean isSetting = false;

    //nut ben phai
    @FXML
    public void buttonSetting() {
        if (isSetting == false) {
            new BounceInDown(vboxRight).play();
            centerBorderPane.setRight(vboxRight);
            isSetting = true;
            System.out.println("Setting");
        } else {
            centerBorderPane.setRight(null);
            isSetting = false;
            System.out.println("Setting");
        }
////            centerBorderPane.setLeft(vbox);
//            System.out.println("Cửa sổ Left đã mở");
//
//            isSettingLock = true;
//            isLock = true;
//
//        } else {
////            centerBorderPane.setLeft(null);
//            System.out.println("Cửa sổ Left đã đóng");
//            isSettingLock = false;
//            isLock = false;
//        }
    }

    @FXML
    public void initialize() throws IOException {
        ControllerLogin.isHomeController = true;

        centerBorderPane.setRight(null);
        centerBorderPane.setLeft(null);
        buttonHome();
        title.setText("Hello "+ DataBase.UserName);
        ////////////////////////SET UP MOUSE LEFT///////////////////////////
//        centerBorderPane.setOnMouseMoved(event -> {
//            int x = (int)event.getX();
//            int y = (int) event.getY();
//            if ((x>=0&&x < 100)&&(y>=0&&y<750)) {
//                if (centerBorderPane.getLeft() == null) {
//                    centerBorderPane.setLeft(vbox);
//                    System.out.println("show");
//                }
//            }else if ((x > 240&&x<1320)&&(y>=0&&y<750)&&(isLock==false||isSettingLock==false)){
//                if (centerBorderPane.getLeft() != null) {
//                    centerBorderPane.setLeft(null);
//                    System.out.println("hide");
//                }
//            }
////            System.out.println("Mouse Entered - X: " + x+ " Y: " + y );
//
//        });
    }

    @FXML
    protected void buttonHome() throws IOException {
//        System.out.println("Home");
        AnchorPane view = FXMLLoader.load(getClass().getResource("TabHome.fxml"));
        view.getStylesheets().add(getClass().getResource("TabHome.css").toExternalForm());
        new ZoomIn(view).play();
        centerBorderPane.setCenter(view);
        title.setText("Home");
    }

    @FXML
    protected void buttonSearch() throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("FileSql.fxml"));
        new BounceInDown(view).play();
        view.getStylesheets().add(getClass().getResource("FileSql.css").toExternalForm());
        centerBorderPane.setCenter(view);
        new BounceInLeft(title).play();
        title.setText("Search");
    }

    @FXML
    protected void buttonOnlineSearch() throws IOException {
        Pane view = FXMLLoader.load(getClass().getResource("GoogleApi.fxml"));
        new ZoomIn(view).play();
        centerBorderPane.setCenter(view);
        new BounceInLeft(title).play();
        title.setText("Online Search");
    }

    //tap game
    @FXML
    protected void buttonGame() throws IOException {
        centerBorderPane.setCenter(vBoxGame);
        vBoxGame.getChildren().clear();
        new BounceInLeft(vBoxGame).play();
        vBoxGame.getChildren().add(hBoxButton);
        new BounceInLeft(title).play();
        title.setText("Game");
    }

    @FXML
    protected void buttonButton() throws IOException {
        System.out.println("buttonButton");
        System.out.println("1");
        AnchorPane view = FXMLLoader.load(getClass().getResource("SoTayCaNhan.fxml"));
        new FadeInDown(view).play();
        System.out.println("2");
        centerBorderPane.setCenter(view);
        System.out.println("3");
        new BounceInLeft(title).play();
        title.setText("Notebook");
    }

    @FXML
    protected void buttonFeedback() {
        System.out.println("Feed Back");
        String url = "https://github.com/phamthanhtung35NB/Developing-an-English-learning-application-in-Java";
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void buttonLoOut(ActionEvent event) {

        try {
            isLoadData = false;
            isLoadDataOfSoTuCaNhan = false;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage HomeStage = new Stage();
            HomeStage.setTitle("Login");
            HomeStage.setScene(scene);
            /**
             * Lấy Node từ sự kiện
             * Đối tượng event là sự kiện mà chúng ta đã gán cho nút đăng nhập
             * Đối tượng source là Node gốc mà sự kiện được phát ra
             * Trong trường hợp này, Node gốc là nút đăng nhập
             * Sau khi có Node gốc, chúng ta có thể lấy Stage từ Node gốc
             * Cuối cùng, chúng ta có thể đóng Stage
             */
            Node source = (Node) event.getSource();
            Stage currentStage = (Stage) source.getScene().getWindow();
            currentStage.close();
            new ZoomIn(root).play();
            HomeStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    protected void exit() {
        System.exit(0);
    }

    @FXML
    protected void buttonSettingLockUnlockSetLeft() {
        System.out.println("vao");
        if (isLeft == false) {
            isLeft = true;
            new BounceInDown(vbox).play();
            centerBorderPane.setLeft(vbox);
            System.out.println("show");
        } else {
            isLeft = false;
            centerBorderPane.setLeft(null);
            System.out.println("anr");
        }
//        if (isSettingLockUnlockSetLeft.isSelected()) {
////            centerBorderPane.setLeft(vbox);
//            System.out.println("Cửa sổ Left đã mở");
//
//            isSettingLock = true;
//            isLock = true;
//
//        } else {
////            centerBorderPane.setLeft(null);
//            System.out.println("Cửa sổ Left đã đóng");
//            isSettingLock = false;
//            isLock = false;
//        }
    }
/////////////////////////////////////////////////////////CONTROLLER GAME///////////////////////////////////////


    @FXML
    private void letGo() throws IOException {
        System.out.println("letGo started");
        ControllerHangman.difficultyHangman = (int) difficultySlider.getValue();
        GridPane view = FXMLLoader.load(getClass().getResource("Hangman.fxml"));
        view.getStylesheets().add(getClass().getResource("Hangman.css").toExternalForm());
        new ZoomIn(view).play();
        centerBorderPane.setCenter(view);
        new BounceInLeft(title).play();
        title.setText("Hangman");
    }

    @FXML
    private void hangman() throws IOException {
        System.out.println("buttonButton");
        vBoxGame.getChildren().clear();
        new BounceInDown(vBoxMoTa).play();
        new BounceInDown(hBoxButton).play();
        vBoxGame.getChildren().add(vBoxMoTa);
        vBoxGame.getChildren().add(hBoxButton);
        new BounceInLeft(title).play();
        title.setText("Setup Hangman");
    }

    ////////////////////////////GAME TU//////////////////////////////////////
    @FXML
    private void pronounce() throws IOException {
        GridPane view = FXMLLoader.load(getClass().getResource("GamePron.fxml"));
        view.getStylesheets().add(getClass().getResource("GamePron.css").toExternalForm());
        new ZoomIn(view).play();
        centerBorderPane.setCenter(view);
        new BounceInLeft(title).play();
        title.setText("Pronounce");
    }

}