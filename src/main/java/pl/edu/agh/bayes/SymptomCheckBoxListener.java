package pl.edu.agh.bayes;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class SymptomCheckBoxListener implements ItemListener {
    private final NetworkWrapper network;
    private final Symptom symptom;

    public SymptomCheckBoxListener(NetworkWrapper network, Symptom symptom) {
        this.network = network;
        this.symptom = symptom;
    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            network.setEvidence(symptom, true);
            network.recalculate();
        }
    }
}
