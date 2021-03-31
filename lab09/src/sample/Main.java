package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Main extends Application {
    private GraphicsContext gc;
    private Canvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        Scene scene = new Scene(root, 700, 800);
        canvas = new Canvas();
        canvas.widthProperty().bind(primaryStage.widthProperty());
        canvas.heightProperty().bind(primaryStage.heightProperty());
        root.getChildren().add(canvas);
        primaryStage.setTitle("Lab 09");
        primaryStage.setScene(scene);
        primaryStage.show();

        gc = canvas.getGraphicsContext2D();
        ArrayList<Float> S1 = downloadStockPrices("GOOG");
        ArrayList<Float> S2 = downloadStockPrices("AAPL");
        drawLinePlot(S1,S2);

    }


    public ArrayList<Float> downloadStockPrices(String stockTicks){
        ArrayList<Float> val = new ArrayList<Float>();
        try{
            URL url = new URL ("https://query1.finance.yahoo.com/v7/finance/download/" + stockTicks + "?period1=1262322000&period2=1451538000&interval=1mo&events=history&includeAdjustedClose=true" );
            URLConnection connect = url.openConnection();
            connect.setDoOutput(false);
            connect.setDoInput(true);
            InputStream inputStream = connect.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while((line = br.readLine()) != null){
                String string[] = line.split(",");
                if(string[4].equals("Close")){
                    continue;

                }
                else{
                    val.add(Float.valueOf(string[4]));
                }
            }
        }
        catch (Exception p){
            p.printStackTrace();
        }
        return val;

    }

    public void drawLinePlot(ArrayList<Float> float1, ArrayList<Float> float2){
        gc.setStroke(Color.BLACK);
        gc.strokeLine(50,50,50, 750);
        gc.strokeLine(50,750,750, 750);

        plotline(Color.RED, float1);
        plotline(Color.BLUE, float2);


    }
    private void plotline(Color col, ArrayList<Float> var){

        int posX = 50;
        float posY = 700 - var.get(0);
        float posY2;

        for(float info: var){
            posY2 = 700 - info;
            gc.setStroke(col);
            gc.strokeLine(posX, posY, posX+10, posY2);
            posX += 10;
            posY = 700 - info;
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
