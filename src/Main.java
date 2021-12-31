import java.util.*;
import java.util.Random;

class project3 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("enter the size of the compAlien population");
        int size = input.nextInt();
        System.out.println("Simulating compAlien species:");
        String[][] population = new String[size][128];
        population = generateGeneticCode(population);
        String[] genders = findGender(population);
        int[] healts = calculateHealth(population);
        for (int i = 0; i < population.length; i++) {
            System.out.println("ID:" + (i + 1) + "," + genders[i] + "," + healts[i]);

        }

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("(1)Mate two compAliens");
            System.out.println("(2)Randomly mate a set of compAliens");
            System.out.println("(3)show Statistics");
            int in = input.nextInt();
            switch (in) {
                case 1:
                    System.out.println("enter id of first compAlien");
                    int first = input.nextInt();
                    System.out.println("Enter id of second compAlien");
                    int second = input.nextInt();
                    twoCompaliens(population, first, second);
                    break;
                case 2:
                    System.out.println("enter number of compAlien pairs to reproduce");
                    int pairs = input.nextInt();
                    multipleCompaliens(population, pairs);
                    break;
                case 3:
                    Statistics(population);
                    break;
            }
        }
    }

    public static String[][] generateGeneticCode(String[][] al) {
        String[][] pop = new String[al.length][128];
        for (int i = 0; i < pop.length; i++) {

            for (int j = 0; j < pop[i].length; j++) {
                int rand = new Random().nextInt(3);
                if (rand == 0) {
                    pop[i][j] = "x";
                } else if (rand == 1) {
                    pop[i][j] = "y";
                } else {
                    pop[i][j] = "z";
                }

            }
        }

        return pop;

    }

    public static int[] calculateHealth(String[][] al) {
        int array[] = new int[al.length];
        for (int i = 0; i < al.length; i++) {
            String a = "";
            for (int j = 0; j < al[i].length - 3; j++) {
                a = "";
                for (int k = j; k < j + 3; k++) {
                    a += al[i][k];
                    if (a.equals("yxz")) {
                        array[i]++;
                    }
                }

            }

        }
        return array;
    }

    public static String[] findGender(String[][] al) {
        String[] genders = new String[al.length];
        for (int i = 0; i < al.length; i++) {
            if (al[i][127] == "y") {
                genders[i] = "Male";
            } else {
                genders[i] = "Female";
            }
        }
        return genders;
    }

    public static void twoCompaliens(String[][] al, int a, int b) {
        String[] gender = findGender(al);
        String gender1 = gender[a];
        String gender2 = gender[b];
        if (gender1 == "Male") {
            gender1 = "(M)";
        } else if (gender1 == "Female") {
            gender1 = "(F)";
        }
        if (gender2 == "Male") {
            gender2 = "(M)";
        } else if (gender2 == "Female") {
            gender2 = "(F)";
        }
        if (reproductionResult(al, a, b) < 50 || reproductionResult(al, a, b) > 100) {
            System.out.println("compAlien " + a + gender1 + " and " + b + gender2 + " mate Offspring chance: " + "%" + reproductionResult(al, a, b) + "no offspring");
        } else {
            System.out.println("compAlien " + a + gender1 + " and " + b + gender2 + " mate Offspring chance: " + "%" + reproductionResult(al, a, b) + "1 offspring");
        }
    }

    public static double reproductionResult(String[][] al, int a, int b) {
        String[] gender = findGender(al);
        int[] health = calculateHealth(al);
        if (gender[a] != gender[b]) {
            return health[a] + health[b] * 10;

        } else {
            return 0;
        }
    }

    public static void multipleCompaliens(String[][] al, int count) {
        String[] gender = findGender(al);

        for (int i = 0; i < count; i++) {
            int first = new Random().nextInt(al.length);
            int second = new Random().nextInt(al.length);
            String gender1 = gender[first];
            String gender2 = gender[second];
            if (gender1 == "Male") {
                gender1 = "(M)";
            } else if (gender1 == "Female") {
                gender1 = "(F)";
            }
            if (gender2 == "Male") {
                gender2 = "(M)";
            } else if (gender2 == "Female") {
                gender2 = "(F)";
            }
            if (reproductionResult(al, first, second) < 50 || reproductionResult(al, first, second) > 100) {
                System.out.println("CompAlien " + first + gender1 + " and " + second + gender2 + " Mate Offspring chance: " + "%" + reproductionResult(al, first, second) + " no offspring");
            } else {
                System.out.println("CompAlien " + first + gender1 + " and " + second + gender2 + " Mate Offspring chance: " + "%" + reproductionResult(al, first, second) + " yes offspring");
            }
        }

    }

    public static void Statistics(String[][] al) {
        Scanner input = new Scanner(System.in);
        String[] gender = findGender(al);
        int[] array = calculateHealth(al);

        int count_male = 0;
        int count_female = 0;
        for (int i = 0; i < gender.length; i++) {
            if (gender[i] == "Female") {
                count_female++;
            } else if (gender[i] == "Male") {
                count_male++;
            }
        }
        int sum = count_male + count_female;
        System.out.println("Female population %" + (float) (count_female * 100 / sum));
        System.out.println("Male population %" + (float) (count_male * 100 / sum));

        System.out.println("Enter an health thresold between ... and ...");
        int th = input.nextInt();
        int count = 0;
        for (int i = 0; i < gender.length; i++) {
            if (array[i] >= th) {
                count++;
            }

        }
        System.out.println("%" + (float) (count * 100 / sum) + " have an health of " + th + " or higher");
    }

}
