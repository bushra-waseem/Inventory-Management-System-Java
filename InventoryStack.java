package semesterproject;

public class InventoryStack {
    
    class Node{       //step 1 node class
        Item data;   // Item store karega
        Node next;     // Agla node batayega

        public Node(Item data) {   //node constructor
            this.data = data;    // Item ko store karo
            this.next = null;    // Abhi kisi se link nahi (null)
        }       
    } 
    
    Node top = null;   //Top batata hai sabse upar kaunsa item hai.
    
    public void push(Item item){    // step 3 push
        Node newNode = new Node(item);  // Naya node banao step 1
        if(top==null)                   // Agar stack empty hai means null ha step 2
            top = newNode;              // jo first item dalogy wohi top bnjaiga step 3 end
        else{                           // if bna hua ha phly se node 
            newNode.next = top;         // Naya node purane top se link kro step 3
            top = newNode;              //ab ye naya node top ha step 4
        }
    }
    
    public Item pop(){              // step 4 pop
        if(top==null){                 // Agar stack empty ha to underflow hojaiga
            System.out.println("Stack Underflow");
            return null;
        }else{
            Item poppedItem = top.data;   // Top ka item nikalo
            System.out.println("Removed Item: " + poppedItem);
            top = top.next;               // Top ko neeche move karo
            return poppedItem;            //item return kro
        }
    }
    
    public Item peek(){              //step 5 peek
        if(top==null){
            System.out.println("Stack is Empty");
            return null;
        }else{
            return top.data;       // Bas dekho, remove mat karo
        }
    }
    
    public boolean isEmpty(){     // step 6 //Check karo stack mein kuch hai ya nahi.
        if(top==null)             //empty ha
            return true;
        else
            return false;         //nhi, items ha
    }
    
    public String[] getAllItems(){    //step 7 get all items 
        if(top==null){
            return new String[0];    //empty array ko return kro
        }
        
        int count = 0;       //count kro kitne items ha 
        Node temp = top;
        while(temp != null){
            count++;          
            temp = temp.next;
        }
        
        // Create string array and fill it
        String[] items = new String[count];       // string Array bana ke items dalo
        temp = top;
        int index = 0;
        while(temp != null){
            items[index] = temp.data.toString();
            temp = temp.next;
            index++;
        }
        
        return items;
    }
    
    public int getSize(){       //step 8 counting
        int count = 0;      //count shuru kro 0 se
        Node temp = top;    // top se shuru kro
        while(temp != null){  // jb tk nodes ha 
            count++;          //count badhao
            temp = temp.next;  // agy jao
        }
        return count;        //total count return kro
    }
}