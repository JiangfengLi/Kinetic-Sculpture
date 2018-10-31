
/**
 * JiangfengLi JavaFXView.java, PA10-KineticSculpture assignment This class store 
 * the width and height of the canvas, Lines and nodes of Sculpture and initiate a background with white color.
 * Draw the Sculpture and show animation of marbles moving on the line. 
 */
import java.util.HashMap;
import java.util.Map;

import javafx.animation.PathTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * This is the Main class of JavaFXView, it initialize Group object,
 * edgeToLineMap and nodeTorectangle HashMap. Then it displays
 * Sculpture or transfer the marbles according to the commands from Sculpture
 * class.
 */
public class JavaFXView {
    private int width, height;
    public Group root = new Group();
    private HashMap<Integer, Map<Integer, Line>> edgeToLineMap = new HashMap<>();
    private HashMap<Integer, Rectangle> nodeTorectangle = new HashMap<>();

    /*
     * function:JavaFXView(int window_width, int window_height) parameter
     * window_width, window_height: window_width is the width of background
     * while window_height is the height of background.
     * This method initialize a JavaFXView object and add background to root and
     * store width and height of beckground.
     * returns: None.
     */
    public JavaFXView(int window_width, int window_height) {
        width = window_width;
        height = window_height; // Set the background of canvas.
        Rectangle background = new Rectangle(0, 0, window_width, window_height);
        background.setFill(Color.WHITE);
        root.getChildren().add(background);
    }

    /*
     * function:initNode(int node_id, int startX, int startY, int size,
     * String Stroke, String Fill) parameter node_id, startX, startY, size,
     * Stroke, Fill: node_id is the id of node, startX is the X coordinate of
     * node, startY is the Y coordinate of node, size is the size of rectangle
     * for showing node, Stroke is the color of outline and Fill is the color
     * to fill in background of node rectangle.
     * This method initiates nodes and draws it to the background.
     * returns: None.
     */
    public void initNode(int node_id, int startX, int startY, int size,
            String Stroke, String Fill) {
        Rectangle node = new Rectangle(startX, startY, size, size);
        node.setStroke(Color.web(Stroke.toLowerCase()));
        node.setStrokeWidth(2);// set the width of outline.
        node.setFill(Color.web(Fill.toLowerCase()));
        // store the node for changing the color of context.
        nodeTorectangle.put(node_id, node);
        root.getChildren().add(node);
        // Assume this node may have edges coming out of it
        edgeToLineMap.put(node_id, new HashMap<Integer, Line>());
    }

    /*
     * function:changeFill(int node_id, String Fill) parameter node_id, Fill:
     * node_id is the id of node, Fill is the color to fill in background of
     * node rectangle.
     * This method change the color of context inside rectangle nodes.
     * returns: None.
     */
    public void changeFill(int node_id, String Fill) {
        root.getChildren().remove(nodeTorectangle.get(node_id));
        Rectangle node = nodeTorectangle.get(node_id);
        node.setFill(Color.web(Fill.toLowerCase()));
        root.getChildren().add(node); // add node to root.
    }
    
    /*
     * function: initEdge(int source_id, int target_id, int startX, int startY,
     * int endX, int endY) parameter source_id, target_id, startX, startY, endX,
     * endY: source_id is the id of source node, target_id is the Id of target
     * node, startX and startY are the x y coordinates of source node, endX and
     * endY is the x y coordinates of target node.
     * This method initiates the Edges and creates and stores line.
     * returns: None.
     */
    public void initEdge(int source_id, int target_id, int startX, int startY,
            int endX, int endY) {
        Line edge = new Line(startX, startY, endX, endY);
        root.getChildren().add(edge); // Store the line into HashMap
        edgeToLineMap.get(source_id).put(target_id, edge);
    }

    /*
     * function: edgeTransition(String SID, String TID, Map<String, Node>
     * Id_Node, double delay) parameter SID, TID, Id_Node, delay: SID is
     * id of source node, TID is the id of target node, Id_Node is a HashMap,
     * delay is the pause time of animation. This method move circles
     * from the source node other nodes that have connection with source node
     * and simulation the transition on the line between source node and target
     * nodes.
     * returns: None.
     */
    public void edgeTransition(String SID, String TID,
            Map<String, Node> Id_Node, double delay) {
        // create a new marble, associate it with edge, and do transition
        for (String name : Id_Node.keySet()) {
            if (Id_Node.get(name).getID().equals(TID)) {
                Circle marble = circleClone(// Create new marbles
                        Id_Node.get(SID).getOutput().get(0));
                Id_Node.get(name).setInput(marble);
                root.getChildren().add(marble); // add new marble to root.
                int source_id = Integer.valueOf(Id_Node.get(SID).getID());
                PathTransition trans = new PathTransition(
                        Duration.seconds(delay),
                        edgeToLineMap.get(source_id).get(Integer.valueOf(TID)),
                        marble);
                trans.play(); // play the process of transition.
                changeFill(Integer.valueOf(TID), marble.getFill().toString());
            }
        }
    }

    /*
     * function: circleClone(Circle toClone) parameter toClone: toClone is the
     * marble will be copied. This method clone the marble and return it.
     * returns: A circle of marble.
     */
    private Circle circleClone(Circle toClone) {
        Circle clone = new Circle();
        clone.setFill(toClone.getFill());
        clone.setRadius(toClone.getRadius());
        return clone;
    }

    /*
     * function: clearBackground() parameter : None. This method clear all the
     * data structure and background.
     * returns: None.
     */
    public void clearBackground() {
        Rectangle newbackground = new Rectangle(0, 35, width, height);
        newbackground.setFill(Color.WHITE);// Clear background.
        root.getChildren().add(newbackground);
        edgeToLineMap.clear();
    }

}
