
/**
 * JiangfengLi Sculpture.java, PA10-KineticSculpture assignment This class reads lines of 
 * input file, creates list of Edges objects and Id-Nodes pair HashMap and add Nodes and Edges into list and Map. 
 * Then it displays Sculpture or transfer the marbles. 
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * This is the Main class of Sculpture, it initialize a list of Edges objects
 * and an Id-Nodes pair HashMap. Then it displays Sculpture or transfer the
 * marbles according to the commands from PA10Main class.
 */
public class Sculpture {
    private static List<Edges> edges;
    private static Map<String, Node> Id_Node;

    /*
     * function: Sculpture() parameter: None. This method initialize a Sculpture
     * object and initializes a list of Edges objects and an Id-Nodes pair
     * HashMap.
     * returns: None.
     */
    public Sculpture() {
        edges = new ArrayList<>(); // To store edges.
        Id_Node = new HashMap<>();
    }

    /*
     * function: initiaNode(JavaFXView view, String[] lines) parameter view,
     * lines: view is JavaFXView object, lines is the current line that scanner
     * is reading in the file. This method initialize Nodes of Sculpture and
     * input them into Id-Nodes pair HashMap.
     * returns: None.
     */
    public void initiaNode(JavaFXView view, String[] lines) {
        String id = lines[0].substring(0, lines[0].length() - 1);
        int X = 0, Y = 0; // to track the coordinates of nodes.
        if (lines.length == 3) { // Case for when there isn't any space in (,)
            String[] loc = lines[2].split(",");
            X = Integer.valueOf(loc[0].substring(1)) + 20;
            Y = Integer.valueOf(loc[1].substring(0, loc[1].length() - 1)) + 30;
        } else if (lines.length == 4) { // Case for when there is space in (,)
            X = Integer.valueOf(lines[2].substring(1, lines[2].length() - 1))
                    + 20;
            Y = Integer.valueOf(lines[3].substring(0, lines[3].length() - 1))
                    + 30;
        }
        if (lines[1].equals("input,")) { // Initialize input node.
            Id_Node.put("input", new Source(X, Y, id));
            view.initNode(Integer.valueOf(id), X, Y, 30, "red", "white");
        } else if (lines[1].equals("passthrough,")) { // passthrough node.
            Id_Node.put(id, new Passthrough(X, Y, id));
            view.initNode(Integer.valueOf(id), X, Y, 30, "aqua", "white");
        } else if (lines[1].equals("sink,")) {// Sink node.
            Id_Node.put("sink", new Sink(X, Y, id));
            view.initNode(Integer.valueOf(id), X, Y, 30, "blue", "white");
        }
    }

    /*
     * function: initiaEdge(JavaFXView view, String[] lines) parameter view,
     * lines: view is JavaFXView object, lines is the current line that scanner
     * is reading in the file. This method initialize Nodes of Sculpture and
     * input them into list of Edges.
     * returns: None.
     */
    public void initiaEdge(JavaFXView view, String[] lines) {
        Node start = null, end = null; // Track the beginning and ending Node.
        edges.add(new Edges(lines[0], lines[2]));
        for (String name : Id_Node.keySet()) {// Check out Start and end Nodes.
            if (Id_Node.get(name).getID().equals(lines[0]))
                start = Id_Node.get(name);
            else if (Id_Node.get(name).getID().equals(lines[2]))
                end = Id_Node.get(name);
        } // Initiate Edges inside JavaFXView
        view.initEdge(Integer.valueOf(lines[0]), Integer.valueOf(lines[2]),
                start.getX() + 30, start.getY() + 15, end.getX(),
                end.getY() + 15);
    }

    /*
     * function: play(double delay, String[] inputs, JavaFXView view) parameter
     * view, inputs and delay: view is JavaFXView object, inputs is an array of
     * Color and view is JavaFXView object. This method creates circles in
     * different colors and assigns them into input node.
     * returns: None.
     */
    public static void play(double delay, String[] inputs, JavaFXView view) {
        for (String col : inputs) { // Create new circles in different colors
            Circle marble = new Circle();
            marble.setFill(Color.web(col.toLowerCase()));
            marble.setRadius(10);
            Id_Node.get("input").setInput(marble);
        } // Set the context colorof input node to be the color of first marble.
        view.changeFill(Integer.valueOf(Id_Node.get("input").getID()),
                inputs[0]);
        PauseTransition wait = new PauseTransition(Duration.seconds(delay));
        wait.setOnFinished((ActionEvent e) -> {// Display animation.
            if (!allEmpty()) {// Check whether all the nodes become empty.
                process(view);
                wait.playFromStart();
                edgeTransition(delay, view);
                wait.playFromStart();
            } else
            wait.stop();
        });
        // Now that the PauseTransition thread is setup, get it going.
        wait.play();
    }

    /*
     * function: process(JavaFXView view) parameter view: view is JavaFXView
     * object. This method move circles from the input of node to output of
     * node.
     * returns: None.
     */
    private static void process(JavaFXView view) {
        for (String name : Id_Node.keySet()) {
            if (Id_Node.get(name).getInput().size() != 0)
                Id_Node.get(name).moveToNext(view);
        }
    }

    /*
     * function: edgeTransition(double delay, JavaFXView view) parameter delay,
     * view: view is JavaFXView object. This method move circles from the source
     * node other nodes that have connection with source node.
     * returns: None.
     */
    private static void edgeTransition(double delay, JavaFXView view) {
        for (String name : Id_Node.keySet()) {
            // Find out all the nodes that has marbles in output
            if (Id_Node.get(name).getOutput().size() != 0 && 
                    !name.equals("sink")) {
                for (int i = 0; i < edges.size(); i++) {
                    if (edges.get(i).n1.equals(Id_Node.get(name).getID()))
                        view.edgeTransition(name, edges.get(i).n2, Id_Node,
                                delay);
                } // remove the marbles transferred from output
                Id_Node.get(name).getOutput().remove(0);
            }
        }
    }

    /*
     * function: allEmpty() parameter: None. This method whether marbles have
     * been removed from all the nodes.
     * returns: True if there isn't any node that has marble, false otherwise.
     */
    private static boolean allEmpty() {
        for(String name: Id_Node.keySet()) {
            if (Id_Node.get(name).getInput().size() != 0
                    || Id_Node.get(name).getOutput().size() != 0)
                return false; // When there is a node that has marble
        }
        return true;
    }

    /*
     * function: clear(JavaFXView view) parameter view: view is JavaFXView
     * object. This method clear all data structures and canvas before users
     * input another file.
     * returns: None.
     */
    public static void clear(JavaFXView view) {
        edges.clear();
        Id_Node.clear(); // clear HashMap of Nodes
        view.clearBackground();// Clear background.
    }

}
