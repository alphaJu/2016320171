package hw5;

public class Stack {
	public int top;
	public Node[] stkTree;
	
	public boolean is_empty(){
		return top <= 0;
	}
	public void push(Node tree){
		stkTree[top++]=tree;
	}
	public Node pop(){
		return stkTree[--top];
	}

}
