import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Vector> vectors;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int v = scanner.nextInt();

        vectors = new ArrayList<>();

        scan(v);

        int[] edges = getEdges(v, scanner);

        Vector vector = getVector(1);

        setRoot(vector, edges);

        setColor(vector, null, null);


        System.out.println(chooseMax());

        print();
    }

    private static void scan(int v) {
        for (int i = 1; i <= v; i++) {
            Vector vector = new Vector(i);
            vectors.add(vector);
        }
    }

    private static int chooseMax() {
        int max = 1;
        for (Vector vector1 : vectors) {
            if (vector1.getColor() > max) {
                max = vector1.getColor();
            }
        }
        return max;
    }

    private static void print() {
        for (Vector vector : vectors) {
            System.out.println(vector.getKey() + " = " + vector.getColor());
        }
    }

    private static void setColor(Vector vector,
                                 Vector fatherVector, Vector grandFatherVector) {
        int gFV;
        if (grandFatherVector == null) {
            gFV = 0;
        } else {
            gFV = grandFatherVector.getColor();
        }

        int fV;
        if (fatherVector == null) {
            fV = 0;
        } else {
            fV = fatherVector.getColor();
        }

        vector.setColor(chooseMin(gFV,fV,fatherVector));

        for (Vector childrenVector : vector.childrenKeys) {
            setColor(childrenVector, vector, fatherVector);
        }
    }

    private static int chooseMin(int gFV,int fV, Vector fatherVector) {
        int min = 1;
        boolean again = true;
        while (again) {
            int tempMin = min;
            if (min == gFV) {
                min++;
            } else if (min == fV) {
                min++;
            } else if (fatherVector != null) {
                for (Vector chFather : fatherVector.childrenKeys) {
                    if (min == chFather.getColor()) {
                        min++;
                    }
                }
            }
            if (tempMin == min) {
                again = false;
            }
        }
        return min;
    }

    private static int[] getEdges(int v, Scanner scanner) {
        int[] edges = new int[2 * (v - 1)];
        for (int i = 0; i < edges.length; i++) {
            edges[i] = scanner.nextInt();
        }
        return edges;
    }

    private static void setRoot(Vector vector, int[] edges) {
        if (vector == null) {
            return;
        }

        int tempI;
        for (int i = 0; i < edges.length; i++) {
            if (edges[i] == vector.getKey()) {
                if (i % 2 == 0) {
                    tempI = i + 1;
                } else {
                    tempI = i - 1;
                }
                edges[i] = 0;
                int ch = edges[tempI];
                edges[tempI] = 0;
                vector.childrenKeys.add(getVector(ch));
            }
        }

        for (Vector ch : vector.childrenKeys) {
            setRoot(ch, edges);
        }
    }

    private static Vector getVector(int key) {
        for (Vector vector : vectors) {
            if (vector.getKey() == key) {
                return vector;
            }
        }
        return null;
    }
}

