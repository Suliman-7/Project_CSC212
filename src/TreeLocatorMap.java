

public class TreeLocatorMap<K extends Comparable<K>> implements LocatorMap<K> {

	Map <K,Location> bst ; 
	Locator<K> Loc ;
	
	public  TreeLocatorMap () {
		
		bst = new BST <K,Location> () ;
		Loc = new TreeLocator<K> () ;
		
	}
	
	
	@Override
	public Map<K, Location> getMap() {

		return bst;
	}

	@Override
	public Locator<K> getLocator() {
		return Loc;
	}

	@Override
	public Pair<Boolean, Integer> add(K k, Location loc) {

		Pair<Boolean, Integer> Get = bst.insert(k, loc);
		
		if(Get.first==true) {
			Loc.add(k, loc);
		}
		return Get ; 
			
		}
	

	@Override
	public Pair<Boolean, Integer> move(K k, Location loc) {
		
		Pair<Boolean, Integer> Opt = bst.find(k);
		if(Opt.first==true) {
			Loc.remove(k, bst.retrieve());
			Loc.add(k, loc);
			bst.update(loc);
		}
		return Opt ; 
	}

	
	
	
	@Override
	public Pair<Location, Integer> getLoc(K k) {
		
		Pair<Boolean, Integer> Opt = bst.find(k);
		
	   
	
		
		if(Opt.first==true) 
	return new Pair<Location, Integer>(bst.retrieve(), Opt.second);
			else
	return new Pair<Location, Integer>(null, Opt.second);

	}

	
	
	@Override
	public Pair<Boolean, Integer> remove(K k) {
		Pair<Boolean, Integer> Get = bst.find(k);
		if(Get.first==true) {
			Loc.remove(k, bst.retrieve());
			bst.remove(k);
		}
		return Get ; 
	}

	@Override
	public List<K> getAll() {
		return bst.getAll();
	}

	private boolean search(List<K> l , K e) {
		if (l.empty()) {
			return false;
		}
		l.findFirst();
		while(!l.last()) {
			if (l.retrieve().compareTo(e) == 0) {
				return true;
			}
			l.findNext();
		}
		if (l.retrieve().compareTo(e) == 0) {
			return true;
		}
		return false;
	}
	
	private void add_all(List<K> l1 , List <K> l2) {
		if(l2.empty()) {
			return;
		}
		l2.findFirst();
		while(!l2.last()) {
			if(!search(l1,l2.retrieve())) {
				l1.insert(l2.retrieve());
			}
			l2.findNext();
		}
		if(!search(l1,l2.retrieve())) {
			l1.insert(l2.retrieve());
		}
		l2.findNext();
	}
	
	public Pair<List<K>, Integer> getInRange(Location lowerLeft, Location upperRight) {
		Pair<List<Pair<Location , List<K>>> , Integer> rl = Loc.inRange(lowerLeft, upperRight);
		List <Pair<Location , List<K>>> lists = rl.first;
		List<K> keInRe = new LinkedList<K>();
		if(!lists.empty()) {
			lists.findFirst();
			while (!lists.last()) {
				add_all(keInRe , lists.retrieve().second);
				lists.findNext();
			}
			add_all(keInRe , lists.retrieve().second);
		}
		return new Pair<List<K> , Integer>(keInRe , rl.second);
	}

	

}
		
		
		
		
	
  

