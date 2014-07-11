
enum Color {RED,BLACK}
public class RedBlack {
	class TreeNode{
		public Color color;
		public TreeNode left;
		public TreeNode right;
		public TreeNode parent;
		public int key;
		public TreeNode(int key,Color color,TreeNode parent){
			this.key = key;
			this.color = color;
			this.parent = parent;
		}
	}
	TreeNode root;
	public void walk(TreeNode root){
		if(root==null) return;
		System.out.print(root.key+" ");
		walk(root.left);
		walk(root.right);
	}
	
	public int insert(int key){
		//return number of twist
		if(root==null) {
			root = new TreeNode(key,Color.BLACK,null);
			return 0;
		}
		TreeNode t = root;
		while(true){
			if(key<t.key){
				if(t.left==null) break;
				t = t.left;
			}else{
				if(t.right==null) break;
				t = t.right;
			}
		}
		// key should be a child of t
		if(key<t.key) {
			t.left = new TreeNode(key,Color.RED,t);
			t = t.left;
		}
		else {
			t.right = new TreeNode(key,Color.RED,t);
			t = t.right;
		}
		
		//we need twist!
		int twists = 0;
		
		while(t.color==Color.RED && t.parent.color == Color.RED){
			/*System.out.println("key ="+key);
			walk(root);
			System.out.println("t="+t.key);
			System.out.println();*/
			twists ++;
			TreeNode parent = t.parent;
			TreeNode grand = parent.parent;
			TreeNode t1,t2,t3,t4;
			TreeNode x,y,z;
			//4 cases
			if(grand.left == parent && parent.left == t){
				x = t;
				y = parent;
				z = grand;
				t1 = x.left;
				t2 = x.right;
				t3 = y.right;
				t4 = z.right;
				
			}else if(grand.right==parent && parent.right==t){
				x = grand;
				y = parent;
				z = t;
				t1 = x.left;
				t2 = y.left;
				t3 = z.left;
				t4 = z.right;
				
			}else if(grand.left == parent && parent.right == t){
				x = parent;
				y = t;
				z = grand;
				t1 = x.left;
				t2 = y.left;
				t3 = y.right;
				t4 = z.right;
				
			}else {
				x = grand;
				y = t;
				z = parent;
				t1 = x.left;
				t2 = y.left;
				t3 = y.right;
				t4 = z.right;
			}
			
			y.parent = grand.parent;
			if(y.parent!=null){
				if(grand.parent.left == grand) grand.parent.left = y;
				else grand.parent.right = y;
			}
			t = y;
			
			x.left = t1;
			x.right = t2;
			if(t1!=null)
				t1.parent = x;
			if(t2!=null)
				t2.parent = x;
			
			z.left = t3;
			z.right = t4;
			if(t3!=null)
				t3.parent = z;
			if(t4!=null)
				t4.parent = z;
			
			x.color = Color.BLACK;
			z.color = Color.BLACK;
			y.left = x;
			y.right = z;
			
			x.parent = y;
			z.parent = y;
			
			//recolor root to black
			if(t.parent == null) {
				t.color = Color.BLACK;
				this.root = t;
			}
			
			/*System.out.println("after twist");
			walk(this.root);
			System.out.println();*/
		}
		
		return twists;
	}
	
	public int numTwists(int[] keys) {
		root = null;
		int twists = 0;
		for(int key:keys)
			twists += insert(key);
		return twists;
	}
	public static void main(String[] args){
		RedBlack rb = new RedBlack();
		System.out.println(rb.numTwists(new int[]{ 1,2,3,4,5,6,7 }));
		
		System.out.println(rb.numTwists(new int[]{ 6,8,10,12,4,2,18,14,16,19,7,15,9,17,13,5,11,3,1 }));
		System.out.println(rb.numTwists(new int[]{ 5,10,15,14,3,4,11,2,1,12,6,9,7,13,8 }));
		//System.out.println(Color.RED);
	}
}
