import java.util.*;

interface Formula {
    String getName();
    String getSymbolicFormula();
    String getDescription();
    double compute(Map<String, Double> variables);
    String[] requiredVariables();
}

class NewtonsSecondLaw implements Formula {
    @Override
    public String getName() { return "Newton's Second Law"; }

    @Override
    public String getSymbolicFormula() { return "F = m * a"; }

    @Override
    public String getDescription() { return "Computes Force from mass and acceleration."; }

    @Override
    public double compute(Map<String, Double> vars) {
        return vars.get("m") * vars.get("a");
    }

    @Override
    public String[] requiredVariables() {
        return new String[] {"m", "a"};
    }
}

class MechanicsModule {
    private final List<Formula> formulas = new ArrayList<>();

    public MechanicsModule() {
        formulas.add(new NewtonsSecondLaw());
    }

    public void run(Scanner scanner) {
        System.out.println("Mechanics Module:");
        for (int i = 0; i < formulas.size(); i++) {
            System.out.println((i + 1) + ". " + formulas.get(i).getName());
        }

        System.out.print("Select formula: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > formulas.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Formula selected = formulas.get(choice - 1);
        Map<String, Double> inputs = new HashMap<>();

        for (String var : selected.requiredVariables()) {
            System.out.print("Enter value for " + var + ": ");
            inputs.put(var, scanner.nextDouble());
        }

        double result = selected.compute(inputs);
        System.out.println(selected.getSymbolicFormula() + " => Result: " + result);
    }
}

public class EngineeringCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MechanicsModule mech = new MechanicsModule();

        while (true) {
            System.out.println("\n--- Complex Engineering Calculator ---");
            System.out.println("1. Mechanics");
            System.out.println("2. Exit");
            System.out.print("Choose option: ");
            int opt = scanner.nextInt();

            if (opt == 1) {
                mech.run(scanner);
            } else {
                break;
            }
        }
        scanner.close();
    }
}