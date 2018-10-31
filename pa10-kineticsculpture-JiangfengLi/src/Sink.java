
/**
 * JiangfengLi Sink.java, PA10-KineticSculpture assignment This class
 * initialize Sink class which is the subclass of Node class. It sets the type
 * of class with "sink" and modify moveToNext method.
 */
import javafx.scene.shape.Circle;

/**
 * This is the Main class of Sink, it initialize Sink class and set type with
 * "sink".
 */
public class Sink extends Node {

    /*
     * function: Sink(int X, int Y, String id) parameter X, Y, id: X and Y is
     * the coordinates of node, id is the ID of node. This method initialize a
     * Node object and a list of Circle objects for input marbles and another
     * list of Circle object for outputs.
     * returns: None.
     */
    public Sink(int X, int Y, String id) {
        super(X, Y, id);
        super.setType("sink");
    }

    /*
     * function: moveToNext(JavaFXView view) parameter view: view is JavaFXView
     * object.
     * This method prints out all the marbles in inputs list and then remove all
     * the marbles.
     * returns: None.
     */
    public void moveToNext(JavaFXView view) {
        for (Circle sink_marble : super.getInput()) {
            System.out.println("sink output = " + sink_marble);
        } // remove all the marbles
        view.root.getChildren().removeAll(super.getInput());
        super.getInput().clear();
        if (super.getInput().size() == 0) // set background to white
            view.changeFill(Integer.valueOf(super.getID()), "white");
    }

}
