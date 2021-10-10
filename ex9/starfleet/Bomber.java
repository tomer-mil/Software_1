package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class Bomber extends MyAbstractFighter{

	private final int numberOfTechnicians;

	public Bomber(String name, int commissionYear, float maximalSpeed,
				  Set<CrewMember> crewMembers, List<Weapon> weapons, int numberOfTechnicians){
		super("Bomber", name, commissionYear, maximalSpeed, crewMembers, weapons);
		this.numberOfTechnicians = numberOfTechnicians;
	}


	public int getNumberOfTechnicians() {
		return this.numberOfTechnicians;
	}


	@Override
	public int getAnnualMaintenanceCost() {
		return 5000 + (int) (getWeaponsAnnualMaintenanceCost() * (1 - (getNumberOfTechnicians() * 0.1)));
	}

	@Override
	public String toString() {
		return super.toString()
				+ "\n\tNumberOfTechnicians=" + getNumberOfTechnicians();
	}
}
