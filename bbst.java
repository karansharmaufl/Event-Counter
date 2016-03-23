


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


class NewNode
{
    int id;
    int counter;
    char color;
    NewNode left;
    NewNode right;
    
    
     //Constructor
     public NewNode(int ID,int Counter , NewNode lt, NewNode rt)
     {
         left = lt;
         right = rt;
         id = ID;
         counter=Counter;
         color = 'b';
     }   
     
     public NewNode(int ID , int Counter)
     {
         this( ID, Counter , null, null );
     } 
     
     
}

 class RedBlackTree
 {
    
     public NewNode p; // Present Node
     public NewNode pt; // Parent Of Present Node
     public NewNode gpt; // Grand Parent Of Present Node
     public NewNode great; // Great Grand Parent Of Present Node
     public NewNode root; // Root Node  
     private static NewNode nullNode; // Null Node
     int sum=0; // Variable declared globally used in functions
     
     static 
     {
         nullNode = new NewNode(0,0);  // Creates a node with ID =0 and Counter =0
         nullNode.left = nullNode;
         nullNode.right = nullNode;
     }
     
     static final int BLACK = 1;    
     static final int R   = 0;
 
     // Constructor for Creating A New Node
     public RedBlackTree(int a , int b)
     {
         root = new NewNode(a,b);  
         root.left = nullNode; 
         root.right = nullNode; 
     }   
     // Insertion In An RB Tree
     public void insert(int ID , int counter )
     {
         p = pt = gpt = root;
         nullNode.id = ID; // Enter the id of the event
         nullNode.counter=counter; // Enter the count of the event
         while (p.id != ID) // While current Nodes ID Is Not Equal To ID
         {            
             great = gpt; 
             gpt = pt; 
             pt = p;
             
             if(ID<p.id)  // If ID Less Than Current ID Traverse Left Tree
                 p=p.left;
             else
                 p=p.right;// If ID Less Than Current ID Traverse Right Tree
             
             // Check if two red children and fix if so            
             if (p.left.color == R && p.right.color == R)
                 insertFix( ID );
         }
         if (p != nullNode)
             return;
         p = new NewNode(ID, counter , nullNode, nullNode);    
         if (ID < pt.id) // If ID Less Than Parent ID Make p Left Child Of pt
             pt.left = p; 
         else
             pt.right = p; // If ID Less Than Parent ID Make p Left Child Of pt      
         insertFix( ID );
     }
     private void insertFix(int ID)
     {
         p.color = R; // Recolor
         p.left.color = BLACK;
         p.right.color = BLACK;
 
         if (pt.color == R)   
         {
             // Have to rotate
             gpt.color = R;
             if (ID < gpt.id != ID < pt.id)
                 pt = rotate( ID, gpt );  
             p = rotate(ID, great );
             p.color = BLACK;
         }
         // Make root black
         root.right.color = BLACK; 
     }      
     private NewNode rotate(int ID, NewNode pt)
     {
         if(ID < pt.id)
             {
                 if(ID < pt.left.id)
                    pt.left= rotateLeft(pt.left);
                             
                  else
                    pt.left= rotateRight(pt.left) ; 
                 return pt.left;
             }
             
         else
         {
             if(ID<pt.right.id)
                pt.right = rotateLeft(pt.right);
             
             else
                 pt.right = rotateRight(pt.right);
                 
                 return pt.right;
         }           
     }
     // Rotate with left child 
     private NewNode rotateLeft(NewNode k2)
     {
         NewNode k1 = k2.left;
         k2.left = k1.right;
         k1.right = k2;
         return k1;
     }
     // Rotate with right child 
     private NewNode rotateRight(NewNode k1)
     {
         NewNode k2 = k1.right;
         k1.right = k2.left;
         k2.left = k1;
         return k2;
     }
    
     public int Increase(int theID , int m ) // Increase Function
       {
           NewNode r;
           r=root.right; // Root Node
    
           while(r!=null && r.id!=theID  )
           {
               if(theID<r.id)
                   r=r.left;   
               
               if(theID>r.id)
                   r=r.right;
               
               if(r==nullNode)  // If Node Is Not Present Insert Node
               {
                   insert(theID,m);
                   r.counter=r.counter-m;
                   break;
               }   
           } 
            return r.counter=r.counter+m; // Returns Increased Count Of The ID        
       }
     
     
     
     public int Reduce(int theID , int m ) // Decrease Function
     
       {
           NewNode r;
           r=root.right; // Root Node
           while(r!=null && r.id!=theID )
           {
               if(theID<r.id)
                   r=r.left;
          
               if(theID>r.id)
                   r=r.right;
           }
		r.counter=r.counter-m;
           if(r.counter <= 0){
              return 0; }
           else {
           return r.counter;}
     // Returns Decreased Count for theID    
       }
     
      public int Count(int theID )
       {
           NewNode r;
           r=root.right; // Root Node
           
           while(r!=null && r.id!=theID)
           {
               if(theID<r.id)
                 r=r.left;
               
               if(theID>r.id)
                 r=r.right;  
           }

            if(r.counter <= 0){
              return 0; }
           else {
           return r.counter; 
	}  // Returns Count For theID          
       }
     
      
      
      public int inRange(int theID1 , int theID2)
      {
          return  inRange(root.right , theID1 , theID2);
      }
      
      private int inRange(NewNode root ,int theID1 , int theID2)
      {
         if (root == nullNode)
             return 0;
        
         inRange(root.left, theID1, theID2);
 
         if ( theID1 <= root.id && theID2 >= root.id ) // If id is b/w ID1 and ID2 add counter to sum
         {
            sum = sum +root.counter;  
         }
             inRange(root.right, theID1, theID2);
         
         return sum;
      }
      
      
      
      
     /* Function for inorder traversal */ 
     public void inorder()
     {
         inorder(root.right); // Pass root node in inorder
     }
     private void inorder(NewNode r)
     {
         if (r != nullNode)
         {
             inorder(r.left);
             char c = 'B';
             if (r.color == 0)
                 c = 'R';
             System.out.println(r.id +""+c+" ");
             inorder(r.right);
         }
     }

     public int leastValue(NewNode r) // Returns the least value in left subtree
        {
            if (r == nullNode)
                return 0;

            if (r.left != nullNode)
                return leastValue(r.left);

            return r.id;
        }

    public int next(int item)
            {
                return next(root.right,item);
            }

    private int next(NewNode r, int item) // Prints the nextID of any entered ID
    {
            int a=0;
             while ((r != nullNode) && a==0)
             {
                 int rval = r.id;
                 if (item < rval)
                     r = r.left;
                 else if (item > rval)
                     r = r.right;
                 else
                 {
                     a = 1;
                     break;
                 }
             }

            if(a == 1) // If ID present print the next ID
            {
             if( r.right != nullNode)
             return leastValue(r.right);

                NewNode greater = nullNode;
                NewNode newroot=root;
                while (newroot != nullNode)
                {
                    if (r.id < newroot.id)
                    {
                        greater = newroot;
                        newroot = newroot.left;
                    }
                    else if (r.id > newroot.id)
                        newroot = newroot.right;
                    else
                       break;
                }
                if(greater.right==nullNode)
                {
                    return 0;
                }
                else
                return greater.id;


            }

            else // If ID not presnt
            {
                 int c  = findValid_ID(item);
                 if (c < item)
                 {
                    return next(c);
                 }
                else
                {
                    return c;
                }

             }


    }



    public int greatestValue(NewNode r) // Finds the greatest ID in right subtree
    {
        if (r == nullNode)

            return 0;

        if (r.right != nullNode)
            return greatestValue(r.right);

        return r.id;
    }
    public int ctemp;
    public int previous(int item){
        return previous(root.right,item);
    }

    private int previous(NewNode r, int item)
    {

             int a=0;
             while ((r != nullNode) && a==0)
             {
                 int rval = r.id;
                 if (item < rval)
                     r = r.left;
                 else if (item > rval)
                     r = r.right;
                 else
                 {
                     a = 1;
                     break;
                 }
             }

            if(a == 1) // if theID exist print previous
            {


        if( r.left != nullNode )
            return greatestValue(r.left);

        NewNode smaller = nullNode;
        NewNode root1 = root;

        while (root1 != nullNode)
        {
            if (r.id > root1.id)
            {
                smaller = root1;
                root1 = root1.right;
            }
            else if (r.id < root1.id)
                root1 = root1.left;
            else
               break;
        }
	ctemp =  smaller.counter;
        if (smaller.left == nullNode)
		return 0;
	else
	return smaller.id;
	
            }

            else // If theID doesnt exist find the valid  ID
            {
                 int c  = findValid_ID(item);
                 if (c < item)
                 {
		    ctemp = Count(c);
                    return c;
                 }
                 else
                 {
                    return previous(c);
                 }

             }


    }


    NewNode node_tree ; // Points to node which exists in tree
    int i;
    NewNode node_tree_find; // Points to node whose next or previous is to be found

    public int findValid_ID(int val) // Finds a valid ID
    {
        return findValid_ID(root.right,val);
    }

    public int findValid_ID(NewNode node,int theID){
        if (node == nullNode)
            return 0;
        node_tree = node;
        i = Math.abs(theID - node_tree.id);
        node_tree_find = node;
        while (node_tree_find != nullNode) 
        {
            if (theID == node_tree_find.id) // ID exists in the tree
                return node_tree_find.id;
            
            if (Math.abs(theID - node_tree_find.id) < i)
            {
                node_tree = node_tree_find;
                i = Math.abs(theID - node_tree_find.id);
              }
            
            if (theID < node_tree_find.id)
                node_tree_find = node_tree_find.left;
            
            else
                node_tree_find = node_tree_find.right;
        }
        return node_tree.id;

}


 }
 

