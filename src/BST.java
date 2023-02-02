class BSTNode<K extends Comparable<K>, T> {

	public K key;
	public T data;
	public BSTNode<K, T> left, right;
	
	public BSTNode(K key, T data) {
		this.key = key;
		this.data = data;
		left = right = null;
	}
}

public class BST<K extends Comparable<K>, T> implements Map<K, T> {

	BSTNode<K,T> root, current;
	
	public BST() {
		root = current = null;
	}

	public boolean empty() {
		return root == null;
	}

	public boolean full() {
		return false;
	}

	public T retrieve() {
		return current.data;
	}

	public void update(T e) {
		current.data = e;
	}

	public Pair<Boolean, Integer> find(K key) {
		BSTNode<K,T> present = root ;
		int comp = 0;
		
		if (empty() ) {
			return new Pair<Boolean, Integer>(false,comp);
	
		}
		
		while(present != null) {
			comp++;
			// q=p;
			if(key.compareTo(present.key) == 0) {
				current = present;
				return new Pair<Boolean, Integer>(true,comp);
			}
			else if(key.compareTo(present.key) < 0) 
				present = present.left;
			
			else 
				present = present.right;
				
			
		}
		
		return new Pair<Boolean, Integer>(false,comp);
	}		
	/*
	@Override
	public Pair<Boolean, Integer> find(K key) {
		BSTNode<K, T> p = root, q = root;

		int count = 0;
		if (empty()) {
			return new Pair<Boolean, Integer>(false, count) ; 
		}
		
		while (p != null) {
			count++;
			q = p;
			if (key.compareTo(p.key) == 0) {
				current = p;
				return new Pair<Boolean, Integer>(true, count);
			} else if (key.compareTo(p.key) > 0)
				p = p.right;
			else
				p = p.left;
		}

		return new Pair<Boolean, Integer>(false, count);

	}
	
*/
	
	
	@Override
	public Pair<Boolean, Integer> remove(K key) {
		Integer tmp=0 ;	
		
		Pair <Boolean, Integer> Case = new Pair<Boolean , Integer> (false , tmp); 
	
		K key1 = key ;
		
		BSTNode< K , T > p = root;
	    BSTNode< K , T > q = null;
		
		while (p != null && key1.compareTo(p.key) != 0 ) {
			q=p;
		tmp++;
		
		
		if ( key1.compareTo(p.key) < 0)  
			
			p=p.left;
		
		
		
		
		else 
			
			p=p.right;
			
		}
		
		if (p==null) {
			Case.second= --tmp ;
			return Case;
		}
		
		else 
			{
			if ( p.left != null && p.right != null ) {
			
				BSTNode<K,T> S = p.right ; 
				
				q=p ; 
				
				
				while(S.left != null ) {
					q=S ;
					S=S.left;
				}
				
				p.key = S.key;
				p.data = S.data; 
				p = S ;
				key1 = S.key ; 
				
			}
			
			
			if (p.left != null ) 
				p=p.left ;
			
			else 
				p=p.right ;
			//
			if (q==null) 
				root = p;
			
			else {
				if (key.compareTo(q.key) < 0) 
					q.left=p ; 
					
				
				else 
					q.right=p ;
			
			}
			
			current = root ; 
			Case.first=true ;
			Case.second=tmp;
			return Case;
			
			
			}	
		}
	/*
	public List<K> getAll() {
		List<K> l1 = new LinkedList<K>();
		collect(root,l1);
		return l1;
	}
	
	private void collect(BSTNode<K,T> p ,List<K> l1) {
		if(p!=null) {
			collect(p.left,l1);
			l1.insert(p.key);
			collect(p.right,l1);			
		}
	}
	
*/

	public Pair<Boolean, Integer> insert(K key, T data) {
		
		BSTNode<K,T> N = new BSTNode <K,T> ( key , data );
        BSTNode<K,T> p = root ;
        BSTNode<K,T> q = null ;
        Integer tmp = 0 ;
    	Pair <Boolean, Integer> Case = new Pair<Boolean, Integer> (false , tmp);
    	
    if (empty()) {	
    	root = current = N;
    	Case.first=true;
    	
    	return Case ; 
    }
   
		while(p != null) {
			tmp++ ;
			q=p ; 
			
			if (key.compareTo(p.key) == 0 ) {
				Case.second=tmp;
				 return Case ;
			}
			else if(key.compareTo(p.key) > 0)
				p = p.right;
			else
				p = p.left;
		}
		
		if (key.compareTo(q.key) > 0 ) 
		q.right = N ; 
		else 
		q.left = N ;
		
		current = N ;
		Case.first=true;
		Case.second=tmp;
		return Case ; 		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	// 
	
	

	 
		
		
			
			
			
			
			
		
		
		
		
		
		
		
		
	

	
	

	
	@Override
	public List<K> getAll() {
		List <K> L1 = new LinkedList<K> ();
		collect(root , L1) ;
		
		
		return L1 ; 
	}
	
	
	
	private void collect(BSTNode<K,T> p ,List<K> l1) {
		if(p!=null) {
			collect(p.left,l1);
			l1.insert(p.key);
			collect(p.right,l1);			
		}
	}



		



	
		
		
			

		
	

}













