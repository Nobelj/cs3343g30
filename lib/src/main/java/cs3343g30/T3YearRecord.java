package cs3343g30;

import javafx.beans.property.SimpleStringProperty;

/**
 * Class for record of a name in a certain year
 */
public class T3YearRecord {
    /**
     * The year
     */
    public final SimpleStringProperty year;
    /**
     * The rank
     */
    public final SimpleStringProperty rank;
    /**
     * The occurrences
     */
    public final SimpleStringProperty occurrences;
    /**
     * The percentage
     */
    public final SimpleStringProperty percentage;

    /**
     * @param year        The year of the name's data
     * @param rank        The ranking of the name in that year
     * @param occurrences The occurrences of the name in that year
     * @param percentage  The percentage of the name in that year
     */
    public T3YearRecord(int year, int rank, int occurrences, float percentage) {
        this.year = new SimpleStringProperty(Integer.toString(year));
        this.rank = new SimpleStringProperty(Integer.toString(rank));
        this.occurrences = new SimpleStringProperty(Integer.toString(occurrences));
        this.percentage = new SimpleStringProperty(String.format("%.3f", percentage));
    }

    /**
     * @return year
     */
    public String getYear() {
        return year.get();
    }

    /**
     * @param year The new year
     */
    public void setYear(String year) {
        this.year.set(year);
    }


    /**
     * @return rank
     */
    public String getRank() {
        return rank.get();
    }

    /**
     * @param rank The new rank
     */
    public void setRank(String rank) {
        this.rank.set(rank);
    }

    /**
     * @return occurrences
     */
    public String getOccurrences() {
        return occurrences.get();
    }

    /**
     * @param occurrences The new occurrences
     */
    public void setOccurrences(String occurrences) {
        this.occurrences.set(occurrences);
    }

    /**
     * @return percentage
     */
    public String getPercentage() {
        return percentage.get();
    }

    /**
     * @param percentage The new percentage
     */
    public void setPercentage(String percentage) {
        this.percentage.set(percentage);
    }
}
