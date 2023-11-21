package com.example.baitaplon;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.TreeMap;

public class ControllerTabSql extends Application {
    //0==add,1==set,2==delete
    private int idTapPane=0;
    @FXML
    protected BorderPane borderPane;
    @FXML
    protected TabPane tabPane;
    @FXML
    protected HBox hBoxLeft;
    private boolean isLock = false;
    protected static TreeMap<String, WordSQL> dataWordinSql = new TreeMap<>();
    @FXML
    private ListView<String> listViewA;

    //text ,id la tu can doc/xoa/ them vao so tu ca nhan
    private String text="";
    private static int idCanXoa;
    @FXML
    private WebView webViewNghia;
    @FXML
    private TextField searchTra;
    /**
     * tab add.
     */
    @FXML
    private TextField textWordTargetAdd;
    @FXML
    private TextField textWordExplainAdd;
    @FXML
    private TextField textWordPronounceAdd;
    @FXML
    private TextField textWordLoaiTu;
    /**
     * tab set.
     */
    @FXML
    private TextField textIdSet;
    @FXML
    private TextField textWordTargetSet;
    @FXML
    private TextField textWordExplainSet;

    /**
     * tab delete.
     */
    @FXML
    private TextField textIdDelete;
    @FXML
    private TextField textWordTargetDelete;
    @FXML
    private Label labelNoteDelete;

    @FXML
    public  void buttonAddNotebook(){
        DataBase.setSQLiteStuding_array(idCanXoa);
        HomeController.isLoadDataOfSoTuCaNhan=false;
        searchTra.setText("Add to notebook success");
    }
    //doc tu
    @FXML
    public void buttonSpeeck(){
        new TextToSpeech(text);
    }
    @FXML
    public void buttonReload(){
        if (HomeController.isLoadData==false){
            DictionaryManagement.dictionarySQLiteLoadAll();
            HomeController.isLoadData=true;
        }

        listViewA.getItems().clear();
        searchTra.setText("");
        loadWordList();
    }
    //khoi tao tu dong
    @FXML
    public void initialize() throws IOException {

        if (HomeController.isLoadData==false){
            DictionaryManagement.dictionarySQLiteLoadAll();
            HomeController.isLoadData=true;
        }
        initComponents();
        loadWordList();
        borderPane.setRight(null);
    }

    @FXML
    protected void clickCheck() {
        if (searchTra.getText().equals("Add to notebook success")){
            searchTra.setText("");
            listViewA.getItems().clear();
            loadWordList();
        }
        if (searchTra.getText().length() > 0) {

            try {
                text = searchTra.getText().toLowerCase();
                WordSQL selectedWord = dataWordinSql.get(text);
                //lay nghia cua tu
                String definition = selectedWord.getHtml();
                //lay id cua tu can xoa neu muon xoa
                idCanXoa = selectedWord.getID();
                System.out.println(idCanXoa);
                this.webViewNghia.getEngine().loadContent(definition, "text/html");
            } catch (Exception e) {
                System.out.println("WARNING");

                this.webViewNghia.getEngine().loadContent("\n\n Not in the dictionary", "text/html");
            }
        } else {
            DictionaryManagement.dictionarySQLiteLoadAll();
            listViewA.getItems().clear();
            loadWordList();
        }

    }

