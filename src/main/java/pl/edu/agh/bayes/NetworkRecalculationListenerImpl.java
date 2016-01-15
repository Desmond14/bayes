package pl.edu.agh.bayes;

import javax.swing.*;
import java.util.Map;

public class NetworkRecalculationListenerImpl implements NetworkRecalculationListener {
    private final Map<Hypothesis, JLabel> probabilityLabel;
    private final NetworkWrapper network;

    public NetworkRecalculationListenerImpl(Map<Hypothesis, JLabel> probabilityLabel, NetworkWrapper network) {
        this.probabilityLabel = probabilityLabel;
        this.network = network;
    }

    public void onNetworkRecalculation() {
        for (Hypothesis hypothesis : probabilityLabel.keySet())  {
            probabilityLabel.get(hypothesis).setText(String.format("%.3f%%", network.getProbability(hypothesis)*100));
        }
    }
}
