/**
 * JiangfengLi PA10Main.java, PA10-KineticSculpture assignment This program reads an .in file from textfield and 
 * reads the delay, input colors, nodes and edges of a sculpture from file. Then it will display this sculpture 
 * and simulates marbles moving. 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * This is the Main class of PA10Main, it launches javaFX. The program draws the
 * nodes and lines of a sculpture in specific locations according to the file
 * from textfield, then presents the animation of moving marbles.
 */
public class PA10Main extends Application {
    private static Scanner in;

    /*
     * function: main(String[] args) parameter args: args is command line
     * arguments. This function launches JavaFX.
     * users returns: None.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /*
     * function: start(Stage primaryStage) parameter primaryStage: primaryStage
     * is Stage for presenting the Graphs and panel. This function creates
     * prompt, textfield and button for Group, sets eventhandler to read files
     * and simulate sculpture.
     * users returns: None.
     */
    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Sculpture filename:");
        Button button = new Button("Run Sculpture");
        TextField filename_field = new TextField();
        filename_field.setPrefWidth(200); // set the width of TextField
        JavaFXView View = new JavaFXView(800, 600);
        HBox input_box = new HBox(3); // create a horizontal box
        Sculpture scul = new Sculpture();
        input_box.getChildren().addAll(label, filename_field, button);
        View.root.getChildren().add(input_box);
        primaryStage.setTitle("KineticSculpture");
        primaryStage.setScene(new Scene(View.root));
        primaryStage.show();// Show the graph
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try { // To test whether the file is empty.
                    in = new Scanner(new File(filename_field.getText()));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } // take first line of file as delay, second as input colors.
                double delay = Double.valueOf(in.nextLine().split(": ")[1]);
                String[] inputs = in.nextLine().split(": ")[1].split(", ");
                setupStage(primaryStage, 700, 700, View, in, scul);
                Sculpture.play(delay, inputs, View);
            }
        });
    }

    /*
     * function: setupStage(Stage primaryStage, int canvas_width, int
     * canvas_height, JavaFXView view, Scanner in, Sculpture scul) parameter
     * primaryStage, canvas_width, canvas_height, view, in, scul: primaryStage
     * is Stage for presenting the Graphs and panel, canvas_width and
     * canvas_height are the width and height of canvas, view is JavaFXView
     * object, in is Scanner object and scul is Sculpture object. This function
     * reads the lines of file and initiates corresponding nodes and edges.
     * users returns: None.
     */
    protected void setupStage(Stage primaryStage, int canvas_width,
            int canvas_height, JavaFXView view, Scanner in, Sculpture scul) {
        Sculpture.clear(view);
        while (in.hasNext()) { // set up the scene with a hardcoded sculpture
            String[] lines = in.nextLine().split("\\s+");
            if (lines[0].endsWith(":")) {
                scul.initiaNode(view, lines);
            } else {
                scul.initiaEdge(view, lines);
            }
        }
    }

}