        @FXML
    protected void showHiden()  {
            if (isLock==false) {
                borderPane.setRight(hBoxLeft);
                isLock = true;
                System.out.println("show");
            } else {
                borderPane.setRight(null);
                isLock = false;
                System.out.println("hide");
            }
    }
    public void initComponents() {
        tabPane.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            idTapPane=newValue.intValue();
        });
        this.listViewA.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    text = newValue.trim();
                    WordSQL selectedWord = dataWordinSql.get(text);
                    //lay nghia cua tu
                    String definition = selectedWord.getHtml();
                    //lay id cua tu can xoa neu muon xoa
                    idCanXoa = selectedWord.getID();
                    System.out.println(idCanXoa);
                    this.webViewNghia.getEngine().loadContent(definition, "text/html");
                }
        );
        searchTra.textProperty().addListener((observable, oldValue, newValue) -> {
            String searchContent = searchTra.getText().toLowerCase();
            listViewA.getItems().clear();

            for (Word word : dataWordinSql.values()) {
                if (word.getWord_target().toLowerCase().startsWith(searchContent)) {
                    listViewA.getItems().add(word.getWord_target());
                    text = newValue.trim();
                    WordSQL selectedWord = dataWordinSql.get(text);
                    //lay nghia cua tu
                    String definition = selectedWord.getHtml();
                    //lay id cua tu can xoa neu muon xoa
                    idCanXoa = selectedWord.getID();
                    System.out.println(idCanXoa);
                    this.webViewNghia.getEngine().loadContent(definition, "text/html");
                }

            }
        });
        textWordTargetAdd.textProperty().addListener((observable, oldValue, newValue) -> {
            String searchContent = textWordTargetAdd.getText().toLowerCase();
            listViewA.getItems().clear();

            for (Word word : dataWordinSql.values()) {
                if (word.getWord_target().toLowerCase().startsWith(searchContent)) {
                    listViewA.getItems().add(word.getWord_target());
                    text = newValue.trim();
                    WordSQL selectedWord = dataWordinSql.get(text);
                    //lay nghia cua tu
                    String definition = selectedWord.getHtml();
                    //lay id cua tu can xoa neu muon xoa
                    idCanXoa = selectedWord.getID();
                    System.out.println(idCanXoa);
                    this.webViewNghia.getEngine().loadContent(definition, "text/html");
                }

            }
        });
        textWordTargetSet.textProperty().addListener((observable, oldValue, newValue) -> {
            String searchContent = textWordTargetSet.getText().toLowerCase();
            listViewA.getItems().clear();

            for (Word word : dataWordinSql.values()) {
                if (word.getWord_target().toLowerCase().startsWith(searchContent)) {
                    listViewA.getItems().add(word.getWord_target());
                    text = newValue.trim();
                    WordSQL selectedWord = dataWordinSql.get(text);
                    //lay nghia cua tu
                    String definition = selectedWord.getHtml();
                    //lay id cua tu can xoa neu muon xoa
                    idCanXoa = selectedWord.getID();
                    System.out.println(idCanXoa);
                    textIdSet.setText(String.valueOf(idCanXoa));
                    this.webViewNghia.getEngine().loadContent(definition, "text/html");
                }

            }
        });
        ////////////////////////////////DELETE WORD///////////////////////////////////////////
        textWordTargetDelete.textProperty().addListener((observable, oldValue, newValue) -> {
            String searchContent = textWordTargetDelete.getText().toLowerCase();
            listViewA.getItems().clear();

            for (Word word : dataWordinSql.values()) {
                if (word.getWord_target().toLowerCase().startsWith(searchContent)) {
                    listViewA.getItems().add(word.getWord_target());
                    text = newValue.trim();
                    WordSQL selectedWord = dataWordinSql.get(text);
                    //lay nghia cua tu
                    String definition = selectedWord.getHtml();
                    //lay id cua tu can xoa neu muon xoa
                    idCanXoa = selectedWord.getID();
                    System.out.println(idCanXoa);
//                    textIdDelete.setText(String.valueOf(idCanXoa));
                    this.webViewNghia.getEngine().loadContent(definition, "text/html");
                }
                textIdDelete.setText(String.valueOf(idCanXoa));
            }
        });
    }

    public void loadWordList() {
        this.listViewA.getItems().addAll(dataWordinSql.keySet());
    }

    @FXML
    protected void addToTap() {
        System.out.println(idTapPane);
        if (idTapPane==0){
            textWordTargetAdd.setText(text);
        }else if (idTapPane==1){
            textWordTargetSet.setText(text);
            textIdSet.setText(String.valueOf(idCanXoa));
        }else if (idTapPane==2){
            textWordTargetDelete.setText(text);
            textIdDelete.setText(String.valueOf(idCanXoa));
        }
    }

    ////////////////////////////////ADD WORD///////////////////////////////////////////
    @FXML
    protected void clickAddAdd() {
        //khonng de trong word_target va word_explain
        if (textWordTargetAdd.getText().length() > 0 && textWordExplainAdd.getText().length() > 0) {
            String word_target = textWordTargetAdd.getText();
            String word_explain = textWordExplainAdd.getText();
            String word_pronounce = textWordPronounceAdd.getText();
            String word_loaiTu = textWordLoaiTu.getText();
            String html = "<h1>"+word_target+"</h1>" +
                    "<h3><i>/"+word_pronounce+"/</i></h3>" +
                    "<h2>"+word_loaiTu+"</h2>" +
                    "<ul><li>"+word_explain+"</li></ul>";

//            "<h1>"+word_target+"</h1>" +
//                    "<h3><i>/"+word_pronounce+"/</i></h3>" +
//                    "<h2>ngoại động từ</h2>" +
//                    "<ul><li>học thuộc lòng, nghiên cứu, nghiền ngẫm</li><li>điều khiển, lái (con tàu) ((cũng) conn)</li></ul>" +
//                    "<h2>danh từ (từ Mỹ,nghĩa Mỹ)</h2>" +
//                    "<ul><li>con game trò chơi bội tín, sự lừa gạt</li></ul>" +
//                    "<h2>ngoại động từ</h2><ul><li>lừa gạt, lừa bịp</li></ul>" +
//                    "<h2>danh từ</h2>" +
//                    "<ul><li>sự chống lại, sự trái ((xem) pro_and_con)</li></ul>"
            labelNoteDelete.setText(DictionaryManagement.addWordInSQLiteDB(word_target, html));
            HomeController.isLoadData=false;
        } else {
            labelNoteDelete.setText("Không được để trống");
        }
    }

