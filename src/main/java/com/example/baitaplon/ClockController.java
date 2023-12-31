package com.example.baitaplon;

import animatefx.animation.BounceInDown;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClockController {
    @FXML
    private Line hourHand;

    @FXML
    private Line minuteHand;

    @FXML
    private Line secondHand;

    @FXML
    private Label dateText;

    @FXML
    private ListView<String>  listView;
    @FXML
    private Label lableNghia;
    @FXML
    private TextField dateField;

    public void initialize() throws IOException {
        // tao timeline cap nhat thoi gian 1s 1 lan
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> updateClock())
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        // cap nhat ngay thang nam
        updateDateField();
        //doc tu
        if (HomeController.isLoadDataOfSoTuCaNhan == false) {
            DataBase.loadDataSqlOfSoTuCaNhan();
            HomeController.isLoadDataOfSoTuCaNhan = true;
        }
        this.listView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    String text = newValue.trim();
                    WordSQL selectedWord = ControllerSoTayCaNhan.dataSoTu.get(text);
                    //lay nghia cua tu
                    String definition = selectedWord.getWord_explain();
                    new BounceInDown(lableNghia).play();
                    lableNghia.setText(definition);
//                    this.lableNghia.getEngine().loadContent(definition, "text/html");
                }
        );
        this.listView.getItems().addAll(ControllerSoTayCaNhan.dataSoTu.keySet());
    }

    private void updateClock() {
        // Lấy thời gian hiện tại
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedTime = dateFormat.format(now);

        //hien thi thoi gian bang so
        dateText.setText(formattedTime);

        // hien thi ngay duoc chon DatePicker
//        if (datePicker.getValue() != null) {
//            Date selectedDate = java.sql.Date.valueOf(datePicker.getValue());
//            SimpleDateFormat selectedDateFormat = new SimpleDateFormat("dd-MM-yyyy");
//            String selectedFormattedDate = selectedDateFormat.format(selectedDate);
//            dateText.setText(selectedFormattedDate + " " + formattedTime);
//        }

        // update kim
        double second = now.getSeconds();
        double minute = now.getMinutes() + second / 60.0;
        double hour = now.getHours() % 12 + minute / 60.0;

        updateHand(hourHand, hour * 30);
        updateHand(minuteHand, minute * 6);
        updateHand(secondHand, second * 6);
    }

    private void updateHand(Line hand, double angle) {
        double handLength = 80;
        double centerX = 150;
        double centerY = 150;

        double endX = centerX + handLength * Math.sin(Math.toRadians(angle));
        double endY = centerY - handLength * Math.cos(Math.toRadians(angle));

        hand.setEndX(endX);
        hand.setEndY(endY);
    }

    private void updateDateField() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = dateFormat.format(now);
        dateField.setText(formattedDate);
    }
}
