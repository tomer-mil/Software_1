package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class ColonialViper extends MyAbstractFighter {

	public ColonialViper(String name, int commissionYear, float maximalSpeed,
						 Set<CrewWoman> crewMembers, List<Weapon> weapons) {

		super("ColonialViper", name, commissionYear, maximalSpeed, crewMembers, weapons);
	}

	@Override
	public int getAnnualMaintenanceCost() {
		return 4000 + getWeaponsAnnualMaintenanceCost()
				+ (getCrewMembers().size() * 500)
				+ ((int) (getMaximalSpeed() * 500));
	}
}