//    @FXML
//    protected void clickDeleteAdd() {
//        System.out.println("delete");
//    }
    ////////////////////////////////SET WORD///////////////////////////////////////////

    @FXML
    protected void clickSetSet() {
        System.out.println("set");
    }
//
//    @FXML
//    protected void clickDeleteSet() {
//        System.out.println("delete set");
//    }
    ////////////////////////////////DELETE WORD///////////////////////////////////////////


    @FXML
    protected void clickDeleteDelete() {
        System.out.println("delete delete");
        String word_target = textWordTargetDelete.getText();
        labelNoteDelete.setText( DictionaryManagement.dropWordInSQLiteDB(word_target));
        HomeController.isLoadData=false;
    }

//    @FXML
//    protected void clickCancelDelete() {
//        System.out.println("cancel delete");
//    }


//    public void initialize() {
//        // Đặt nội dung HTML của WebView
//        String htmlContent = "<I><Q>@zootomic /,zouə'tɔmik/<br />*  tính từ<br />- (thuộc) giải phẫu động vật</Q></I>";
//        webView.getEngine().loadContent(htmlContent);
//    }


    @FXML
    protected void clickSpeech() {
        new TextToSpeech(text);
    }


    //ddang loi cho nay
//    @FXML
//    protected void clickDelete() throws IOException, SQLException {
//        System.out.println(idCanXoa);
//        //xoa trong SQL
//        if (DataBase.deleteSQLiteStuding_array(idCanXoa)) {
//            DataBase.loadDataSqlOfSoTuCaNhan();
//            ObservableList<String> emptyList = FXCollections.observableArrayList();
//            listViewA.setItems(emptyList);
//            initComponents();
//            loadWordList();
//            System.out.println("xoa thanh cong");
//        }
//    }


    @Override
    public void start(Stage stage) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("FileSql.fxml"));
        Scene scene = new Scene(fxml);
        stage.setTitle("Home!");

        scene.getStylesheets().add(getClass().getResource("FileSql.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
