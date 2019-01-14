import java.lang.reflect.Array;
import java.util.*;

class SofaBiClique implements Comparable<SofaBiClique> {
    public HashSet<Style> styles = new HashSet<>();
    public HashSet<Color> colors = new HashSet<>();

    SofaBiClique () {
    }

    int size() {
        return Math.max(styles.size(), colors.size());
    }

    boolean isClique() {
        for (Style s : styles) {
            for (Color c : colors) {
                if (!s.colors.contains(c)) {
                    return false;
                }
                if (!c.styles.contains(s)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int compareTo(SofaBiClique s) {
        if (this.size() < s.size()) {
            return -1;
        } else
        if (this.size() > s.size()) {
            return 1;
        } else {
            return 0;
        }
    }
}

class Style {
    public HashSet<Color> colors;
    private int value;

    public Style(int value) {
        this.value = value;
        this.colors = new HashSet<>();
    }

    @Override
    public String toString() {
        return "s" + value;
    }
}

class Color {
    public HashSet<Style> styles;
    private int value;

    public Color(int value) {
        this.value = value;
        this.styles = new HashSet<>();
    }

    @Override
    public String toString() {
        return "c" + value;
    }
}

class Main {
    public static void evaluateOneTestCase(Scanner sc) {
        int numSofas = Integer.parseInt(sc.nextLine());
        HashMap<Integer, Style> styles = new HashMap<>();
        HashMap<Integer, Color> colors = new HashMap<>();

        // first parse the input
        for (int i = 0; i < numSofas; i++){
            String input = sc.nextLine();
            String delims = " ";
            String[] tokens = input.split(delims);
            int input_s = Integer.parseInt(tokens[0]);
            int input_c = Integer.parseInt(tokens[1]);
            Style s;
            Color c;
            if (styles.containsKey(input_s)){
                s = styles.get(input_s);
            } else {
                s = new Style(input_s);
                styles.put(input_s, s);
            }

            if (colors.containsKey(input_c)){
                c = colors.get(input_c);
            } else {
                c = new Color(input_c);
                colors.put(input_c, c);
            }

            s.colors.add(c);
            c.styles.add(s);
        }

        // now starts the actual algorithm

        // build the initial disjoint sofa bicliques
        ArrayList<SofaBiClique> disjointSofaBiCliques = new ArrayList<>();
        HashSet<Integer> ignoredColorKeys = new HashSet<>();
        HashSet<Integer> availableColorKeys = new HashSet<>(colors.keySet());
        for (Style s: styles.values()) {
            availableColorKeys.removeAll(ignoredColorKeys);
            ArrayList<Integer> colorKeys = new ArrayList<>(availableColorKeys);
            if (!colorKeys.isEmpty()) {
                SofaBiClique sbc = new SofaBiClique();
                sbc.styles.add(s);
                sbc.colors.add( colors.get(colorKeys.get(0)));
                ignoredColorKeys.add(colorKeys.get(0));
                disjointSofaBiCliques.add(sbc);
            }
        }

        while (true) {
            ArrayList<SofaBiClique> newDSBC = new ArrayList<>();
            int dSBCSize = disjointSofaBiCliques.size();

            for (int i = 0; i < dSBCSize; i++) {
                for (int j = i + 1; j < dSBCSize; j++) {
                    // check if disjoint biclique i and j can be merged
                    SofaBiClique ci = disjointSofaBiCliques.get(i);
                    SofaBiClique cj = disjointSofaBiCliques.get(j);

                    SofaBiClique mergedBiClique = new SofaBiClique();
                    mergedBiClique.colors.addAll(ci.colors);
                    mergedBiClique.colors.addAll(cj.colors);
                    mergedBiClique.styles.addAll(ci.styles);
                    mergedBiClique.styles.addAll(cj.styles);



                    if (mergedBiClique.isClique()) {
                        newDSBC.add(mergedBiClique);
                    } else {
                        newDSBC.add(cj);
                    }
                }
            }
            int oldDSBCMaxSize = Collections.max(disjointSofaBiCliques).size();

            if (newDSBC.isEmpty()) {
                System.out.println(oldDSBCMaxSize);
                break;
            }
            int newDSBCMaxSize = Collections.max(newDSBC).size();

            if (oldDSBCMaxSize < newDSBCMaxSize) {
                disjointSofaBiCliques = newDSBC;
            } else {
                System.out.println(oldDSBCMaxSize);
                break;
            }
        }

        // merge bicliques until exhaustion

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num_test_case = Integer.parseInt(sc.nextLine());
        for (int k = 0; k < num_test_case; k++) {
            evaluateOneTestCase(sc);
        }
    }
}

