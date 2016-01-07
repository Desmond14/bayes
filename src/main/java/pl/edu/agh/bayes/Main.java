package pl.edu.agh.bayes;

import smile.Network;

public class Main {

    public static final String DATA_FILE_NAME = "miasta.xdsl";

    public static void main(String[] args) {
        Network net = new Network();
        net.readFile(DATA_FILE_NAME);
        net.updateBeliefs();
        net.setEvidence("Mieszkasz_w_budynku_sprzed_1918_roku", "Yes");
        net.updateBeliefs();
        int outcomeIndex = getOutComeId(net, "Poznan", "Yes");
        if (outcomeIndex != -1) {
            System.out.println("P-stwo, ze mieszkasz w Poznaniu, jesli mieszkasz w budynku sprzed 1918 roku: " + getProbabilityValue(net, "Poznan", outcomeIndex));
        };
    }

    private static int getOutComeId(Network net, String nodeName, String outcomeName) {
        int outcomeIndex;
        String[] outcomeIds = net.getOutcomeIds(nodeName);
        for (outcomeIndex = 0; outcomeIndex < outcomeIds.length; outcomeIndex++)
            if (outcomeName.equals(outcomeIds[outcomeIndex]))
                return outcomeIndex;
        return -1;
    }

    public static double getProbabilityValue(Network net, String nodeName, int outcomeIndex) {
        double[] aValues = net.getNodeValue(nodeName);
        return aValues[outcomeIndex];
    }

}
