package cs3343g30;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

/**
 * Class for Task 3 data generation
 */
public class T3DataGenerator {
    int startYear, endYear;
    String name;
    boolean isMale;
    // Additional gender string
    String stringGender, gender;

    int mostPopularYear;

    ObservableList<T3YearRecord> observableListData = FXCollections.observableArrayList();
    // A additional list without non-existence data
    ObservableList<T3YearRecord> filteredObservableListData = FXCollections.observableArrayList();

    /**
     * @param startYear The starting year
     * @param endYear   The ending year
     * @param name      The name
     * @param isMale    Whether we are searching for male name
     */
    public T3DataGenerator(int startYear, int endYear, String name, boolean isMale) {
        this.startYear = startYear;
        this.endYear = endYear;
        this.name = name;
        this.isMale = isMale;

        if (isMale) {
            stringGender = "M";
            gender = "male";
        } else {
            stringGender = "F";
            gender = "female";
        }

        for (int i = startYear; i <= endYear; i++) {
            T3YearRecord nameYearRecord = AnalyzeNames.getNameYearRecord(i, name, stringGender);
            this.observableListData.add(nameYearRecord);
            if (!nameYearRecord.getYear().equals("-1")) {
                this.filteredObservableListData.add(nameYearRecord);
            }
        }

        this.mostPopularYear = getMostPopularYear();
    }

    /**
     * To generate the summary for the name in the year range
     *
     * @return the summary string
     */
    public String generateSummary() {
        // Return if name is never used
        if (mostPopularYear == -1) return "This name was not recorded in the selected years";

        int occurrences = AnalyzeNames.getOccurrence(mostPopularYear, name, stringGender);
        int rank = AnalyzeNames.getRank(mostPopularYear, name, stringGender);
        float percentage = (float) occurrences / (float) AnalyzeNames.getTotalOccurrence(mostPopularYear) * 100;

        return "The year when the name " + name + " was most popular is " + mostPopularYear + " at rank " +
                rank + ". In that year, the number of occurrence is " + occurrences + ", which represents " +
                String.format("%.3f", percentage) + "% of total " + gender + " births in " + mostPopularYear + ".";
    }

    /**
     * To find the most popular year within range
     *
     * @return the year which the name is most popular in the time period, in integer
     */
    public int getMostPopularYear() {
        int mostPopularYear = -1;
        int currentMaxOccurrence = -1;

        for (T3YearRecord record : observableListData) {
            int occurrence = Integer.parseInt(record.getOccurrences());

            if (occurrence > currentMaxOccurrence) {
                mostPopularYear = Integer.parseInt(record.getYear());
                currentMaxOccurrence = occurrence;
            }
        }
        return mostPopularYear;
    }

    /**
     * To generate data for the data table
     *
     * @return observable list without non-existence data
     */
    public ObservableList<T3YearRecord> generateDataTableData() {
        return filteredObservableListData;
    }

    /**
     * To generate series for bar chart
     *
     * @return series without non-existence data
     */
    public XYChart.Series<String, Number> generateBarChartSeries() {
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        series.setName("Occurrence of " + name);
        for (T3YearRecord record : filteredObservableListData) {
            series.getData().add(new XYChart.Data<String, Number>(record.getYear(), Integer.parseInt(record.getOccurrences())));
        }
        return series;
    }


    /**
     * To generate series for line chart
     *
     * @return series without non-existence data
     */
    public XYChart.Series<Number, Number> generateLineChartSeries() {
        XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
        series.setName("Occurrence of " + name);
        for (T3YearRecord record : filteredObservableListData) {
            series.getData().add(new XYChart.Data<Number, Number>(Integer.parseInt(record.getYear()), Integer.parseInt(record.getOccurrences())));
        }
        return series;
    }
}