public class bbst
{

    public static void main(String[] args) 
    {   
      
        RedBlackTree rbt = new RedBlackTree(Integer.MIN_VALUE , Integer.MAX_VALUE); 
        
        File inFile = null;
        if (0 < args.length) {
            inFile = new File(args[0]);
        } 
        else {
            System.err.println("Invalid arguments count:" + args.length);
            System.exit(0);
            }

    BufferedReader br = null;

    try {
        String s1;
        String s2;
        br = new BufferedReader(new FileReader(inFile));
        s1 = br.readLine();
        int j = Integer.parseInt(s1);
        
        while ((s2 = br.readLine()) != null) 
        {
            
            String[] parts = s2.split(" ");
            int part0 = Integer.parseInt(parts[0]);
            int part1 = Integer.parseInt(parts[1]);
            rbt.insert(part0,part1);
        }
	//rbt.inorder();
   		    Scanner input = new Scanner(System.in);
                    int c=0;
                    while (input.hasNextLine()) 
                    {
                      String curr = input.nextLine();
			String parts1[]=curr.split(" ");
                      if (parts1[0].matches("increase"))
                      {
                        int arg1 = Integer.parseInt(parts1[1]);
                        int arg2 = Integer.parseInt(parts1[2]);

                        System.out.println(rbt.Increase(arg1,arg2));
                      }

                      else if (parts1[0].matches("reduce"))
                      {
                        int arg1 = Integer.parseInt(parts1[1]);
                        int arg2 = Integer.parseInt(parts1[2]);
                        System.out.println(rbt.Reduce(arg1,arg2));
                      }
                    
                    else if (parts1[0].matches("count"))
                      {
                        int arg1 = Integer.parseInt(parts1[1]);
                        
                        System.out.println(rbt.Count(arg1));
                      }
                        
                    else if (parts1[0].matches("inrange"))
                      {
                        int arg1 = Integer.parseInt(parts1[1]);
                        int arg2 = Integer.parseInt(parts1[2]);
                        System.out.println(rbt.inRange(arg1,arg2));
                        rbt.sum = 0;
                      }

                      else if (parts1[0].matches("next"))
                      {
                        int arg1 = Integer.parseInt(parts1[1]);
                       
                        if (rbt.next(arg1) != 0){
                            System.out.println(rbt.next(arg1)+" "+rbt.Count(rbt.next(arg1)));
                        }
                        else {
                            System.out.println("0 0");
                        }
                      }

                      else if (parts1[0].matches("previous"))
                      {
                        int arg1 = Integer.parseInt(parts1[1]);
                       
                        if (rbt.previous(arg1) != 0)
                        {   
                            int temp = rbt.previous(arg1);
                            System.out.println(temp +" "+rbt.ctemp);
                        }
                        else 
                        {
                            System.out.println("0 0");
                        }
                      

                      }

                      else if (parts1[0].matches("quit"))
                      { 
                        System.exit(0);
                      }

                     }         
    }




catch (IOException e) {
        e.printStackTrace();
    }     
}
}
   

