package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class CylonRaider extends MyAbstractFighter {

	public CylonRaider(String name, int commissionYear, float maximalSpeed, Set<Cylon> crewMembers,
			List<Weapon> weapons) {
		super("CylonRaider", name, commissionYear, maximalSpeed, crewMembers, weapons);
	}

	@Override
	public int getAnnualMaintenanceCost() {
		return 3500 + getWeaponsAnnualMaintenanceCost()
				+ (getCrewMembers().size() * 500)
				+ (int) (1200 * getMaximalSpeed());
	}
}
