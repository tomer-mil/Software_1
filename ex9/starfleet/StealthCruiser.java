package il.ac.tau.cs.sw1.ex9.starfleet;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class StealthCruiser extends MyAbstractFighter {

	public static int numberOfStealthCruisers = 0;


	public StealthCruiser(String name, int commissionYear, float maximalSpeed,
						  Set<CrewMember> crewMembers, List<Weapon> weapons) {
		super("StealthCruiser", name, commissionYear, maximalSpeed, crewMembers, weapons);
		numberOfStealthCruisers++;
	}

	public StealthCruiser(String name, int commissionYear, float maximalSpeed,
						  Set<CrewMember> crewMembers) {
		this(name, commissionYear, maximalSpeed, crewMembers,
				Collections.singletonList(new Weapon("Laser Cannons", 10, 100)));
	}


	@Override
	public int getAnnualMaintenanceCost() {
		return REGULAR_FIGHTER_ANNUAL_COST + getWeaponsAnnualMaintenanceCost()
				+ (int) (1000 * this.getMaximalSpeed())
				+ (numberOfStealthCruisers * 50);
	}

}
