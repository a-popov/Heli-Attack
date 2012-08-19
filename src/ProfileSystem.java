import java.io.File;
import java.io.Serializable;


public class ProfileSystem implements Serializable{

        private Node head;
        private Node tail;
        private Node current;
        private Integer sizeOfList;
        private Node currentNode;
        
        private boolean foundMatch = false;

        public ProfileSystem(){

                head = null;
                tail = null;      
                sizeOfList = 0;           
        }

        /* Add the new song 's' to my RadioList
         *
         */
        public void addProfile(String name, int emb) {

                //Instantiate a new Song
                Profile one = new Profile(name, emb);
                //Instantiate a new Node
                //Add the song to the node
                Node node = new Node(one, null, null);
                
                if(sizeOfList == 0) {
                        head = node;
                }
                if (sizeOfList != 0) {
                        tail = node;
                        current.setNext(node);
                        node.setPrevious(current);
                }
                current = node;
                sizeOfList++;
                //Traverse the linked list to find the end; append this new
                //node to the end of the list 
                //increase the size of the list by 1
                //Special case if this is the head of the list

        }

        public void deleteProfile (String name){

                Profile temp = head.getProfile();
                Node currentOne;
                Node previousOne = null;
                Node nextOne = null;
                
                if (temp.getName().equals(name)) {
                        if (head.getNext() != null) {
                                nextOne = head.getNext();
                                head = nextOne;
                                head.setPrevious(null);
                        }
                        else {
                                head = null;
                        }
                        sizeOfList--;
                
                }
                else {
                        currentOne = head;
                        while (foundMatch == false) {
                                if (currentOne.getNext() != null) {
                                        previousOne = currentOne;
                                        currentOne = currentOne.getNext();
                                        if (currentOne.getNext() == null) {
                                                nextOne = null;
                                        }
                                        else {
                                                nextOne = currentOne.getNext();
                                        }
                                        temp = currentOne.getProfile();
                                }
                                else {
                                        System.out.println("No match found");
                                        break;
                                }
                                
                                if (temp.getName().equals(name)) {
                                        previousOne.setNext(nextOne);
                                        if (nextOne != null)
                                        	nextOne.setPrevious(previousOne);
                                        foundMatch = true;
                                        sizeOfList--;
                                }                               
                        }
                }
                /*      Traverse the linked list and check the song title
                 *      If you find it take the following steps:
                 *      1) Take the pointer that pointed the the node containing the song and 
                 *      point it to the song following it
                 *      (Draw a diagram)
                 *      2) Special cases if the node that contains the song is either the head or tail
                 *  or if the list does not contain the song
                 */      
        }


        public Node listProfiles(){

                /*
                 *Starting at the head of the list until you reach the tail,visit each 
                 *node in the linked list and print out the title of the song
                 */
                return head;

        }

        public void insert(int n, String name, int emb) {

                /*  Insert a given name of a song at a index n in the list
                 */
                Node current2 = head;
                Node previousOne = null;
                
                Profile one = new Profile(name, emb);
                Node node = new Node(one, null, null);
                
                if (n > 1 && n <= (sizeOfList + 1)) {
                        for (int s = 1; s < n; s++) {
                                previousOne = current2;
                                current2 = current2.getNext();
                        }
                        previousOne.setNext(node);
                        node.setNext(current2);
                        node.setPrevious(previousOne);
                        if (n!= sizeOfList + 1)
                                current2.setPrevious(node);
                        sizeOfList++;
                }
                
                else if (n == 1 || n == 0) {
                                if (head!= null) 
                                        head.setPrevious(node);
                        node.setNext(head);
                        head = node;
                        head.setPrevious(null);
                        sizeOfList++;
                }
                else if (n > (sizeOfList + 1)) {
                        addProfile(name, emb);
                }
        }
        
        public Profile profileSelected() {
            
            currentNode = head;
            return currentNode.getProfile();
        }
                
        public Integer getSize(){
                return sizeOfList;
        }

        public Profile next() {

                if (currentNode.getNext() != null)
                        currentNode = currentNode.getNext();

                return currentNode.getProfile();
        }

}
