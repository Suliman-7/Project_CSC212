class TreeNode<T>{
	Location Loca;
	List<T> infos;
	TreeNode<T> superleft;
	TreeNode<T> normaleft;
	TreeNode<T> normalright;
	TreeNode<T> superright;
	public TreeNode(T dat,Location l) {
		superleft=null;
		normaleft=null;
		normalright=null;
		superright=null;
		Loca=l;
		infos = new LinkedList<T>();
		infos.insert(dat);
		
		
		
	}
}
	
public class TreeLocator<T> implements Locator<T> {
	
	TreeNode<T> root;
	TreeNode<T> current;
	
	
	public TreeLocator() {
		root=current =null;
	}

	
	
	@Override
	public int add(T e, Location loc) {
		TreeNode<T> p = root,q = null;
		if(root == null) {
			root=current= new TreeNode<T>(e,loc);
			return 0;
		}
		int count=0;
		while(p != null) {
			count++;
			
			if(loc.x==p.Loca.x && loc.y==p.Loca.y) {
				
				// p.data.insert(e);
				// return count;
				break;
			}else {
				q=p;
			
			
			
			 if(loc.x < p.Loca.x&&loc.y<=p.Loca.y)
				p = p.superleft;
			else if(loc.x <= p.Loca.x&&loc.y>p.Loca.y)
				p = p.normaleft;
			else if(loc.x > p.Loca.x&&loc.y>=p.Loca.y)
				p = p.normalright;
			
			else if(loc.x >= p.Loca.x&&loc.y<q.Loca.y)//pfromq
				p = p.superright ;
			
		}
		}		
		
		if(p!=null) {
			p.infos.insert(e);
			return count;
		}
		 
		TreeNode<T> faisal=new TreeNode<T>(e,loc);
		 
		if (loc.x < q.Loca.x&&loc.y<=q.Loca.y) {
			q.superleft=faisal;
			}
		else if(loc.x <= q.Loca.x&&loc.y>q.Loca.y)
			q.normaleft=faisal;
		else if(loc.x > q.Loca.x&&loc.y>=q.Loca.y)
			q.normalright=faisal;
		
		else if(loc.x >= q.Loca.x&&loc.y<q.Loca.y)
			q.superright=faisal;
		
		current=faisal;
		return count;
		
	}
	
	
	
	
//if(loc.x==p.loc.x && loc.y==p.loc.y)
	@Override
	public Pair<List<T>, Integer> get(Location loc) {
		
		TreeNode<T> p = root;
		int count=0;
		if(root==null)
			return new Pair <List<T>,Integer>(new LinkedList<T>(),0);
		
		while(p != null) {
			count++;
			if(loc.x==p.Loca.x && loc.y==p.Loca.y) {
				
				return new Pair <List<T>,Integer>(p.infos,count);
			}
			else if(loc.x < p.Loca.x&&loc.y<=p.Loca.y)
				p = p.superleft;
			else if(loc.x <= p.Loca.x&&loc.y>p.Loca.y)
				p = p.normaleft;
			else if(loc.x > p.Loca.x&&loc.y>=p.Loca.y)
				p = p.normalright;
			
			else
				p = p.superright ;
		}
		
		
		return new Pair <List<T>,Integer>(new LinkedList<T>(),count);
	}

	
	public boolean toRemove(List<T>L,T e) {
		if(L.empty())
			return false;
		boolean flag= false;
		L.findFirst();
		while(!L.last()) {
			if(L.retrieve().equals(e)) {
				L.remove();
				flag=true;
			}else
				L.findNext();
			}
			if (L.retrieve().equals(e)) {
				L.remove();
				flag=true;
			}
		return flag;
	}
	
	
	
	@Override
	public Pair<Boolean, Integer> remove(T e, Location loc) {

		TreeNode<T> p = root;
		int count=0;
		if(root==null) {
			return new Pair <Boolean,Integer>(false,0);
		}
		while(p != null) {
			count++;
			if(loc.x==p.Loca.x && loc.y==p.Loca.y) {
				boolean flag=toRemove(p.infos, e);
				if(flag)
					return new Pair <Boolean,Integer>(true,count);
				else 
					return new Pair <Boolean,Integer>(false,count);
		
			}
			else
			{
				if(loc.x < p.Loca.x&&loc.y<=p.Loca.y)
					p = p.superleft;
				else if(loc.x <= p.Loca.x&&loc.y>p.Loca.y)
					p = p.normaleft;
				else if(loc.x > p.Loca.x&&loc.y>=p.Loca.y)
					p = p.normalright;
				
				else {
					p = p.superright ;
				}
			}
		}
		return new Pair <Boolean,Integer>(false,count);
	}
		

	@Override
	public List<Pair<Location, List<T>>> getAll() {
		List <Pair<Location,List<T>>>faisa=new LinkedList<Pair<Location,List<T>>>();
		if (root==null) return faisa;
		getAllinside(root,faisa);
		return faisa;
	}

	private void getAllinside(TreeNode<T>p,List<Pair<Location,List<T>>>faisa){
		if (p==null) {return;}
		else {
			faisa.insert(new Pair <Location,List<T>>(p.Loca,p.infos));
			getAllinside(p.superleft,faisa);
			getAllinside(p.normaleft,faisa);
			getAllinside(p.normalright,faisa);
			getAllinside(p.superright,faisa);
			
		}
	}
	
	
	
	
	private void inRangeRec(Location ll, Location ur, Location lr, Location ul, List<Pair<Location, List<T>>> range, TreeNode<T> node, Pair<List<Pair<Location,List<T>>>, Integer> counter)
	{
		if (node == null)
			return;

		counter.second++;
		
		if ((ll.x <= node.Loca.x) && (ll.y <= node.Loca.y) && (ur.x >= node.Loca.x) && (ur.y >= node.Loca.y))
		{
			Pair<Location, List<T>> one = new Pair<Location, List<T>>(node.Loca, node.infos);
			range.insert(one);
		}
		
		
		if (ll.x < node.Loca.x && ll.y <= node.Loca.y)
			inRangeRec(ll, ur, lr, ul, range, node.superleft, counter);
		
		if (ul.x <= node.Loca.x && ul.y > node.Loca.y)
			inRangeRec(ll, ur, lr, ul, range, node.normaleft, counter);
		
		if (ur.x > node.Loca.x && ur.y >= node.Loca.y)
			inRangeRec(ll, ur, lr, ul, range, node.normalright, counter);
		
		if (lr.x >= node.Loca.x && lr.y < node.Loca.y)
			inRangeRec(ll, ur, lr, ul, range, node.superright, counter);
	}
	
	
	
	@Override
	public Pair<List<Pair<Location, List<T>>>, Integer> inRange(Location lowerLeft, Location upperRight) {
		
	
	List<Pair<Location, List<T>>>mlis = new LinkedList<Pair<Location,List<T>>>();
	Integer intga=0;
	
	Pair<List<Pair<Location, List<T>>> , Integer> range =new Pair<List<Pair<Location, List<T>>>,Integer>(mlis, intga);
	
	Location upL = new Location(lowerLeft.x, upperRight.y);
	Location lowR= new Location(upperRight.x, lowerLeft.y);
	
	
	
	inRangeRec(lowerLeft,upperRight,lowR,upL,mlis,root,new Pair<List<Pair<Location, List<T>>>,Integer>(mlis, intga));
	return range;
	
		
	}
	
	

}
