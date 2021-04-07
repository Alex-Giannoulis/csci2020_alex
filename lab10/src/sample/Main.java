package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static Text connection = Text.connect("localhost", 16789);
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Client");
        GridPane pane = new GridPane();

        Label lblUser = new Label("Username: ");
        TextField txtUser = new TextField("Enter Username");

        Label lblTextMessage = new Label("Message");
        TextField txtTextMessage = new TextField("Enter Message");

        Button SendButton = new Button("Send");

        Button ExitButton = new Button("Exit");

        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setVgap(10);

        pane.add(lblUser,0,0);
        pane.add(txtUser,1,0);
        pane.add(lblTextMessage,0,1);
        pane.add(txtTextMessage,1,1);
        pane.add(SendButton,0,2);
        pane.add(ExitButton,0,3);


        ExitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                connection.exit();
            }
        });
        SendButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                connection.sendTextMessage(txtUser.getText(), txtTextMessage.getText());
            }
        });



        primaryStage.setScene(new Scene(pane, 500, 250));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
