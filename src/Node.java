
import java.io.Serializable;

public class Node implements Serializable
{


    private Profile currentProfile;
    private Node next;
    private Node previous;
    private boolean isHead;
    private boolean isTail;

    public Node (Profile s, Node n, Node p)
    {
        currentProfile = s;
        next = n;
        previous = p;
        isHead = false;
        isTail = false;
    }


    public Profile getProfile ()
    {
        return currentProfile;
    }


    public void setTail (boolean b)
    {
        isTail = b;
    }


    public void setHead (boolean b)
    {
        isHead = b;
    }


    public Node getNext ()
    {
        return next;

    }


    public Node getPrevious ()
    {
        return previous;
    }


    public boolean isHead ()
    {
        return isHead;
    }


    public boolean isTail ()
    {
        return isTail;
    }


    public void setNext (Node n)
    {
        next = n;
    }


    public void setPrevious (Node n)
    {
        previous = n;
    }
}

