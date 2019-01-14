import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int veggiesCount = Integer.parseInt(sc.nextLine());

        HashSet<Integer> veggiesWeight = new HashSet();

        String[] rawVeggiesWeights = sc.nextLine().split(" ");
        for (int i = 0; i < veggiesCount; i++) {
            veggiesWeight.add(Integer.parseInt(rawVeggiesWeights[i]));
        }

        Set<Set<Integer>> veggiesWeightPS = powerSet(veggiesWeight);

        int totalWeightGreaterThan200 = 0;

        for (Set<Integer> veggiesWeightSS : veggiesWeightPS) {
            int subsetSum = 0;
            for (Integer vw : veggiesWeightSS) {
                subsetSum += vw;
            }

            if (subsetSum >= 200) {
                totalWeightGreaterThan200 += subsetSum;
            }
        }

        System.out.println(totalWeightGreaterThan200);
    }

    public static <T> Set<Set<T>> powerSet(Set<T> originalSet) {
        Set<Set<T>> sets = new HashSet<Set<T>>();
        if (originalSet.isEmpty()) {
            sets.add(new HashSet<T>());
            return sets;
        }
        List<T> list = new ArrayList<T>(originalSet);
        T head = list.get(0);
        Set<T> rest = new HashSet<T>(list.subList(1, list.size()));
        for (Set<T> set : powerSet(rest)) {
            Set<T> newSet = new HashSet<T>();
            newSet.add(head);
            newSet.addAll(set);
            sets.add(newSet);
            sets.add(set);
        }
        return sets;
    }
}
