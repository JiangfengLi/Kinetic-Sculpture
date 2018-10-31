
/**
 * JiangfengLi Node.java, PA10-KineticSculpture assignment This class initialize Node class, 
 * stores Circle transmitted from other nodes into inputs List and move the marbles that will 
 * be transmitted to other nodes into outputs list. 
 */
import java.util.ArrayList;
import java.util.List;

import javafx.scene.shape.Circle;

/**
 * This is the Main class of Node, it initialize Node class, stores x and y
 * coordinates of node, Circle transmitted from other nodes into inputs List and
 * move the marbles that will be transmitted to other nodes into outputs list.
 */
public abstract class Node {
    private int x, y;
    private List<Circle> inputs;
    private List<Circle> outputs;
    private String Type, ID;

    /*
     * function: Node(int X, int Y, String id) parameter X, Y, id: X and Y is
     * the coordinates of node, id is the ID of node. This method initialize a
     * Node object and a list of Circle objects for input marbles and another
     * list of Circle object for outputs.
     * returns: None.
     */
    public Node(int X, int Y, String id) {
        this.x = X;
        this.y = Y;
        this.ID = id;
        inputs = new ArrayList<>();
        outputs = new ArrayList<>();
    }

    /*
     * function: getX() parameter: None.
     * This method returns the x coordinate of Node.
     * returns: an integer of x coordinate.
     */
    public int getX() {
        return x;
    }

    /*
     * function: getY() parameter: None.
     * This method returns the y coordinate of Node.
     * returns: an integer of y coordinate.
     */
    public int getY() {
        return y;
    }

    /*
     * function: getID() parameter: None.
     * This method returns the ID of Node.
     * returns: a string of ID.
     */
    public String getID() {
        return ID;
    }

    /*
     * function: setType(String T) parameter T: T is the input type.
     * This method set the type of Node.
     * returns: None.
     */
    public void setType(String T) {
        Type = T;
    }

    /*
     * function: setInput(Circle marble) parameter marble: marble is the circle
     * to input.
     * This method add marble to the inputs list.
     * returns: None.
     */
    public void setInput(Circle marble) {
        inputs.add(marble);
    }

    /*
     * function: setOutput(Circle marble) parameter marble: marble is the circle
     * to output list.
     * This method add marble to the outputs list.
     * returns: None.
     */
    public void setOutput(Circle marble) {
        outputs.add(marble);
    }

    /*
     * function: getInput() parameter: None.
     * This method return the inputs list.
     * returns: A list of circles.
     */
    public List<Circle> getInput() {
        return inputs;
    }

    /*
     * function: getOutput() parameter: None.
     * This method return the outputs list.
     * returns: A list of circles.
     */
    public List<Circle> getOutput() {
        return outputs;
    }

    /*
     * function: moveToNext(JavaFXView view) parameter view: view is JavaFXView
     * object.
     * This method move marble from inputs list to the outputs list.
     * returns: None.
     */
    public void moveToNext(JavaFXView view) {
        outputs.add(inputs.get(0));// Clear the marbles on background
        view.root.getChildren().removeAll(inputs);
        inputs.remove(0);
        if (inputs.size() == 0) // Check whether the inputs is empty
            view.changeFill(Integer.valueOf(ID), "white");
    }

}
