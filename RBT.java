package hw;

public class RBT {
    public boolean RED = false;
    public boolean BLACK = true;
    public Node nil, root;
    public int NB = 0;
    int[] array = new int[100];
    int index = 0;

    public RBT() {
        nil = new Node(this);
        root = nil;
    }

    public Node rbtMin(RBT T, Node z) {
        while (z.left != T.nil) {
            z = z.left;
        }
        return z;
    }

    public void print() {

    }

    public void RB_Insert(RBT T, Node z) {
        Node y = T.nil;
        Node x = T.root;
        while (x != T.nil) {
            y = x;
            if (z.key < x.key) {
                x = x.left;
            } else
                x = x.right;
        }

        z.p = y;

        if (y == T.nil) {
            T.root = z;
        } else if (z.key < y.key) {
            y.left = z;
        } else
            y.right = z;

        z.left = T.nil;
        z.right = T.nil;
        z.color = RED;
        T.RB_InsertFixUp(T, z);
//        this.tree_print(this.root, 0);
//        System.out.println();
    }

    public void RB_InsertFixUp(RBT T, Node z) {
//        System.out.println("INSERT FIXUP");
        Rotate r = new Rotate();
//		System.out.println(z.key);
//        this.tree_print(this.root, 0);
//        System.out.println();
        while (z.p.color == RED) {
            if (z.p == z.p.p.left) {
                Node y = z.p.p.right;
                if (y.color == RED) {
                    z.p.color = BLACK;
                    y.color = BLACK;
                    z.p.p.color = RED;
                    z = z.p.p;
                }
                else{
                	if (z == z.p.right) {
                    z = z.p;
//                    System.out.println("LEFT ROTATION");
                    r.leftRotate(T, z);
//                    this.tree_print(this.root, 0);
//                    System.out.println();
                	} 
                    z.p.color = BLACK;
                    z.p.p.color = RED;
//                    System.out.println("RIGHT ROTATION");
                    r.rightRotate(T, z.p.p);
//                    this.tree_print(this.root, 0);
//                    System.out.println();
                }
            } else {
                Node y = z.p.p.left;
                if (y.color == RED) {
                    z.p.color = BLACK;
                    y.color = BLACK;
                    z.p.p.color = RED;
                    z = z.p.p;
                } 
                else {
                	if (z == z.p.left) {
                    z = z.p;
//                    System.out.println("RIGHT ROTATION");
                    r.rightRotate(T, z);
//                    this.tree_print(this.root, 0);
//                    System.out.println();
                	} 
                    z.p.color = BLACK;
                    z.p.p.color = RED;
//                    System.out.println("LEFT ROTATION");
                    r.leftRotate(T, z.p.p);
//                    this.tree_print(this.root, 0);
//                    System.out.println();
                }
            }
//            this.tree_print(this.root, 0);
//            System.out.println();
        }
        T.root.color = true;

    }

    public void RB_Delete(RBT T, Node z) {
        Node y = z;
        Node x;

        boolean yOriginCol = y.color;
        {
            if (z.left == T.nil) {
                x = z.right;
                RB_Transplant(T, z, z.right);
            } else if (z.right == T.nil) {
                x = z.left;
                RB_Transplant(T, z, z.left);
            } else {
                y = T.rbtMin(T, z.right);
                yOriginCol = y.color;
                x = y.right;
                if (y.p == z) {
                    x.p = y;
                } else {
                    RB_Transplant(T, y, y.right);
                    y.right = z.right;
                    y.right.p = y;
                }
                RB_Transplant(T, z, y);
                y.left = z.left;
                y.left.p = y;
                y.color = z.color;
            }
        }

        if (yOriginCol == true) {
            RB_DeleteFixUp(T, x);
        }

//        this.tree_print(this.root, 0);
//        System.out.println();

    }

    public void RB_DeleteFixUp(RBT T, Node x) {
//        System.out.println("DELETE FIXUP");
        Rotate r = new Rotate();
        try {
            while (x != T.root && x.color == BLACK) {
                if (x == x.p.left) {
                    Node w = x.p.right;
                    if (w.color == RED) {
                        w.color = BLACK;
                        x.p.color = RED;
//                        System.out.println("LEFT ROTATION");
                        r.leftRotate(T, x.p);
                        w = x.p.right;
                    }

                    if (w.left.color == BLACK && w.right.color == BLACK) {
                        w.color = RED;
                        x = x.p;
                    } else {
                        if (w.right.color == BLACK) {
                            w.left.color = BLACK;
                            w.color = RED;
//                            System.out.println("RIGHT ROTATION");
                            r.rightRotate(T, w);
                            w = w.p.right;
                        }
                        w.color = x.p.color;
                        x.p.color = BLACK;
                        w.right.color = BLACK;
//                        System.out.println("LEFT ROTATION");
                        r.leftRotate(T, x.p);

                        x = T.root;
                    }
                } else {
                    Node w = x.p.left;
                    if (w.color == RED) {
                        w.color = BLACK;
                        x.p.color = RED;
//                      System.out.println("RIGHT ROTATION");
                        r.rightRotate(T, x.p);
                        w = x.p.left;
                    }

                    if (w.right.color == BLACK && w.left.color == BLACK) {
                        w.color = RED;
                        x = x.p;
                    } else {
                        if (w.left.color == BLACK) {
                            w.right.color = BLACK;
                            w.color = RED;
//                          System.out.println("LEFT ROTATION");
                            r.leftRotate(T, w);

                            w = x.p.left;
                        }
                        w.color = x.p.color;
                        x.p.color = BLACK;
                        w.left.color = BLACK;
                        r.rightRotate(T, x.p);
//                        System.out.println("RIGHT ROTATION");
                        x = T.root;
                    }

                }
            }

        } catch (NullPointerException e) {
            e.printStackTrace();

        }
        x.color = BLACK;
    }

