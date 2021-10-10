package il.ac.tau.cs.sw1.ex7;

import java.util.*;

public class FractionalKnapSack implements Greedy<FractionalKnapSack.Item> {

    final Item emptyItem = new Item(-1.0, -1.0);
    int capacity;
    List<Item> lst;

    FractionalKnapSack(int c, List<Item> lst1) {
        capacity = c;
        Collections.sort(lst1);
        lst = lst1;
        checkSack();
    }


    public static class Item implements Comparable<Item> {
        double weight, value, ratio;

        Item(double w, double v) {
            weight = w;
            value = v;
            ratio = value / weight;

        }

        @Override
        public String toString() {
            return "{" + "weight=" + weight + ", value=" + value + '}';
        }

        @Override
        public int compareTo(Item o) {

            return Double.compare(o.ratio, this.ratio);
        }

        private void setWeight(double newWeight) {
            this.weight = newWeight;
        }

    }

    private void checkSack() {
        if (this.lst.isEmpty() || this.capacity <= 0) {
            List<Item> errorList = new ArrayList<Item>();
            Item errorItem = new Item(-1.0, -1.0);
            errorList.add(errorItem);
            this.lst = errorList;
            this.capacity = -1;
        }
    }

    private boolean isEmptySack() {
        return this.lst.get(0).value == -1;
    }

    private double calcTotalWeight(List<Item> items) {
        if (isEmptySack()) {
            return 0;
        }
        double totalWeight = 0;
        for (Item item : items) {
            totalWeight += item.weight;
        }
        return totalWeight;
    }

    @Override
    public Iterator<Item> selection() {

        List<Item> lst = this.lst;

        return lst.iterator();
    }

    @Override
    public boolean feasibility(List<Item> candidates_lst, Item element) {
        if (isEmptySack()) {
            return false;
        }
        return (calcTotalWeight(candidates_lst) < this.capacity);
    }

    @Override
    public void assign(List<Item> candidates_lst, Item element) {

        double remainingCapacity = this.capacity - calcTotalWeight(candidates_lst);
        double proportion = remainingCapacity / element.weight;

        // proportion cannot be non-positive because of feasibility check

        if (proportion < 1) {
            element.setWeight(element.weight * proportion);
        }
        candidates_lst.add(element);

    }

    @Override
    public boolean solution(List<Item> candidates_lst) {
        if (isEmptySack()) {
            candidates_lst.clear();
            this.capacity = 0;
        }

        double currentTotalWeight = calcTotalWeight(candidates_lst);

        if (currentTotalWeight < this.capacity
                && candidates_lst.size() == this.lst.size()) {
            return true;
        }
        return calcTotalWeight(candidates_lst) == this.capacity;
    }

}
