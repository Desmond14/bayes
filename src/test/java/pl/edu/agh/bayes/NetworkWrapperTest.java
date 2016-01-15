package pl.edu.agh.bayes;


public class NetworkWrapperTest {
    public static void main(String[] args) {
        NetworkWrapper net = new NetworkWrapper("miasta.xdsl");
        net.recalculate();
        System.out.println("Probability: " + net.getProbability(Hypothesis.Poznan));
        net.setEvidence(Symptom.Jako_drugi_jezyk_obcy_znasz_niemiecki, true);
        net.setEvidence(Symptom.Mieszkasz_w_budynku_sprzed_1918_roku, true);
        net.recalculate();
        System.out.println("Probability: " + net.getProbability(Hypothesis.Poznan));
        net.recalculate();
        System.out.println("Probability: " + net.getProbability(Hypothesis.Poznan));
    }
}