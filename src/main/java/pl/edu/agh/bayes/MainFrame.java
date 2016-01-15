package pl.edu.agh.bayes;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;


public class MainFrame extends JFrame {

	private static final String DATA_FILE_NAME = "miasta.xdsl";
	private final NetworkWrapper network;
	private final Map<Symptom, JCheckBox> checkboxes;
	private final Map<Hypothesis, JLabel> probabilityLabels;

	public MainFrame(){
		network = new NetworkWrapper(DATA_FILE_NAME);
		setTitle("Gdzie mieszkasz?");
		setLayout(new BorderLayout());
		JPanel pytania = new JPanel();
		Border borderPytania = BorderFactory.createLineBorder(Color.BLUE, 5);
		pytania.setBorder(borderPytania);
		
		pytania.setLayout(new GridLayout(14, 1));
		add(pytania,BorderLayout.CENTER);

		JPanel odpowiedzi = new JPanel();
		Border borderOdpowiedzi = BorderFactory.createLineBorder(Color.RED, 5);
		odpowiedzi.setBorder(borderOdpowiedzi);
		odpowiedzi.setLayout(new GridLayout(7, 2));
		add(odpowiedzi,BorderLayout.EAST);

		checkboxes = initChechboxes();
		for (JCheckBox checkBox : checkboxes.values()) {
			pytania.add(checkBox);
		}

		probabilityLabels = initLabels();
		for (Hypothesis hypothesis : probabilityLabels.keySet()) {
			odpowiedzi.add(new JLabel(hypothesis.name()));
			odpowiedzi.add(probabilityLabels.get(hypothesis));
		}

		network.setListener(new NetworkRecalculationListenerImpl(probabilityLabels, network));
		network.recalculate();

		pack();
	}

	private Map<Symptom, JCheckBox> initChechboxes() {
		Map<Symptom, JCheckBox> symptomCheckboxes = new HashMap<Symptom, JCheckBox>(Symptom.values().length);
		for (Symptom symptom : Symptom.values()) {
			JCheckBox symptomCheckbox = new JCheckBox(symptom.name().replaceAll("_", " "));
			symptomCheckbox.addItemListener(new SymptomCheckBoxListener(network, symptom));
			symptomCheckboxes.put(symptom, symptomCheckbox);
		}
		return symptomCheckboxes;
	}

	private Map<Hypothesis, JLabel> initLabels() {
		Map<Hypothesis, JLabel> hypothesisLabels = new HashMap<Hypothesis, JLabel>(Hypothesis.values().length);
		for (Hypothesis hypothesis : Hypothesis.values()) {
			JLabel label = new JLabel("0%");
			label.setHorizontalAlignment(JLabel.RIGHT);
			hypothesisLabels.put(hypothesis, label);
		}
		return hypothesisLabels;
	}
}
