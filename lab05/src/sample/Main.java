package sample;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    private TableView<StudentRecord> table = new TableView<StudentRecord>();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Student Information");
        stage.setWidth(525);
        stage.setHeight(500);

        final Label label = new Label("Student Records");
        label.setFont(new Font("Arial", 20));

        TableColumn SIDCol = new TableColumn("SID");
        SIDCol.setCellValueFactory(new PropertyValueFactory<>("StudId"));
        TableColumn AssignCol = new TableColumn("Assignments");
        AssignCol.setCellValueFactory(new PropertyValueFactory<>("assignment"));
        TableColumn MidtermCol = new TableColumn("Midterm");
        MidtermCol.setCellValueFactory(new PropertyValueFactory<>("midterm"));
        TableColumn FExamCol = new TableColumn("Final Exam");
        FExamCol.setCellValueFactory(new PropertyValueFactory<>("finalExam"));
        TableColumn FMarkCol = new TableColumn("Final Mark");
        FMarkCol.setCellValueFactory(new PropertyValueFactory<>("finalMark"));
        TableColumn LetterCol = new TableColumn("Letter Grade");
        LetterCol.setCellValueFactory(new PropertyValueFactory<>("letterGrade"));

        ObservableList<StudentRecord> marks = DataSource.getAllMarks();
        table.setItems(marks);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        table.getColumns().addAll(SIDCol, AssignCol, MidtermCol, FExamCol, FMarkCol, LetterCol);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }
};