package il.ac.tau.cs.sw1.ex9.starfleet;


import com.sun.xml.internal.bind.v2.TODO;

import java.util.*;
import java.util.stream.Collectors;


public class StarfleetManager {

	/**
	 * Returns a list containing string representation of all fleet ships, sorted in descending order by
	 * fire power, and then in descending order by commission year, and finally in ascending order by
	 * name
	 */
	public static List<String> getShipDescriptionsSortedByFirePowerAndCommissionYear (Collection<Spaceship> fleet) {

		return fleet.stream().sorted((o1, o2) -> {
			if (o1.getFirePower() == o2.getFirePower()) {
				if (o1.getCommissionYear() == o2.getCommissionYear()) {
					return o1.getName().compareTo(o2.getName());
				}
				return Integer.compare(o2.getCommissionYear(), o1.getCommissionYear());
			}
			return Integer.compare(o2.getFirePower(), o1.getFirePower());
		}).map(Object::toString).collect(Collectors.toList());
	}

	/**
	 * Returns a map containing ship type names as keys (the class name) and the number of instances created for each type as values
	 */
	public static Map<String, Integer> getInstanceNumberPerClass(Collection<Spaceship> fleet) {

		Map<String, Integer> instanceCountMap = new HashMap<>();

		for (Spaceship spaceship : fleet) {
			String spaceshipType = spaceship.getClass().getSimpleName();
			if (!instanceCountMap.containsKey(spaceshipType)) {
				instanceCountMap.put(spaceshipType, 1);
			}
			else {
				int currentCount = instanceCountMap.get(spaceshipType);
				instanceCountMap.put(spaceshipType, ++currentCount);
			}
		}

		return instanceCountMap;

	}


	/**
	 * Returns the total annual maintenance cost of the fleet (which is the sum of maintenance costs of all the fleet's ships)
	 */
	public static int getTotalMaintenanceCost (Collection<Spaceship> fleet) {
		return fleet.stream().reduce(0, (x, y) -> x + y.getAnnualMaintenanceCost(), Integer::sum);
	}


	/**
	 * Returns a set containing the names of all the fleet's weapons installed on any ship
	 */
	public static Set<String> getFleetWeaponNames(Collection<Spaceship> fleet) {

		Set<String> weaponsNames = new HashSet<>();
		fleet.stream().filter(x -> x instanceof MyAbstractFighter)
				.map(spaceship -> ((MyAbstractFighter) spaceship).getWeapon().stream()
						.map(Weapon::getName).collect(Collectors.toSet()))
				.forEach(weaponsNames::addAll);
		return weaponsNames;
	}

	/*
	 * Returns the total number of crew-members serving on board of the given fleet's ships.
	 */
	public static int getTotalNumberOfFleetCrewMembers(Collection<Spaceship> fleet) {
		return fleet.stream().map(spaceship -> spaceship.getCrewMembers().size()).reduce(0, Integer::sum);
	}

	/*
	 * Returns the average age of all officers serving on board of the given fleet's ships. 
	 */
	public static float getAverageAgeOfFleetOfficers(Collection<Spaceship> fleet) {

		List<Officer> allOfficersInFleet = getAllOfficersList(fleet);
		Optional<Integer> totalAges = allOfficersInFleet.stream().map(Officer::getAge).reduce(Integer::sum);
		float ageAverage = 0f;
		if (totalAges.isPresent()) {
			ageAverage =  totalAges.get().floatValue() / allOfficersInFleet.size();
		}

		return ageAverage;
	}

	/*
	 * Returns a map mapping the highest ranking officer on each ship (as keys), to his ship (as values).
	 */
	public static Map<Officer, Spaceship> getHighestRankingOfficerPerShip(Collection<Spaceship> fleet) {

		Map<Officer, Spaceship> officerSpaceshipMap = new HashMap<>();

		for (Spaceship spaceship : fleet) {
			spaceship.getCrewMembers().stream()
					.filter(crewMember -> crewMember instanceof Officer).map(x->(Officer) x)
					.max(new MyOfficerComparator())
					.ifPresent(officer -> officerSpaceshipMap.put(officer, spaceship));
		}
		return officerSpaceshipMap;

	}

	/*
	 * Returns a List of entries representing ranks and their occurrences.
	 * Each entry represents a pair composed of an officer rank, and the number of its occurrences among starfleet personnel.
	 * The returned list is sorted ascendingly based on the number of occurrences.
	 */
	public static List<Map.Entry<OfficerRank, Integer>> getOfficerRanksSortedByPopularity(Collection<Spaceship> fleet) {

		Map<OfficerRank, Integer> rankIntegerMap = new TreeMap<>();
		List<Officer> allFleetOfficers = getAllOfficersList(fleet);


		for (Officer officer : allFleetOfficers) {
			OfficerRank rank = officer.rank;
			if (rankIntegerMap.containsKey(rank)) {
				int rankCount = rankIntegerMap.get(rank);
				rankIntegerMap.put(rank, ++rankCount);
			}
			else {
				rankIntegerMap.put(rank, 1);
			}
		}

		List<Map.Entry<OfficerRank,Integer>> entriesList = new ArrayList<>(rankIntegerMap.entrySet());
		entriesList.sort((o1, o2) -> {
			int comparison = Integer.compare(o1.getValue(), o2.getValue());
			if (comparison == 0) {
				return o1.getKey().compareTo(o2.getKey());
			}
			return comparison;
		});
		return entriesList;
	}

	private static List<Officer> getAllOfficersList(Collection<Spaceship> fleet) {
		List<Officer> allFleetOfficers = new ArrayList<>();
		for (Spaceship spaceship : fleet) {
			allFleetOfficers.addAll(spaceship.getCrewMembers().stream()
					.filter(crewMember -> crewMember instanceof Officer).map(x -> (Officer) x)
					.collect(Collectors.toList()));
		}
		return allFleetOfficers;
	}

}
