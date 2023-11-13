package com.example.baitaplon;

import java.io.*;
import java.sql.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class DictionaryManagement {
    Scanner scan = new Scanner(System.in);

    /**
     * Nhập vào bàn phím số lượng từ vựng (Word).
     * Định dạng nhập dữ liệu từ điển Anh – Việt:
     * +Dòng 1: Nhập từ tiếng Anh.
     * +Dòng 2: Nhập giải thích bằng tiếng Việt.
     */
    public void insertFromCommandline() {


    }

    /**
     * nhập dữ liệu từ điển từ tệp dictionaries.txt.
     * Quy định tệp dictionaries.txt: Mỗi dòng chứa từ tiếng anh và giải thích tiếng việt
     * (ngăn cách nhau bằng dấu tab).
     */
    public void insertFromFile() {

    }


    /**
     * chức năng tra cứu từ điển.
     */
    public void dictionaryLookup(String word) {

    }

    /**
     * chuc nang them tu.
     *
     * @param word_target  _ tu tieng anh
     * @param word_explain _ nghia  tieng viet
     * @return _ true khi them thanh cong nguoc lai
     */
    public boolean dictionaryAdd(String word_target, String word_explain) {

        return true;
    }

    /**
     * chuc nang sua tu.
     */
//    public void dictionaryEdit(String word_target, String word_explain) {
//        Dictionary.set(word_target, word_explain);
//    }

    /**
     * chuc nang xoa tu.
     *
     * @return true khi xoa thanh cong
     */
//    public boolean dictionaryErase(String word) {
//        if (Dictionary.delete(word) == true) {
//            System.out.println("Done");
//            return true;
//        }
//        System.out.println("Error");
//        return false;
//    }

    /**
     * Search Word in database.
     */
    public void dictionarySearcherinDB() {
        System.out.print("Tim tu: ");
        String input = scan.nextLine();
        String searchWord = input.toLowerCase();
        MySQLConnector mySQLConnection = new MySQLConnector();
        Connection connection = mySQLConnection.getConnection();

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT definition FROM dictionary_db.dictionary where target like '" + searchWord + "'";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String detail = resultSet.getString("definition");

                System.out.println("detail: " + detail);
                System.out.println("-------------------------");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        mySQLConnection.closeConnection();
    }

    /**
     * Add Word.
     * Search text in database + add to Add_Word_Dictionary.txt if word not in data
     */
    public void addWordInDB() {
        //Input
        System.out.print("Add word: ");
        String input = scan.nextLine();
        String addWord = input.toLowerCase();
        //Check whether it was in DB or not
        MySQLConnector mySQLConnection = new MySQLConnector();
        Connection connection = mySQLConnection.getConnection();
        boolean ifNot = false;
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT definition FROM dictionary_db.dictionary where target like '" + addWord + "'";
            ResultSet resultSet = statement.executeQuery(query);
            String detail = "";

            while (resultSet.next()) {
                detail = resultSet.getString("definition");
            }
            //Check
            if (detail == "") {
                ifNot = true;
            } else {
                ifNot = false;
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mySQLConnection.closeConnection();
        //If not then add
        if (ifNot) {
            //Add to txt file
            try {
                String filePath = "data/Add_Word_Dictionary.txt";
                FileWriter filePen = new FileWriter(filePath);
                BufferedWriter bufferedWriter = new BufferedWriter(filePen);
                //write
                System.out.println("Nhap nghia cua tu " + addWord);
                String mean = scan.nextLine();
                String newWord = addWord + "\t\t" + mean;
                bufferedWriter.write(newWord);
                //close
                bufferedWriter.flush();
                filePen.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //If it was the same -> anouncement
        else {
            //Note
            System.out.println("Tu da ton tai");
        }
    }

    public void dropWord() {
        //Input
        System.out.print("Xoa tu: ");
        String input = scan.nextLine();
        String wordDrop = input.toLowerCase();
        HashMap<String, String> mapofWord = new HashMap<>();

        //Slove
        //Check tu trong text
        //Doc file tuan tu luu vao mot mang / map
        try {
            String filePath = "data/Add_Word_Dictionary.txt";
            FileInputStream fileInputStream = new FileInputStream(filePath);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }
                String[] arrayofWord = line.split("   ", 2);
                if (arrayofWord.length >= 2) {
                    String word = arrayofWord[0].trim().toLowerCase();
                    String definition = arrayofWord[1].trim();

                    System.out.printf("%s,%s\n", word, definition);

                    mapofWord.put(word, definition);
                }
                else {
                    System.out.println("Lỗi out of bound exception");
                }
            }
            System.out.println("Lưu file txt vào bộ nhớ đệm Map");

            //Close buff, file,...
            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();
        } catch (IOException e) {
            System.out.print("Lỗi đọc hoặc đóng mở file: ");
            e.printStackTrace();
        }
        //Duyet mang va check
        if (mapofWord.containsKey(wordDrop)) {
            //Neu co thi xoa trong text
            //Xoa tu trong map
            System.out.println("Đã xóa từ khỏi Map: " + wordDrop);
            mapofWord.remove(wordDrop);
            //Ghi lai len file
            try {
                String filePath = "data/Add_Word_Dictionary.txt";
                FileWriter fileWriter = new FileWriter(filePath, false);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                mapofWord.forEach(
                        (word, definition)
                                -> {
                            String temp = word + "\t\t" + definition + "\n";
                            try {
                                bufferedWriter.write(temp);
                            } catch (IOException e) {
                                System.out.print("Lỗi ghi đè lại file: ");
                                e.printStackTrace();
                            }
                        }
                );
                System.out.println("Đã ghi đè lại file");

                //close
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                System.out.print("Lỗi ghi file hoặc đóng file: ");
                e.printStackTrace();
            }
        } else {
            //Khong co check trong DB
            System.out.println("Không thấy từ " + wordDrop);
        }

        //Co thi xoa trong DB
        //Khong co nua thi bao loi
        //Output
    }
}
