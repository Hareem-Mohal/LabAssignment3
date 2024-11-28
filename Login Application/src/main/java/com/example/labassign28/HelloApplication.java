package com.example.labassign28;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        File file = new File("Users.txt");
        BorderPane mainLayout = new BorderPane();

        Image image = new Image("C:/Users/hamza/OneDrive/Pictures/download.jpeg");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(250);
        imageView.setFitHeight(150);

        VBox topBox = new VBox();
        topBox.getChildren().add(imageView);
        topBox.setAlignment(Pos.CENTER);
        topBox.setPadding(new Insets(10));
        mainLayout.setTop(topBox);

        GridPane grid = new GridPane();

        Label username = new Label("Username");
        TextField userText = new TextField();
        userText.setPromptText("Enter Username");
        userText.setStyle("-fx-background-color: #f9f9f9; -fx-border-color: #8bc34a; " +
                "-fx-border-radius: 8px; -fx-background-radius: 8px; -fx-padding: 8px;");
        grid.add(username, 0, 0);
        grid.add(userText, 1, 0);

        Label password = new Label("Password");
        PasswordField passText = new PasswordField();
        passText.setPromptText("Enter Password");
        passText.setStyle("-fx-background-color: #f9f9f9; -fx-border-color: #8bc34a; " +
                "-fx-border-radius: 8px; -fx-background-radius: 8px; -fx-padding: 8px;");
        grid.add(password, 0, 1);
        grid.add(passText, 1, 1);

        HBox btnBox = new HBox();
        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white; -fx-font-size: " +
                "14px; -fx-font-weight: bold; -fx-border-radius: 8px; -fx-background-radius: 8px;");
        Button exitButton = new Button("Exit");
        exitButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-size: 14px;" +
                " -fx-font-weight: bold; -fx-border-radius: 8px; -fx-background-radius: 8px;");
        btnBox.getChildren().addAll(loginButton, exitButton);
        btnBox.setSpacing(20);
        grid.add(btnBox, 1, 2);

        grid.getChildren().filtered((node) -> node instanceof Label).forEach((node) -> {
            node.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-font-family: 'Arial'; -fx-text-fill: #37474f;");
        });

        loginButton.setOnAction(e -> {
            if (file.exists()) {
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    boolean flag = false;
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] userPass = line.split(" ");
                        if (userPass[0].equals(userText.getText()) &&
                                userPass[1].equals(passText.getText())) {
                            flag = true;
                            Label userFound = new Label("User Name: "+userText.getText());
                            userFound.setStyle("-fx-font-size: 16px; -fx-text-fill: #2e7d32;");
                            Stage newStage = new Stage();
                            GridPane newGrid = new GridPane();
                            Scene scene = new Scene(newGrid, 400, 200);
                            newGrid.add(userFound, 0, 0);
                            newGrid.setAlignment(Pos.CENTER);
                            userText.clear();
                            passText.clear();

                            newStage.setScene(scene);
                            newStage.show();
                        }
                    }

                    if (!flag) {
                        Label userNotFound = new Label("User not found");
                        userNotFound.setStyle("-fx-text-fill: #d32f2f; -fx-font-size: 14px;");
                        grid.add(userNotFound, 1, 4);
                    }

                } catch (IOException ev) {
                    ev.printStackTrace();
                }
            } else {
                System.out.println("File not found");
            }
        });

        exitButton.setOnAction(event -> {
            System.exit(0);
        });

        grid.setHgap(10);
        grid.setVgap(15);
        grid.setAlignment(Pos.CENTER);
        mainLayout.setCenter(grid);

        Scene scene = new Scene(mainLayout, 600, 400);
        stage.setTitle("Login Application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
