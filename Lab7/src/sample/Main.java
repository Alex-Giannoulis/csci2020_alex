package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;



public class Main extends Application {

    private int flashFlood = 0;
    private int severeThunderstorm = 0;
    private int specialMarine = 0;
    private int Tornado = 0;
    private HashMap<String, Integer> map = new HashMap<String, Integer>();
    private GraphicsContext gc;
    private Canvas canvas;


    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        Scene scene = new Scene(root, 1000, 600);
        canvas = new Canvas();
        canvas.widthProperty().bind(primaryStage.widthProperty());
        canvas.heightProperty().bind(primaryStage.heightProperty());
        root.getChildren().add(canvas);
        BufferedReader br = new BufferedReader( new FileReader("./csvfile/weatherwarnings-2015.csv"));
        String holder = null;

        while((holder = br.readLine())!= null) {
            String line[] = holder.split(",");
            counter(line[5]);

        }
        primaryStage.setScene(scene);
        primaryStage.show();
        gc = canvas.getGraphicsContext2D();
        draw();

        }

    private void draw(){
        int total = 0;
        for (int pie: map.values()){
            total+=pie;
        }

        int i = 0;
        double degree = 0.0;
        Color color[] = {Color.YELLOW, Color.CYAN, Color.ORANGE, Color.RED};

        for (int pie: map.values()){
            double curve = (double)pie/(double)total;
            double s = curve *360;
            gc.setFill(color[i]);
            i++;
            gc.fillArc(500,50,350,350,degree,s, ArcType.ROUND);
            degree+=s;
        }
        gc.setFill(Color.RED);
        gc.fillRect(100,100,75,50);
        gc.setFill(Color.CYAN);
        gc.fillRect(100,200,75,50);
        gc.setFill(Color.ORANGE);
        gc.fillRect(100,300,75,50);
        gc.setFill(Color.YELLOW);
        gc.fillRect(100,400,75,50);
        gc.setFill(Color.BLACK);
        gc.fillText("Severe Thunderstorm", 185,125);
        gc.fillText("Tornado", 185,225);
        gc.fillText("Flash Flood", 185,325);
        gc.fillText("Special Marine", 185,425);
    }
    private void counter(String count){
        if(map.containsKey(count)){
            int previous = map.get(count);
            map.put(count, previous+1);
        } else{
            map.put(count, 1);
        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}
