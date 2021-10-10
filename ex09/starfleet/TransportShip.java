package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Set;

public class TransportShip extends MyAbstractSpaceship {

	private final int cargoCapacity;
	private final int passengerCapacity;
	private final int annualMaintenanceCost;


	public TransportShip(String name, int commissionYear, float maximalSpeed,
						 Set<CrewMember> crewMembers, int cargoCapacity, int passengerCapacity) {
		super("TransportShip", name, commissionYear, maximalSpeed, crewMembers);
		this.cargoCapacity = cargoCapacity;
		this.passengerCapacity = passengerCapacity;
		this.annualMaintenanceCost = calcAnnualMaintenanceCost(cargoCapacity, passengerCapacity);

	}

	private int calcAnnualMaintenanceCost(int cargoCapacity, int passengerCapacity) {
		return 3000 + (5 * cargoCapacity) + (3 * passengerCapacity);
	}

	public int getCargoCapacity() {
		return cargoCapacity;
	}

	public int getPassengerCapacity() {
		return passengerCapacity;
	}

	@Override
	public int getAnnualMaintenanceCost() {
		return annualMaintenanceCost;
	}

	@Override
	public String toString() {
		return super.toString()
				+ "\n\tCargoCapacity=" + getCargoCapacity()
				+ "\n\tPassengerCapacity=" + getPassengerCapacity();

	}
}
