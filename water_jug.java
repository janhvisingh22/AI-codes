public class water_jug
 {

    public static void main(String[] args) {
        solveWaterJugProblem(3, 5, 4);
    }

    public static void solveWaterJugProblem(int jug1Capacity, int jug2Capacity, int targetAmount) {
        int jug1 = 0;
        int jug2 = 0;

        while (jug1 != targetAmount && jug2 != targetAmount) {
            // Fill jug2 if it's empty
            if (jug2 == 0) {
                jug2 = jug2Capacity;
                System.out.println("Fill jug2 (" + jug2Capacity + "L)");
            }

            // Pour water from jug2 to jug1
            int transfer = Math.min(jug2, jug1Capacity - jug1);
            jug2 -= transfer;
            jug1 += transfer;
            System.out.println("Pour " + transfer + "L from jug2 to jug1");

            // Check if jug1 is now equal to the target amount
            if (jug1 == targetAmount) {
                System.out.println("Target amount " + targetAmount + "L reached!");
                break;
            }

            // Empty jug1 if it's full
            if (jug1 == jug1Capacity) {
                jug1 = 0;
                System.out.println("Empty jug1");
            }
        }
    }
}
