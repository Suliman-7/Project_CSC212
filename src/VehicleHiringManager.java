
public class VehicleHiringManager {
	
	LocatorMap <String> LM ;

	public VehicleHiringManager() {
		LM = new TreeLocatorMap<String>() ;
	}

	
	public LocatorMap<String> getLocatorMap() {
		return LM;
	}

	
	public void setLocatorMap(LocatorMap<String> locatorMap) {
		LM = locatorMap ;
	}


	public boolean addVehicle(String id, Location loc) {
	Pair <Boolean , Integer > Get = LM.add(id, loc) ;
		return Get.first;
	}

	
	public boolean moveVehicle(String id, Location loc) {
		Pair <Boolean , Integer > Get = LM.move(id, loc) ;
		return Get.first;
	}

	
	public boolean removeVehicle(String id) {
		Pair <Boolean , Integer > Opt = LM.remove(id) ;
		return Opt.first;
	}

	
	public Location getVehicleLoc(String id) {
		Pair <Location , Integer > Opt = LM.getLoc(id) ;

		return Opt.first;
	}

	
	public List<String> getVehiclesInRange(Location loc, int r) {
		
		
	Pair <List<String>, Integer> Opt = LM.getInRange(new Location(loc.x-r,loc.y-r), new Location(loc.x+r, loc.y+r) );
		return Opt.first;
	}
}
