/**
 * JiangfengLi Edges.java, PA10-KineticSculpture assignment This class
 * initialize Edges class, stores a string of source Id as n1 and target Id as
 * n2.
 */
public class Edges {
    public String n1, n2;

    /*
     * function: Edges(String N1, String N2) parameter N1, N2: N1 is
     * a string of source Id N2 is a string of target Id. This method take N1,
     * N2 as parameter and store N1 to n1 and N2 to n2.
     * returns: None.
     */
    public Edges(String N1, String N2) {
        this.n1 = N1;
        this.n2 = N2;
    }

}
