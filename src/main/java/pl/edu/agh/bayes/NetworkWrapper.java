package pl.edu.agh.bayes;

import smile.Network;

import java.util.HashMap;
import java.util.Map;

public class NetworkWrapper {
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
            probabilities.put(hypothesis, getProbability(hypothesis, true));
        }
        return probabilities;
    }

    public double getProbability(Hypothesis hypothesis, boolean value) {
        int valueIndex = getOutcomeId(hypothesis.name(), value);
        if (valueIndex == -1) {
            throw new IllegalStateException("Could not find outcome id for " + hypothesis);
        }
        double[] probabilities = network.getNodeValue(hypothesis.name());
        return probabilities[valueIndex];
    }

    private int getOutcomeId(String nodeName, boolean value) {
        String outcomeName = value ? TRUE_VALUE_MARKER : FALSE_VALUE_MARKER;
        int outcomeIndex;
        String[] outcomeIds = network.getOutcomeIds(nodeName);
        for (outcomeIndex = 0; outcomeIndex < outcomeIds.length; outcomeIndex++) {
            if (outcomeName.equals(outcomeIds[outcomeIndex])) {
                return outcomeIndex;
            }
        }
        return -1;
    }

}
