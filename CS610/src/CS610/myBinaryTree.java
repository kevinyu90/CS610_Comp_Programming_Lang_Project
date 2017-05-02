package CS610;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class myBinaryTree {

	Node root;
	
	public void Insert(int key){
		
		Node newNode = new Node(key);
		
		if(root == null){
			root = newNode;
		} else {
			Node focusNode = root;
			Node parent;
			
			while(true){
				parent = focusNode;
				if(key == focusNode.key){
					System.out.println("Insert "+ key + " FAILED (already in the list)");
					return;
				}
				if(key < focusNode.key){
					focusNode = focusNode.leftChild;
					if(focusNode == null){
						parent.leftChild = newNode;
						return;
					}
				} else {
					focusNode = focusNode.rightChild;
					if(focusNode == null){
						parent.rightChild = newNode;
						return; 
					}
				}
			}
		}
	}
	public void Delete(int key){
		if(root == null){
			System.out.println("Delete " + key+" FAILED (not in the list)");
		}
		Node focusNode = root;
		Node parent = root;
		boolean isLeftChild = true;
		while(focusNode.key != key){
			if(key > focusNode.key){
				parent = focusNode;
				focusNode = focusNode.rightChild;
				isLeftChild = false;
			} else {
				parent = focusNode;
				focusNode = focusNode.leftChild;
				isLeftChild = true;
			}
			if(focusNode == null){
				break;
			}
		}
		if(focusNode == null){
			System.out.println("Delete " + key+" FAILED (not in the list)");
			return;
		}
		/*刪除節點沒有左右節點*/
		if(focusNode.leftChild == null && focusNode.rightChild == null){
//			System.out.println("Delete: "+focusNode.key);
			if(focusNode.key == root.key)
				root = null;
			if(isLeftChild)
				parent.leftChild = null;
			else
				parent.rightChild = null;
		}
		/*刪除節點只有右節點*/
		else if(focusNode.leftChild == null && focusNode.rightChild != null){
			if(focusNode.key == root.key){
				root = root.rightChild;
			}
			if(isLeftChild)
				parent.leftChild = focusNode.rightChild;
			else
				parent.rightChild = focusNode.rightChild;
		}
		/*刪除節點只有左節點*/
		else if(focusNode.leftChild != null && focusNode.rightChild == null){
			if(focusNode.key == root.key){
				root = root.leftChild;
			}
			if(isLeftChild)
				parent.leftChild = focusNode.leftChild;
			else
				parent.rightChild = focusNode.leftChild;
		}
		/*刪除節點有左右節點*/
		else{
			Node followingNode = this.getFellowingNode(focusNode);
			if(focusNode.key == root.key)
				root = followingNode;
			else if (isLeftChild)
				parent.leftChild = followingNode;
			else
				parent.rightChild = followingNode;
			followingNode.leftChild = focusNode.leftChild;
			followingNode.rightChild = focusNode.rightChild;
		}
		
	}
	private Node getFellowingNode(Node nodeToDel){
		Node parent = nodeToDel;
		Node node = nodeToDel.rightChild;
		while(node.leftChild != null){
			parent = node;
			node = node.leftChild;
		}
		if(node.key != nodeToDel.rightChild.key)
			parent.leftChild = node.rightChild;
		else
			parent.rightChild = node.rightChild;
		return node;
		
	}
	public void Search(int key){
		
		if(root == null){
			System.out.println("There is no " + key);
		} else{
			Node focusNode = root;
//			Node parent;
			
			while(true){
//				parent = focusNode;
				if(key == focusNode.key){
					System.out.println("Find "+ key );
					return;
				}
				if(key < focusNode.key){
					focusNode = focusNode.leftChild;
					if(focusNode == null){
						System.out.println("There is no " + key);
						return;
					}
				} else {
					focusNode = focusNode.rightChild;
					if(focusNode == null){
						System.out.println("There is no " + key);
						return; 
					}
				}
				
			}
			
		}
		
	}
	
	public void Print(Node focusNode){
		if(focusNode != null){
			System.out.print("(");
			Print(focusNode.leftChild);
			
			System.out.print(focusNode.value());
			
			Print(focusNode.rightChild);
			System.out.print(")");
		}
	}
	
	public void min(Node focusNode){
		if(focusNode != null){
			System.out.println(focusNode.minValue());
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		myBinaryTree theTree = new myBinaryTree();
		
		System.out.println("Please specify the input file (default = f.txt):");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String pathname = "f.txt";
		pathname = sc.nextLine();
		if(pathname.equalsIgnoreCase("")){
			pathname = "f.txt";
		}
		System.out.println("Open File: "+pathname);
		/* Read File*/
		@SuppressWarnings("resource")
		BufferedReader in = new BufferedReader(new FileReader(pathname));
		String line;
		String status = null;
		while((line = in.readLine()) != null){
//			System.out.println("指令:"+line);
			for(String get : line.split(" ")){
//				System.out.println(get);
				if(get.charAt(0) == 'I'){
					status = get ;
				}else if(get.charAt(0) == 'D'){
					status = get ;
				}else if(get.charAt(0) == 'P'){
					status = get ;
					theTree.Print(theTree.root);
					System.out.println("");
				}else{
					if(status.charAt(0) == 'I'){
						theTree.Insert(Integer.parseInt(get));
					}
					if(status.charAt(0) == 'D'){
						theTree.Delete(Integer.parseInt(get));
					}
				}
			}
		}
//		theTree.Print(theTree.root);
		/*myBinaryTree Tree = new myBinaryTree();

		Tree.Insert(60);
		Tree.Insert(90);
		Tree.Insert(100);
		Tree.Insert(20);
		Tree.Insert(30);
		Tree.Insert(80);
		Tree.Insert(10);

		
		Tree.Print(Tree.root);
		System.out.println("");
		
		Tree.Insert(70);
		Tree.Insert(75);
		Tree.Insert(40);
		Tree.Insert(35);
		Tree.Insert(32);
		Tree.Insert(78);
		Tree.Insert(72);
		Tree.Insert(37);
		
		Tree.Print(Tree.root);
		System.out.println("");
		
		Tree.Delete(60);
		
		Tree.Print(Tree.root);
		System.out.println("");
		
		Tree.Delete(30);
		Tree.Delete(80);
		Tree.Delete(10);
		Tree.Delete(100);
		
		Tree.Print(Tree.root);
		System.out.println("");
		
		Tree.Insert(90);
		Tree.Delete(5);*/

	}
}

class Node {
	int key;
	
	Node leftChild;
	Node rightChild;
	
	Node(int key){
		this.key = key;
	}
	public int value(){
		return key;
	}
	public int minValue(){
		if(leftChild == null)
			return key;
		else
			return leftChild.minValue();
		
	}
}