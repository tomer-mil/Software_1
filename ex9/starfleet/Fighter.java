package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class Fighter extends MyAbstractFighter {

	public Fighter(String name, int commissionYear, float maximalSpeed,
				   Set<? extends CrewMember> crewMembers, List<Weapon> weapons) {
		super("Fighter", name, commissionYear, maximalSpeed, crewMembers, weapons);
	}

	@Override
	public int getAnnualMaintenanceCost() {
		return REGULAR_FIGHTER_ANNUAL_COST + getWeaponsAnnualMaintenanceCost() + (int) (1000 * this.getMaximalSpeed());
	}

}
