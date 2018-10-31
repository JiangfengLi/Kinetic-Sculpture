/**
 * JiangfengLi Source.java, PA10-KineticSculpture assignment This class
 * initialize Source class which is the subclass of Node class. It sets the type
 * of class with "input".
 */
public class Source extends Node {

    /*
     * function: Source(int X, int Y, String id) parameter X, Y, id: X and Y is
     * the coordinates of node, id is the ID of node. This method initialize a
     * Node object and a list of Circle objects for input marbles and another
     * list of Circle object for outputs.
     * returns: None.
     */
    public Source(int X, int Y, String id) {
        super(X, Y, id);
        super.setType("input");
    }

    /*
     * function: moveToNext(JavaFXView view) parameter view: view is JavaFXView
     * object.
     * This method move marble from inputs list to the outputs list and fill the
     * context of rectangle node with the color of next marble in inputs list.
     * returns: None.
     */
    public void moveToNext(JavaFXView view) {
        super.moveToNext(view);
        if (!super.getInput().isEmpty()) // Check whether the inputs is empty
        view.changeFill(Integer.valueOf(super.getID()),
                super.getInput().get(0).getFill().toString());
    }

}
