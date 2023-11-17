package cs3343g30;

import org.apache.commons.csv.CSVRecord;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The class for generating the content of the summary in task 1 and the data of the tables is task 1
 * @author Nobel
 */
public class T1DataGenerator {
	
	/**
	 * Retrieve the summary content
	 * @param year
	 * @return the content of the summary in String type
	 */
	public static String getSummary(int year) {
		String popMaleName = null;
		String popFemaleName = null;
		String resultStr = "";
		int popMaleOccurr = 0;
		int popFemaleOccurr = 0;
		double totalMaleOccurr=0;
		double totalFemaleOccurr=0;
		boolean popMaleFound = false;
		boolean popFemaleFound = false;
		for (CSVRecord rec : AnalyzeNames.getFileParser(year)) {
			if(rec.get(1).equals("F")) {
				if(popFemaleFound==false) {
					popFemaleFound = true;
					popFemaleName = rec.get(0);
					popFemaleOccurr = Integer.parseInt(rec.get(2));
				}
				totalFemaleOccurr+=Integer.parseInt(rec.get(2));
			}
			else {
				if(popMaleFound==false) {
					popMaleFound=true;
					popMaleName = rec.get(0);
					popMaleOccurr = Integer.parseInt(rec.get(2));
				}
				totalMaleOccurr+=Integer.parseInt(rec.get(2));
			}
		}
		
		
		resultStr += popMaleName + " is the most popular name with the number of occurrences of " + popMaleOccurr+", which "
				+ "represents " + String.format("%.2f", popMaleOccurr/totalMaleOccurr*100) + "% of total male births in "+year+".\n";
		
		resultStr += popFemaleName + " is the most popular name with the number of occurrences of " + popFemaleOccurr+", which "
				+ "represents " + String.format("%.2f", popFemaleOccurr/totalFemaleOccurr*100) + "% of total female births in "+year+".";
		
		return resultStr;
	}
	
	/**
	 * Retrieving the data for the tables
	 * @param numNames number of most popular names 
	 * @param year
	 * @param gender
	 * @return the data for the tables
	 */
	public static ObservableList<T1Record> getTableData(int numNames, int year, String gender){
		ObservableList<T1Record> resultList = FXCollections.observableArrayList();
		for(int i=1;i<=numNames;++i) {
			String name = AnalyzeNames.getName(year, i, gender);
			String occurr = String.valueOf(AnalyzeNames.getOccurrence(year, name, gender));
			String percent = String.format("%.2f%%", Double.parseDouble(occurr)/AnalyzeNames.getTotalOccurrence(year, gender)*100);
			resultList.add(new T1Record(name, occurr, percent));
		}
		
		
		return resultList;
	}
}
