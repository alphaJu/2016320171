package hw;
import java.io.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class Test {
    public static void main(String[] args) throws IOException {
        //String path = Test.class.getResource("").getPath(); 
        RBT rbt = new RBT(); 
        File dir = new File("test01.txt"); 
        File search = new File("search.txt");
        FileWriter w = new FileWriter("output01.txt");

        
		int miss = 0, total = 0,  nb = 0, bh = 1, delete = 0, insert = 0;

		if(dir.isFile())
			System.out.println(dir.getName());
		BufferedReader br = new BufferedReader(new FileReader(dir));
		BufferedReader br2 = new BufferedReader(new FileReader(search));

		while (true) {
			String line = br.readLine();
			
	        if (line == null) 
	        	break;
	        line = line.replaceAll(" ", "");
	        int x = Integer.parseInt(line);
	        if (x == 0) {
	        	break;
	        } 
	            
	        else if (x > 0) {
	        	Node n = new Node(rbt, x);
	            rbt.RB_Insert(rbt, n);
	            insert++;
	            total++;
	            
	            
	        } 
	            
	        else if (x < 0) {
	        	x = -x;
	            Node n = rbt.tree_search(rbt.root, x);

	            if (n == rbt.nil) {
	            	miss++;
	            } 
	                
	            else {
	            	total--;
	                rbt.RB_Delete(rbt, n);
	                delete++;
	            }
	        }

	    }
			
		nb = rbt.numOfBlack(rbt,  rbt.root);
	    bh = rbt.heightOfBlack(rbt, rbt.root);
	    System.out.println("total:" + total);
	    System.out.println("insert:" + insert);
	    System.out.println("delete:" + delete);
	    System.out.println("miss:" + miss);
	    System.out.println("nb:" + nb);
	    System.out.println("bh:" + bh);
	    rbt.inorder_iter(rbt.root);
	   
	    br.close();
		
	    
	    while(true){
	    	String line = br2.readLine();

	    	if(line == null)
	    		break;
	    	line = line.replaceAll(" ", "");
	    	int x = Integer.parseInt(line);
	    	
	    	if(x==0)
	    		break;
	    	
	    	else{
	    		Node n = rbt.tree_search(rbt.root , x);
	    		
	    		if(n==rbt.nil){
	    			rbt.storeInOrder(rbt.root, rbt);
	    			w.write(rbt.compare(x));
	    			w.append("\r\n");
	    			
	    		}
	    		
	    		else{
	    			if(rbt.findPredecessor(n, rbt)==rbt.nil){
	    				System.out.print("NIL ");
	    				w.write("NIL ");
	    			}
	    			else{
	    				System.out.print(rbt.findPredecessor(n, rbt).key + " ");
	    				w.write(rbt.findPredecessor(n, rbt).key + " ");
	    			}	
	    			System.out.print(n.key + " ");
	    			w.append(n.key + " ");
	    			
	    			if(rbt.findSuccessor(n, rbt)==rbt.nil){
	    				System.out.print("NIL ");
	    				w.append("NIL ");
	    				w.append("\r\n");
	    			}
	    			else{
	    				System.out.print(rbt.findSuccessor(n, rbt).key + " ");
	    				w.append(rbt.findSuccessor(n, rbt).key + " ");
	    				w.append("\r\n");
	    				
	    			}
	    		}
	    	}
	    	System.out.println("");
	    			
	    }
	    
	    
	
	
        

	    
       
        w.close();
       
    }
    
}