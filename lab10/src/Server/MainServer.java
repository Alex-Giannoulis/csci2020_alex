package Server;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainServer<MainServer> extends Application {
    public static TextArea board = new TextArea();
    @Override
    public void start(Stage primaryStage) throws Exception{
        Server app = new Server();
        primaryStage.setTitle("Server");
        GridPane pane = new GridPane();

        Button exit = new Button("Exit");

        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setVgap(10);

        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                app.close();
            }
        });

        board.setEditable(false);
        pane.add(board,0,0);
        pane.add(exit,0,1);

        primaryStage.setScene(new Scene(pane, 550, 225));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
