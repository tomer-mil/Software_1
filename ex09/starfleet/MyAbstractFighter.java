package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class MyAbstractFighter extends MyAbstractSpaceship {

    protected List<Weapon> weaponsList;
    protected int firePower;
    protected final int REGULAR_FIGHTER_ANNUAL_COST = 2500;


    public MyAbstractFighter(String type, String name, int commissionYear, float maximalSpeed,
                             Set<? extends CrewMember> crewMembers, List<Weapon> weaponsList) {
        super(type, name, commissionYear, maximalSpeed, crewMembers);
        this.weaponsList = weaponsList;
        this.firePower = super.getFirePower() + calcWeaponsTotalFirePower();
    }

    public List<Weapon> getWeapon() {
        return weaponsList;
    }

    private int calcWeaponsTotalFirePower() {
        return weaponsList.stream().mapToInt(Weapon::getFirePower).sum();
    }

    public int getWeaponsAnnualMaintenanceCost() {
        int sum = 0;
        for (Weapon weapon : weaponsList) {
            sum += weapon.getAnnualMaintenanceCost();
        }
        return sum;
    }

    @Override
    public int getFirePower() {
        return this.firePower;
    }

    @Override
    public String toString() {
        return super.toString()
                + "\n\tWeaponArray=" + getWeapon().toString();
    }
}
