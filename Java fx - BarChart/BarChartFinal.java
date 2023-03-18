


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Scanner;

public class BarChartFinal extends Application
{
    static double project;
    static double quiz;
    static double midterm;
    static double finals;
    static double projectHeight;
    static double quizHeight;
    static double midtermHeight;
    static double finalsHeight;

    public void start(Stage primaryStage)
    {
        HBox hB = new HBox(30);
        hB.setAlignment(Pos.TOP_CENTER);
        hB.setPadding(new Insets(30,30,30,30));
        Text pro = new Text("Project = "+project+"%");
        pro.setStroke(Color.BLACK);
        pro.setFill(Color.RED);
        Text qui = new Text("Quiz = "+quiz+"%");
        qui.setStroke(Color.BLACK);
        qui.setFill(Color.BLUE);
        Text mid = new Text("midTerm = "+midterm+"%");
        mid.setStroke(Color.BLACK);
        qui.setFill(Color.GREEN);
        Text fin = new Text("Finals = "+finals+"%");
        fin.setStroke(Color.BLACK);
        fin.setFill(Color.YELLOW);

        hB.getChildren().addAll(pro,qui,mid,fin);


        HBox hbox = new HBox(30);
        hbox.setAlignment(Pos.BOTTOM_CENTER);
        hbox.setPadding(new Insets(30,30,30,30));
        Rectangle rec1 = new Rectangle(80,projectHeight,Color.RED);
        Rectangle rec2 = new Rectangle(80,quizHeight,Color.BLUE);
        Rectangle rec3 = new Rectangle(80,midtermHeight,Color.GREEN);
        Rectangle rec4 = new Rectangle(80,finalsHeight,Color.YELLOW);
        hbox.setAlignment(Pos.BOTTOM_CENTER);
        hbox.getChildren().addAll(rec1,rec2,rec3,rec4);

        StackPane sPane = new StackPane();
        sPane.setPrefSize(300, 180);
        sPane.getChildren().addAll(hB,hbox);

        Scene scene =new Scene(sPane,500,500);
        primaryStage.setTitle("BARCHART - SriVishnuram");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public static void main(String[] args)
    {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Project Percentage =");
        project = userInput.nextDouble();
        System.out.println("Quiz Percentage =");
        quiz = userInput.nextDouble();
        System.out.println("MidTerm Percentage =");
        midterm = userInput.nextDouble();
        System.out.println("Finals Percentage =");
        finals = userInput.nextDouble();

        double[] findMax= {project,quiz,midterm,finals};
        double maxValue = Double.MIN_VALUE;
        for(double a : findMax)
        {
            if(maxValue<a)
            {
                maxValue = a;
            }
        }
        if(project+quiz+midterm+finals != 100)
        {
            System.out.println("CHECK : Total Percentage not equal to 100");
        }
        else
        {
            projectHeight= (int) (400*project/maxValue);
            quizHeight = (int)(400*quiz/maxValue);
            midtermHeight = (int) (400*midterm/maxValue);
            finalsHeight = (int) (400*finals/maxValue);
        }
        launch(args);
    }
}
