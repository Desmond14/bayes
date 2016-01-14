package pl.edu.agh.bayes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class MainFrame extends JFrame {

	public static final String DATA_FILE_NAME = "miasta.xdsl";

	JCheckBox czytaszCheckBox;
	JCheckBox rowerCheckBox;
	JCheckBox autoCheckBox;
	JCheckBox zbiorowoCheckBox;
	JCheckBox pieszoCheckBox;
	JCheckBox miastoCheckBox;
	JCheckBox goryCheckBox;
	JCheckBox kosciolCheckBox;
	JCheckBox niemieckiCheckBox;
	JCheckBox budynekCheckBox;
	JCheckBox poludnieCheckBox;
	JCheckBox zachodCheckBox;
	JCheckBox skoczniaCheckBox;
	JCheckBox zooCheckBox;
	
	JLabel warszawa;
	JLabel krakow;
	JLabel zakopane;
	JLabel rzeszow;
	JLabel wroclaw;
	JLabel poznan;
	JLabel inne;

	public MainFrame(){
		
		final NetworkWrapper network = new NetworkWrapper(DATA_FILE_NAME);
		
		setTitle("Gdzie mieszkasz?");
		setLayout(new BorderLayout());
		
		JPanel pytania = new JPanel();
		Border borderPytania = BorderFactory.createLineBorder(Color.BLUE, 5);
		pytania.setBorder(borderPytania);
		
		pytania.setLayout(new GridLayout(14,1));
		add(pytania,BorderLayout.CENTER);
		
		JPanel odpowiedzi = new JPanel();
		Border borderOdpowiedzi = BorderFactory.createLineBorder(Color.RED, 5);
		odpowiedzi.setBorder(borderOdpowiedzi);
		odpowiedzi.setLayout(new GridLayout(7,2));
		add(odpowiedzi,BorderLayout.EAST);
		
		ActionListener listener = new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(czytaszCheckBox.isSelected())
					network.setEvidence(Symptom.Czytasz_wiecej_niz_12_ksiazek_rocznie, true);
				else
					network.setEvidence(Symptom.Czytasz_wiecej_niz_12_ksiazek_rocznie, false);

				if(rowerCheckBox.isSelected())
					network.setEvidence(Symptom.Jezdzisz_do_pracy_rowerem, true);
				else
					network.setEvidence(Symptom.Jezdzisz_do_pracy_rowerem, false);

				if(autoCheckBox.isSelected())
					network.setEvidence(Symptom.Jezdzisz_do_pracy_autem, true);
				else
					network.setEvidence(Symptom.Jezdzisz_do_pracy_autem, false);
				
				if(zbiorowoCheckBox.isSelected())
					network.setEvidence(Symptom.Komunikacja_zbiorowa, true);
				else
					network.setEvidence(Symptom.Komunikacja_zbiorowa, false);

				if(pieszoCheckBox.isSelected())
					network.setEvidence(Symptom.Chodzisz_do_pracy_pieszo, true);
				else
					network.setEvidence(Symptom.Chodzisz_do_pracy_pieszo, false);

				if(miastoCheckBox.isSelected())
					network.setEvidence(Symptom.Mieszkasz_w_miescie, true);
				else
					network.setEvidence(Symptom.Mieszkasz_w_miescie, false);


				if(goryCheckBox.isSelected())
					network.setEvidence(Symptom.Jezdzisz_w_gory_conajmniej_5_razy_w_roku, true);
				else
					network.setEvidence(Symptom.Jezdzisz_w_gory_conajmniej_5_razy_w_roku, false);
				
				if(kosciolCheckBox.isSelected())
					network.setEvidence(Symptom.Regularnie_chodzisz_do_kosciola, true);
				else
					network.setEvidence(Symptom.Regularnie_chodzisz_do_kosciola, false);

				if(niemieckiCheckBox.isSelected())
					network.setEvidence(Symptom.Jako_drugi_jezyk_obcy_znasz_niemiecki, true);
				else
					network.setEvidence(Symptom.Jako_drugi_jezyk_obcy_znasz_niemiecki, false);

				if(budynekCheckBox.isSelected())
					network.setEvidence(Symptom.Mieszkasz_w_budynku_sprzed_1918_roku, true);
				else
					network.setEvidence(Symptom.Mieszkasz_w_budynku_sprzed_1918_roku, false);

				if(poludnieCheckBox.isSelected())
					network.setEvidence(Symptom.Mieszkasz_na_poludniu_Polski, true);
				else
					network.setEvidence(Symptom.Mieszkasz_na_poludniu_Polski, false);

				if(zachodCheckBox.isSelected())
					network.setEvidence(Symptom.Mieszkasz_na_zachodzie_Polski, true);
				else
					network.setEvidence(Symptom.Mieszkasz_na_zachodzie_Polski, false);

				if(skoczniaCheckBox.isSelected())
					network.setEvidence(Symptom.W_Twojej_miejscowosci_jest_skocznia_narciarska, true);
				else
					network.setEvidence(Symptom.W_Twojej_miejscowosci_jest_skocznia_narciarska, false);

				if(zooCheckBox.isSelected())
					network.setEvidence(Symptom.W_Twojej_miejscowosci_jest_zoo, true);
				else
					network.setEvidence(Symptom.W_Twojej_miejscowosci_jest_zoo, false);

				//JCheckBox zooCheckBox;

				network.recalculate();
				
				warszawa.setText(String.format("%.3f%%",network.getProbability(Hypothesis.Warszawa)*100));
				krakow.setText(String.format("%.3f%%",network.getProbability(Hypothesis.Krakow)*100));
				zakopane.setText(String.format("%.3f%%",network.getProbability(Hypothesis.Zakopane)*100));
				rzeszow.setText(String.format("%.3f%%",network.getProbability(Hypothesis.Rzeszow)*100));
				wroclaw.setText(String.format("%.3f%%",network.getProbability(Hypothesis.Wroclaw)*100));
				poznan.setText(String.format("%.3f%%",network.getProbability(Hypothesis.Poznan)*100));
				inne.setText(String.format("%.3f%%",network.getProbability(Hypothesis.Inne)*100));

			}
		};
		
		//Czytasz_wiecej_niz_12_ksiazek_rocznie
		czytaszCheckBox = new JCheckBox("Czytasz wiêcej ni¿ 12 ksi¹¿ek rocznie");
		czytaszCheckBox.addActionListener(listener);
		pytania.add(czytaszCheckBox);		
		
		//Jezdzi_do_pracy_rowerem
		rowerCheckBox = new JCheckBox("JeŸdzisz do pracy rowerem");
		rowerCheckBox.addActionListener(listener);
		pytania.add(rowerCheckBox);		
		
		//Jezdzi_do_pracy_autem
		autoCheckBox = new JCheckBox("JeŸdzisz do pracy autem");
		autoCheckBox.addActionListener(listener);
		pytania.add(autoCheckBox);		
		
		//Komunikacja_zbiorowa
		zbiorowoCheckBox = new JCheckBox("Doje¿dzasz do pracy komunikacj¹ zbiorow¹");
		zbiorowoCheckBox.addActionListener(listener);
		pytania.add(zbiorowoCheckBox);		

		//Chodzi_do_pracy_pieszo
		pieszoCheckBox = new JCheckBox("Chodzisz do pracy pieszo");
		pieszoCheckBox.addActionListener(listener);
		pytania.add(pieszoCheckBox);		

		//Mieszkasz_w_miescie
		miastoCheckBox = new JCheckBox("Mieszkasz w miescie");
		miastoCheckBox.addActionListener(listener);
		pytania.add(miastoCheckBox);		

		//Jezdzisz_w_gory_conajmniej_5_razy_w_roku
		goryCheckBox = new JCheckBox("Jezdzisz w gory conajmniej 5 razy w roku");
		goryCheckBox.addActionListener(listener);
		pytania.add(goryCheckBox);		

		//Regularnie_chodzi_do_kosciola
		kosciolCheckBox = new JCheckBox("Regularnie chodzisz do kosciola");
		kosciolCheckBox.addActionListener(listener);
		pytania.add(kosciolCheckBox);		

		//Jako_drugi_jezyk_obcy_znasz_niemiecki
		niemieckiCheckBox = new JCheckBox("Jako drugi jezyk obcy znasz niemiecki");
		niemieckiCheckBox.addActionListener(listener);
		pytania.add(niemieckiCheckBox);		

		//Mieszkasz_w_budynku_sprzed_1918_roku
		budynekCheckBox = new JCheckBox("Mieszkasz w budynku sprzed 1918 roku");
		budynekCheckBox.addActionListener(listener);
		pytania.add(budynekCheckBox);		

		//Mieszkasz_na_poludniu_Polski
		poludnieCheckBox = new JCheckBox("Mieszkasz na poludniu Polski");
		poludnieCheckBox.addActionListener(listener);
		pytania.add(poludnieCheckBox);		

		//Mieszkasz_na_zachodzie_Polski
		zachodCheckBox = new JCheckBox("Mieszkasz na zachodzie Polski");
		zachodCheckBox.addActionListener(listener);
		pytania.add(zachodCheckBox);		

		//W_Twojej_miejscowosci_jest_skocznia_narciarska
		skoczniaCheckBox = new JCheckBox("W Twojej miejscowosci jest skocznia narciarska");
		skoczniaCheckBox.addActionListener(listener);
		pytania.add(skoczniaCheckBox);		
 
		//W_Twojej_miejscowosci_jest_zoo
		zooCheckBox = new JCheckBox("W Twojej miejscowosci jest zoo");
		zooCheckBox.addActionListener(listener);
		pytania.add(zooCheckBox);
		
		odpowiedzi.add(new JLabel("Warszawa"));
		warszawa = new JLabel("0%");
		warszawa.setHorizontalAlignment(JLabel.RIGHT);
		odpowiedzi.add(warszawa);
		
		odpowiedzi.add(new JLabel("Krakow"));
		krakow = new JLabel("0%");
		krakow.setHorizontalAlignment(JLabel.RIGHT);
		odpowiedzi.add(krakow);
		
		odpowiedzi.add(new JLabel("Zakopane"));
		zakopane = new JLabel("0%");
		zakopane.setHorizontalAlignment(JLabel.RIGHT);
		odpowiedzi.add(zakopane);
		
		odpowiedzi.add(new JLabel("Rzeszów"));
		rzeszow = new JLabel("0%");
		rzeszow.setHorizontalAlignment(JLabel.RIGHT);
		odpowiedzi.add(rzeszow);
		
		odpowiedzi.add(new JLabel("Wroc³aw"));
		wroclaw = new JLabel("0%");
		wroclaw.setHorizontalAlignment(JLabel.RIGHT);
		odpowiedzi.add(wroclaw);
		
		odpowiedzi.add(new JLabel("Poznañ"));
		poznan = new JLabel("0%");
		poznan.setHorizontalAlignment(JLabel.RIGHT);
		odpowiedzi.add(poznan);
		
		odpowiedzi.add(new JLabel("Inne"));
		inne = new JLabel("0%");
		inne.setHorizontalAlignment(JLabel.RIGHT);
		odpowiedzi.add(inne);

		pack();
	}
}
