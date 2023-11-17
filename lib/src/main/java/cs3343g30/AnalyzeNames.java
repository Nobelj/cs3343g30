package cs3343g30;

import org.apache.commons.csv.*;
import edu.duke.*;

import java.text.DecimalFormat;

/**
 * The class for analyzing names
 */
public class AnalyzeNames {

    /**
     * @param year The year
     * @return The parser for that year
     */
    public static CSVParser getFileParser(int year) {
        FileResource fr = new FileResource(String.format("dataset/yob%s.csv", year));
        return fr.getCSVParser(false);
    }


    /**
     * @param year The year
     * @return The summary for that year
     */
    public static String getSummary(int year) {
        String oReport = "";
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int totalNames = 0;
        int uniqueBoys = 0;
        int uniqueGirls = 0;

        oReport = String.format("Summary (Year of %d):\n", year);
        for (CSVRecord rec : getFileParser(year)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            totalNames += 1;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                uniqueBoys++;
            } else {
                totalGirls += numBorn;
                uniqueGirls++;
            }
        }

        oReport += String.format("Total Births = %,d\n", totalBirths);
        oReport += String.format("***Baby Girls = %,d\n", totalGirls);
        oReport += String.format("***Baby Boys = %,d\n", totalBoys);

        oReport += String.format("Total Number of Unique Names = %,d\n", totalNames);
        oReport += String.format("***Unique Names (baby girls) = %,d\n", uniqueGirls);
        oReport += String.format("***Unique Names (baby boys) = %,d\n", uniqueBoys);

        return oReport;
    }


    /**
     * @param year   The year
     * @param name   The name
     * @param gender The gender
     * @return The rank of the name of that gender in that year
     */
    public static int getRank(int year, String name, String gender) {
        boolean found = false;
        int oRank = 0;
        int rank = 1;
        for (CSVRecord rec : getFileParser(year)) {
            // Increment rank if gender matches param
            if (rec.get(1).equals(gender)) {
                // Return rank if name matches param
                if (rec.get(0).equals(name)) {
                    found = true;
                    oRank = rank;
                    break;
                }
                rank++;
            }
        }
        if (found)
            return oRank;
        else
            return -1;
    }

    /**
     * @param year   The year
     * @param name   The name
     * @param gender The gender
     * @return The occurrence of the name of that gender in that year
     */
    public static int getOccurrence(int year, String name, String gender) {

        // Range check
        if (year < 1880 || year > 2019) return 0;

        boolean found = false;
        int occurrence = 0;
        for (CSVRecord rec : getFileParser(year)) {
            if (rec.get(1).equals(gender)) {
                if (rec.get(0).equals(name)) {
                    found = true;
                    occurrence = Integer.parseInt(rec.get(2));
                    break;
                }
            }
        }
        if (found)
            return occurrence;
        else
            return -1;
    }

    /**
     * @param year   The year
     * @param name   The name
     * @param gender The gender
     * @return The record of the name of that gender in that year
     */
    public static T3YearRecord getNameYearRecord(int year, String name, String gender) {
        boolean found = false;
        int rank = 1;
        int occurrence = 0;
        for (CSVRecord rec : getFileParser(year)) {
            if (rec.get(1).equals(gender)) {
                if (rec.get(0).equals(name)) {
                    found = true;
                    occurrence = Integer.parseInt(rec.get(2));
                    break;
                }
            }
            rank++;
        }
        float percentage = (float) occurrence / getTotalOccurrence(year) * 100;
        if (found)
            return new T3YearRecord(year, rank, occurrence, percentage);
        else
            // Return record with all negative one to signal non existence data
            return new T3YearRecord(-1, -1, -1, -1);
    }


    /**
     * @param year The year
     * @return Total number of occurrence in that year
     */
    public static int getTotalOccurrence(int year) {
        int recordCount = 0;
        for (CSVRecord rec : getFileParser(year)) {
            recordCount += Integer.parseInt(rec.get(2));
        }
        return recordCount;
    }

    /**
     * Retrieve the total occurrence of the babies in the given gender in the given year
     * @param year The year
     * @param gender The gender
     * @return Total number of occurrence in the given gender in the given year
     */
    public static int getTotalOccurrence(int year, String gender) {
    	int recordCount = 0;
    	for(CSVRecord rec : getFileParser(year)) {
    		if(rec.get(1).equals(gender))
    			recordCount+=Integer.parseInt(rec.get(2));
    	}
    	return recordCount;
    }


    /**
     * @param year   The year
     * @param rank   The rank
     * @param gender The gender
     * @return The name of that gender of that rank in that year
     */
    public static String getName(int year, int rank, String gender) {
        boolean found = false;
        String oName = "";
        int currentRank = 0;

        // For every name entry in the CSV file
        for (CSVRecord rec : getFileParser(year)) {
            // Get its rank if gender matches param
            if (rec.get(1).equals(gender)) {
                // Get the name whose rank matches param
                currentRank++;
                if (currentRank == rank) {
                    found = true;
                    oName = rec.get(0);
                    break;
                }
            }
        }
        if (found)
            return oName;
        else
            return "information on the name at the specified rank is not available";
    }

}