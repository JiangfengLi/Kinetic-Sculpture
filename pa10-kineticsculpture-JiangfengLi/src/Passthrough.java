/**
 * JiangfengLi Passthrough.java, PA10-KineticSculpture assignment This class
 * initialize Passthrough class which is the subclass of Node class. It sets the
 * type of class with "pass".
 */
public class Passthrough extends Node {

    /*
     * function: Source(int X, int Y, String id) parameter X, Y, id: X and Y is
     * the coordinates of node, id is the ID of node. This method initialize a
     * Node object and a list of Circle objects for input marbles and another
     * list of Circle object for outputs.
     * returns: None.
     */
    public Passthrough(int X, int Y, String id) {
        super(X, Y, id);
        super.setType("pass");
    }

}