    public Node tree_search(Node n, int key) {
        if (n == this.nil)
            return this.nil;
        else if (key == n.key)
            return n;
        else if (key < n.key)
            return tree_search(n.left, key);
        else
            return tree_search(n.right, key);
    }

    public void inorder_iter(Node n) {
        Stack stk = new Stack();
        stk.top = 0;
        stk.stkTree = new Node[10000];

        while (!stk.is_empty() || n.key != 0) {
            if (n != null&&n.key!=0) {
                stk.push(n);
                n = n.left;
            }
            else {
                n = stk.pop();
                if(n.color == BLACK){
                	System.out.println(n.key + " B");
                }
                else if(n.color == RED){
                	System.out.println(n.key + " R");
                }
                
               // i++;
                n = n.right;
            }
        }
//        System.out.println();
//      System.out.println("total : " + i);
//        System.out.println(stk.stkTree.length + " : " + Arrays.toString(stk.stkTree));

    }
   
    public void storeInOrder(Node root, RBT rbt) {
    	if (root == rbt.nil)
    		return;
    	storeInOrder(root.left, rbt);
    	if(root!=rbt.nil){
    		array[index++] = root.key;

    	}
    	storeInOrder(root.right, rbt);
    	
    }
    
    
    public String compare(int x){
    	index = 0;
    	for(int i = 0; i<array.length-1; i++){
    		if(array[i]<x){
    			if(array[i+1]>x){
    				if(array[i]==0 && array[i+1]==0){
    					System.out.print("NIL NIL NIL");
    					return "NIL NIL NIL";
    				}
    				else if(array[i]==0){
    					System.out.print("NIL NIL " + array[i+1]);
    					return "NIL NIL " + array[i+1];
    				
    				}
    				else if(array[i+1]==0){
    					System.out.print(array[i] + " NIL NIL");
    					return array[i] + "NIL NIL \r\n";
    				}
    				else{
    					System.out.print(array[i] + " NIL " + array[i+1]);
    					return array[i] + " NIL " + array[i+1];
    				}
    				
    			}
    			else {
    				for(int j = 0; j<array.length; j++){
    					if(array[j]==0){
    						System.out.print(array[j-1] + " NIL NIL");
    						return array[j-1] + " NIL NIL";
    					}
    				}
    			
    			}
    		}
    		else{
    			System.out.print("NIL NIL " + array[i]);
    			return ("NIL NIL " + array[i]);
    		}
    	}
    	return "";
    }

    

    public int numOfBlack(RBT T, Node r) {
    	if(r == T.nil){
    		return 0;
    	}
    	
    	else{
    		if(r.color == BLACK){
    			NB++;
    		}
    		numOfBlack(T, r.left);
    		numOfBlack(T, r.right);
    	}
    	return NB;
    	
    	
    }

    public void RB_Transplant(RBT T, Node u, Node v) {
        if (u.p == T.nil) {
            T.root = v;
        } else if (u == u.p.left) {
            u.p.left = v;
        } else {
            u.p.right = v;
        }

        v.p = u.p;


    }

    public void tree_print(Node tree, int level) {
        if (tree.right != this.nil)
            tree_print(tree.right, level + 1);
        for (int i = 0; i < level; i++)
            System.out.print("\t");
        if (tree.color)
            System.out.println(String.format("%d%s", tree.key, "B"));
        else
            System.out.println(String.format("%d%s", tree.key, "R"));
        if (tree.left != this.nil)
            tree_print(tree.left, level + 1);
    }
    
    public int heightOfBlack(RBT T, Node r){
    	int height = 0;
    	while(r!=T.nil){
    		if(r.color == BLACK){
    			height++;
    		}
    		r = r.right;
    	}
    	return height;
    }
    
    public static Node findSuccessor(Node node, RBT rbt){
        if (node == null || node == rbt.nil)
            return rbt.nil;
        
        if (node.right != rbt.nil)
            return findMinimum(node.right, rbt);
        
        Node y = node.p;
        Node x = node;
        while (y != rbt.nil && x == y.right)
        {
            x = y;
            y = y.p;
        }

        return y;
    }
    
    public static Node findMinimum(Node node, RBT rbt) {
    	  if (node == rbt.nil)
    		  return rbt.nil;
    	  
    	  if (node.left == rbt.nil)
    		  return node; 
    	  
    	   else
    		   return findMinimum(node.left, rbt);
    	  
    }
    
    public static Node findPredecessor(Node node, RBT rbt){
        if (node == null || node == rbt.nil)
            return rbt.nil;
        
        if (node.left != rbt.nil)
            return findMaximum(node.left, rbt);
                
    
        Node y = node.p;
        Node x = node;
        while (y != rbt.nil && x == y.left)
        {
            x = y;
            y = y.p;
        }
        
        return y;
    }
    
    public static Node findMaximum(Node node, RBT rbt){
    	if(node == rbt.nil)
    		return rbt.nil;
    	
    	if (node.right == rbt.nil)
    		return node;
    	
    	else
    		return findMaximum(node.right, rbt);
    	
    }

    
 

}