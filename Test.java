package hw;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class Test {
    public static void main(String[] args) throws IOException {
        //String path = Test.class.getResource("").getPath(); 
        RBT rbt = new RBT();
        int miss = 0, total = 0,  nb = 0, bh = 1, delete = 0, insert = 0;
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        File dir = new File("./input/"); 
		File[] fileList = dir.listFiles(); 
		
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            line = line.replaceAll(" ", "");
            int x = Integer.parseInt(line);
            if (x == 0) {
                break;
            } 
            
            else if (x > 0) {
                Node n = new Node(rbt, x);
//              System.out.println("insert " + n.key);
                rbt.RB_Insert(rbt, n);
                insert++;
                total++;
            } 
            
            else if (x < 0) {
                x = -x;
                Node n = rbt.tree_search(rbt.root, x);

                if (n == rbt.nil) {
//                  System.out.println(x + " is not in tree");
                    miss++;
                } 
                
                else {
//                  System.out.println("delete " + n.key);
                    total--;
                    rbt.RB_Delete(rbt, n);
                    delete++;
                }
            }
            //rbt.inorder_iter(rbt.root);
        }

	    for(int i = 0 ; i < fileList.length ; i++){
	    	File file = fileList[i]; 
			if(file.isFile())
			System.out.println(file.getName());
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
    }
    
}