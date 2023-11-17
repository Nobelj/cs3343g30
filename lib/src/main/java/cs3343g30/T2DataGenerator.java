package cs3343g30;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class for Task 2 data generation
 */
public class T2DataGenerator {
	int startYear, endYear, k;
	char gender;
	String gen;
	
	ObservableList<T2Record> observableListData = FXCollections.observableArrayList();
	ArrayList<String> nameList = new ArrayList<>();
	ArrayList<Integer> freqList = new ArrayList<>();
	ArrayList<Integer> occurrList = new ArrayList<>();
	
	/**
	 * Class constructor for class for task 2 data generation. The constructed class can be used to display data. 
	 * @param startYear The start of the years of interest
	 * @param endYear The end of the years of interest
	 * @param k The value of k
	 * @param gender The gender of interest
	 */
	public T2DataGenerator(int startYear,int endYear,int k, char gender) {
		this.startYear = startYear;
		this.endYear = endYear;
		this.k = k;
		this.gender = gender;
		if (gender == 'M') gen = "male"; else gen = "female";
		
		int totalOccurr = 0;
		
		for (int i = startYear; i <= endYear; i++) {
            String name = AnalyzeNames.getName(i, k, String.valueOf(gender));
            int occurr = AnalyzeNames.getOccurrence(i, name, String.valueOf(gender));
            totalOccurr += occurr;
            if (nameList.contains(name)) {
            	freqList.set(nameList.indexOf(name), freqList.get(nameList.indexOf(name))+1);
            	occurrList.set(nameList.indexOf(name), occurrList.get(nameList.indexOf(name))+occurr);
            }
            else {
            	nameList.add(name);
            	freqList.add(1);
            	occurrList.add(occurr);
            }
        }
		
		while (nameList.size() >0) {
			int ind = occurrList.indexOf(Collections.max(occurrList));
			T2Record temp = new T2Record(nameList.remove(ind),String.valueOf(occurrList.get(ind)),
					String.valueOf(freqList.remove(ind)),String.format("%.2f", (double)occurrList.get(ind)/totalOccurr*100));
			occurrList.remove(ind);
			this.observableListData.add(temp);
		}
		
	}
	
	/** 
	 * Trivial observableListData getter
	 * @return observableListData which contains the data to be displayed
	 */
	public ObservableList<T2Record> getOLD() {
		return observableListData;
	}
	
	/**
	 * Builds the summary to be displayed
	 * @return The summary as a String
	 */
	public String getSummary() {
		String topName = observableListData.get(0).getName();
		String topFreq = observableListData.get(0).getFreq();
		String topOccurr = observableListData.get(0).getOccurr();
		String topPercent = observableListData.get(0).getPercent();
		String baby, gen;
		if (gender == 'M') {baby = "boys"; gen = "male";}
		else {baby = "girls"; gen = "female";}
		
		return topName + " has hold the "+ String.valueOf(k) +"-th rank most often for a total of "+String.valueOf(topFreq)+" times among names registered \n"
				+ "for baby "+baby+" born in the period from "+ String.valueOf(startYear) +" to "+String.valueOf(endYear)+". The total number of occurrences of \n"
				+ topName +" is "+topOccurr+", which represents "+topPercent+"% of total "+gen+" births at the "+String.valueOf(k)+"-th rank in the "
				+ "period from "+String.valueOf(startYear)+" to "+String.valueOf(endYear)+".";
	}
	
	public XYChart.Series<String, Number> generateBarChartSeries() {
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        series.setName(String.valueOf(k)+"-th popular "+gen+" names over the period from "+String.valueOf(startYear)+" to "+String.valueOf(endYear));
        for (T2Record record : observableListData) {
            series.getData().add(new XYChart.Data<String, Number>(record.getName(), Integer.parseInt(record.getOccurr())));
        }
        return series;
    }
	
}
