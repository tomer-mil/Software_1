package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class MyAbstractSpaceship implements Spaceship, Comparable<Spaceship> {

    private final String type;
    private final String name;
    private final int commissionYear;
    private final float maximalSpeed;
    private int firePower;
    final private Set<? extends CrewMember> crewMembers;
    protected int annualMaintenanceCost;

    public MyAbstractSpaceship(String type, String name, int commissionYear, float maximalSpeed,
                               Set<? extends CrewMember> crewMembers) {
        this.type = type;
        this.name = name;
        this.commissionYear = commissionYear;
        this.maximalSpeed = maximalSpeed;
        this.firePower = 10;
        this.crewMembers = crewMembers;
        this.annualMaintenanceCost = -1;
    }

    @Override
    public int compareTo(Spaceship o1) {
        if (this.firePower == o1.getFirePower()) {
            if (this.commissionYear == o1.getCommissionYear()) {
                return this.name.compareTo(o1.getName());
            }
            return Integer.compare(this.commissionYear, o1.getCommissionYear());
        }
        return Integer.compare(this.firePower, o1.getFirePower());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getCommissionYear() {
        return commissionYear;
    }

    @Override
    public float getMaximalSpeed() {
        return maximalSpeed;
    }

    @Override
    public int getFirePower() {
        return firePower;
    }

    @Override
    public Set<? extends CrewMember> getCrewMembers() {
        return crewMembers;
    }

    @Override
    public abstract int getAnnualMaintenanceCost();

    @Override
    public String toString() {
        return  type
                + "\n\tName=" + getName()
                + "\n\tCommissionYear=" + getCommissionYear()
                + "\n\tMaximalSpeed=" + getMaximalSpeed()
                + "\n\tFirePower=" + getFirePower()
                + "\n\tCrewMembers=" + getCrewMembers().size()
                + "\n\tAnnualMaintenanceCost=" + getAnnualMaintenanceCost();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyAbstractSpaceship that = (MyAbstractSpaceship) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
