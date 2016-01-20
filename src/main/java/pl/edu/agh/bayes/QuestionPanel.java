package pl.edu.agh.bayes;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class QuestionPanel extends JPanel {
	
	JRadioButton yesRadioButton;
	JRadioButton noRadioButton;
	JRadioButton clearRadioButton;
	
	public QuestionPanel(Symptom symptom,NetworkWrapper network){
		this.setLayout(new BorderLayout());
		this.add(new JLabel(symptom.name().replaceAll("_", " ")),BorderLayout.WEST);
		clearRadioButton = new JRadioButton("Nie wiem");
		clearRadioButton.setSelected(true);
		clearRadioButton.addItemListener(new ClearItemListener(symptom, network));
		noRadioButton = new JRadioButton("Nie");
		noRadioButton.addItemListener(new NoItemListener(symptom,network));
		yesRadioButton = new JRadioButton("Tak");
		yesRadioButton.addItemListener(new YesItemListener(symptom,network));
		ButtonGroup group = new ButtonGroup();
		group.add(clearRadioButton);
		group.add(noRadioButton);
		group.add(yesRadioButton);
		JPanel radioPanel = new JPanel();
		radioPanel.add(clearRadioButton);
		radioPanel.add(noRadioButton);
		radioPanel.add(yesRadioButton);
		this.add(radioPanel,BorderLayout.EAST);
	}

	private class ClearItemListener implements ItemListener{

		private final NetworkWrapper network;
		private final Symptom symptom;

		public ClearItemListener(Symptom symptom, NetworkWrapper network) {
			this.network = network;
			this.symptom = symptom;
		}
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			int state = e.getStateChange();
			if (state == ItemEvent.SELECTED) {
				this.network.clearEvidence(symptom);
				this.network.recalculate();
			}
		}
	}

	private class NoItemListener implements ItemListener{

		private final NetworkWrapper network;
		private final Symptom symptom;

		public NoItemListener(Symptom symptom, NetworkWrapper network) {
			this.network = network;
			this.symptom = symptom;
		}
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			int state = e.getStateChange();
			if (state == ItemEvent.SELECTED) {
				network.setEvidence(symptom, false);
				this.network.recalculate();
			}
		}
	}
	private class YesItemListener implements ItemListener{

		private final NetworkWrapper network;
		private final Symptom symptom;

		public YesItemListener(Symptom symptom, NetworkWrapper network) {
			this.network = network;
			this.symptom = symptom;
		}
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			int state = e.getStateChange();
			if (state == ItemEvent.SELECTED) {
				network.setEvidence(symptom, true);
				this.network.recalculate();
			}
		}
	}

}
