package cs3343g30;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class for task 5 which picks the name and generates the reason for picking the name 
 */
public class T5NamePicker {
	private String iName;
	private String iGender;
	private int iYOB;
	private String iGenderMate;
	private String iPreference;
	private boolean T5X2;

	private String genderString;
	private String oName;
	private String reason;

	private ArrayList<String> nameList = new ArrayList<>();
	private ArrayList<Integer> yearList = new ArrayList<>();
	private ArrayList<Integer> occurrList;
	
	private int start,end;
	private int iRank;

	/**
	 * Constructor for the T5NamePicker class
	 * @param iName The user's name
	 * @param iGender The user's gender
	 * @param iYOB The user's year of birth
	 * @param iGenderMate The user's preferred soulmate gender
	 * @param iPreference The user's preferred relative age
	 * @param T5X2 The user's choice of algorithm
	 */
	public T5NamePicker(String iName, String iGender, int iYOB, String iGenderMate, String iPreference,boolean T5X2) {
		this.iName = iName;
		this.iGender = iGender;
		this.iYOB = iYOB;
		this.iGenderMate = iGenderMate;
		this.iPreference = iPreference;
		this.T5X2 = T5X2;
		if (iGenderMate == "M") genderString = "male"; else genderString = "female";
		
		if (AnalyzeNames.getRank(iYOB, iName, iGender) == -1) {
			oName = "Error";
			reason = "Your name does not appear in our database,\nplease try another name.";
		}

		else if (!T5X2) {
			oName = AnalyzeNames.getName(iYOB,1,iGenderMate);
			reason = String.format("%s is the most popular %s baby name in %d!\n"
					+ "You will have the highest chance of having a\n"
					+ "soulmate with the name %s", oName,genderString,iYOB,oName);
		}

		else {
			iRank = AnalyzeNames.getRank(iYOB, iName, iGender);

			if (iPreference == "younger") {
				start = iYOB;
				end = iYOB+9;
			}
			else {
				start = iYOB-9;
				end = iYOB;
			}
			for (int i = start;i<end;i++) {
				if (i<1880||i>2019) break;
				String name = AnalyzeNames.getName(i, iRank, iGenderMate);
				if (!nameList.contains(name))
					{nameList.add(name); yearList.add(i);}
			}
			occurrList = new ArrayList<>(nameList.size());
			for (int i =0; i<nameList.size();i++) {
				int total = 0;
				for (int y = start;y<end;y++) {
					if (y<1880||y>2019) break;
					total += AnalyzeNames.getOccurrence(y, nameList.get(i), iGenderMate);
				}
				occurrList.add(total);
			}
			
			oName = nameList.get(occurrList.indexOf(Collections.max(occurrList)));
			int oYear = yearList.get(occurrList.indexOf(Collections.max(occurrList)));
			
			reason = String.format("The rank of your name, %s, is %d in %d,\n"
					+ "and according to our calculations,\n"
					+ "%s has matched your rank\n"
					+ "in the year %d,\n"
					+ "and also occurs the most between\n"
					+ "%d and %d, which means\n"
					+ "they will most likely become your soulmate in the future!", 
					iName,iRank,iYOB,oName,oYear,start,end);
		}
	}

	/**
	 * Gets the picked name
	 * @return The picked name
	 */
	public String getOName() {
		return oName;
	}

	/**
	 * Gets the reason
	 * @return The reason for the picked name
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * Generates a bar chart depending on the algorithm
	 * @return The data to display the bar chart
	 */
	public XYChart.Series<String, Number> generateChart() {
		if (AnalyzeNames.getRank(iYOB, iName, iGender) == -1) return null;
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
		if (!T5X2) {
			series.setName("Most popular names in "+String.valueOf(iYOB));
			for (int i=1;i<=5;i++) {
				String name = AnalyzeNames.getName(iYOB, i, iGenderMate);
				series.getData().add(new XYChart.Data<String, Number>(name, AnalyzeNames.getOccurrence(iYOB, name, iGenderMate)));
			}
		}
		else {
			series.setName("Occurrences between "+start +" to " + end);
			while (nameList.size() > 0) {
				int ind = occurrList.indexOf(Collections.max(occurrList));
				series.getData().add(new XYChart.Data<String, Number>(nameList.remove(ind),occurrList.remove(ind)));
			}
		}
		return series;
	}

}
