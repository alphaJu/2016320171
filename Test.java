package hw5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        //String path = Test.class.getResource("").getPath(); 
        RBT rbt = new RBT();
        int countMinus = 0, total = 0,  nb = 0, bh = 1;
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
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

                total++;
            } 
            
            else if (x < 0) {
                x = -x;
                Node n = rbt.tree_search(rbt.root, x);

                if (n == rbt.nil) {
//                  System.out.println(x + " is not in tree");
                    countMinus++;
                } 
                
                else {
//                  System.out.println("delete " + n.key);
                    total--;
                    rbt.RB_Delete(rbt, n);
                }
            }
            //rbt.inorder_iter(rbt.root);
        }
        nb = rbt.numOfBlack(rbt,  rbt.root);
        bh = rbt.heightOfBlack(rbt, rbt.root);
        System.out.println("total:" + total);
        System.out.println("nb:" + nb);
        System.out.println("bh:" + bh);
        rbt.inorder_iter(rbt.root);

        br.close();
    }
}
