package pl.edu.agh.bayes;

import smile.Network;

import java.util.HashMap;
import java.util.Map;

public class NetworkWrapper {
    private static final String HYPOTHESIS_NODE_NAME = "Gdzie_mieszkasz_";
    private static final String TRUE_VALUE_MARKER = "Yes";
    private static final String FALSE_VALUE_MARKER = "No";
    private final Network network;

    public NetworkWrapper(String filename) {
        this.network = new Network();
        network.readFile(filename);
        network.updateBeliefs();
    }

    public void recalculate() {
        network.updateBeliefs();
    }

    public void setEvidence(Symptom symptom, boolean value) {
        if (value) {
            network.setEvidence(symptom.name(), TRUE_VALUE_MARKER);
        } else {
            network.setEvidence(symptom.name(), FALSE_VALUE_MARKER);
        }
    }

    public void clearEvidence(Symptom symptom) {
        network.clearEvidence(symptom.name());
    }

    public void clearAllEvidence() {
        network.clearAllEvidence();
    }

    public Map<Hypothesis, Double> getAllHypothesisProbabilities() {
        Map<Hypothesis, Double> probabilities = new HashMap<Hypothesis, Double>();
        for (Hypothesis hypothesis : Hypothesis.values()) {
            probabilities.put(hypothesis, getProbability(hypothesis));
        }
        return probabilities;
    }

    public double getProbability(Hypothesis hypothesis) {
        int valueIndex = getOutcomeId(HYPOTHESIS_NODE_NAME, hypothesis.name());
        if (valueIndex == -1) {
            throw new IllegalStateException("Could not find outcome id for " + hypothesis);
        }
        double[] probabilities = network.getNodeValue(HYPOTHESIS_NODE_NAME);
        return probabilities[valueIndex];
    }

    private int getOutcomeId(String nodeName, String hypothesisName) {
        int outcomeIndex;
        String[] outcomeIds = network.getOutcomeIds(nodeName);
        for (outcomeIndex = 0; outcomeIndex < outcomeIds.length; outcomeIndex++) {
            if (hypothesisName.equals(outcomeIds[outcomeIndex])) {
                return outcomeIndex;
            }
        }
        return -1;
    }

}
