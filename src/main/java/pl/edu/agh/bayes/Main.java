package pl.edu.agh.bayes;

public class Main {

    public static final String DATA_FILE_NAME = "miasta.xdsl";

    public static void main(String[] args) {
        NetworkWrapper network = new NetworkWrapper(DATA_FILE_NAME);
        System.out.println("Probability that you live in Poznan: " + network.getProbability(Hypothesis.Poznan));
        network.setEvidence(Symptom.Mieszkasz_w_budynku_sprzed_1918_roku, true);
        network.recalculate();
        System.out.println("Probability that you live in Poznan if you live in building constructed before 1918: " + network.getProbability(Hypothesis.Poznan));
        network.clearAllEvidence();
        network.recalculate();
        System.out.println("Probability that you live in Poznan: " + network.getProbability(Hypothesis.Poznan));
    }

}
