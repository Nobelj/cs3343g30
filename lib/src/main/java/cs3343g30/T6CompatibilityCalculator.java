package cs3343g30;

import edu.duke.FileResource;

/**
 * The class for calculating the compatibility between two person in task 6
 */
public class T6CompatibilityCalculator {

    private final String ownName;
    private final boolean isMale;
    private final int ownYOB;
    private final String targetName;
    private final boolean targetIsMale;
    private final boolean younger;
    private final boolean isT6X2;
    private final int targetBirthMonth;
    private final int targetBirthDay;
    private String prediction = "";
    private int compatibilityScore = 0;
    private String genderPronoun = "";

    /**
     * The constructor for using algorithm T6X2
     *
     * @param ownName
     * @param isMale
     * @param ownYOB
     * @param targetName
     * @param targetIsMale
     * @param younger
     * @param isT6X2
     * @param targetBirthMonth
     * @param targetBirthDay
     */
    public T6CompatibilityCalculator(String ownName, boolean isMale, int ownYOB, String targetName, boolean targetIsMale, boolean younger, boolean isT6X2, int targetBirthMonth, int targetBirthDay) {

        this.ownName = ownName;
        this.isMale = isMale;
        this.ownYOB = ownYOB;
        this.targetName = targetName;
        this.targetIsMale = targetIsMale;
        this.younger = younger;
        this.isT6X2 = isT6X2;
        this.targetBirthMonth = targetBirthMonth;
        this.targetBirthDay = targetBirthDay;

        initiate();
    }

    /**
     * The constructor for using algorithm T6X1
     *
     * @param ownName
     * @param isMale
     * @param ownYOB
     * @param targetName
     * @param targetIsMale
     * @param younger
     * @param isT6X2
     */
    public T6CompatibilityCalculator(String ownName, boolean isMale, int ownYOB, String targetName, boolean targetIsMale, boolean younger, boolean isT6X2) {

        this.ownName = ownName;
        this.isMale = isMale;
        this.ownYOB = ownYOB;
        this.targetName = targetName;
        this.targetIsMale = targetIsMale;
        this.younger = younger;
        this.isT6X2 = isT6X2;
        this.targetBirthMonth = 0;
        this.targetBirthDay = 0;

        initiate();
    }

    /**
     * @param input The key to search for
     * @return the corresponding text
     */
    public static String getText(String input) {
        try {
            FileResource fileResource = new FileResource("t6data.txt");
            Iterable<String> lines = fileResource.lines();
            for (String line : lines) {
                // Skip comments or empty string
                if (line.startsWith("#") || line.equals("")) continue;

                String[] arrOfString = line.split(":");
                if (arrOfString[0].equals(input)) {
                    return arrOfString[1];
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "error";
    }

    /**
     * @param input the string to search for
     * @return the score offset
     */
    public static int getScore(String input) {
        try {
            FileResource fileResource = new FileResource("t6data.txt");
            Iterable<String> lines = fileResource.lines();
            for (String line : lines) {
                // Skip comments or empty string
                if (line.startsWith("#") || line.equals("")) continue;

                String[] arrOfString = line.split(":");
                if (arrOfString[0].equals(input)) {
                    return Integer.parseInt(arrOfString[2]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Start the calculation after all information is obtained
     */
    public void initiate() {
        if (targetIsMale) genderPronoun = "him";
        else genderPronoun = "her";

        makePrediction();
    }

    /**
     * Make prediction based on which algorithm is selected
     */
    public void makePrediction() {
        if (!isT6X2) {
            calculateT6X1();
        } else {
            calculateT6X2();
        }
    }

    /**
     * Calculate the score for algorithm 2
     */
    public void calculateT6X2() {

        compatibilityScore = 50; // Base score

        int frequencyOffset = nameFrequencyOffset();
        compatibilityScore += frequencyOffset;
        compatibilityScore += getScore(getChineseZodiac() + "+" + getConstellation());

        if (younger) compatibilityScore += 15;
        else compatibilityScore += 5;

        compatibilityScore = normalize(compatibilityScore);

        prediction = "Hi " + ownName + ", you are " + compatibilityScore + "% compatible with " + targetName + ".\n";

        if (frequencyOffset > 0) {
            prediction += "A " + targetName + " is very unique, you are lucky to find one.\n";
        } else {
            prediction += "Although " + targetName + " is a pretty common name, I'm sure it will still work.\n";
        }

        if (younger) {
            prediction += "As the older person, it means you are more mature than " + genderPronoun + ", but it also means you have to be more compassionate towards " +
                    genderPronoun + ".\n";
        } else {
            prediction += "As the younger person, you need to learn to understand and consider " + genderPronoun + " perspective, but it also means " + genderPronoun + " will take care of you.";
        }

        prediction += "You and " + targetName + " are a " + getChineseZodiac() + " and " + getConstellation() + " match, with " +
                getChineseZodiac() + " being " + getChineseZodiacTraits() + ", and " + getConstellation() + " being " + getConstellationTraits() +
                ", you two should " + getRelationshipTips() + ".";
    }

    /**
     * @param compatibilityScore
     * @return the compatibility score, but it cannot be less than 10 and more than 90
     */
    public int normalize(int compatibilityScore) {
        if (compatibilityScore < 10) {
            return 10;
        } else if (compatibilityScore > 90) {
            return 90;
        }
        return compatibilityScore;
    }

    /**
     * @return a offset based on whether the person's name or the target's name occurred more
     */
    public int nameFrequencyOffset() {
        int selfOccurrence = 0;
        int targetOccurrence = 0;
        String ownGender;
        if (isMale) ownGender = "M";
        else ownGender = "F";
        String targetGender;
        if (targetIsMale) targetGender = "M";
        else targetGender = "F";
        if (younger) {
            for (int year = ownYOB; year < ownYOB + 10; year++) {
                selfOccurrence += AnalyzeNames.getOccurrence(year, ownName, ownGender);
                targetOccurrence += AnalyzeNames.getOccurrence(year, targetName, targetGender);
            }
        } else {
            for (int year = ownYOB - 10; year < ownYOB; year++) {
                selfOccurrence += AnalyzeNames.getOccurrence(year, ownName, ownGender);
                targetOccurrence += AnalyzeNames.getOccurrence(year, targetName, targetGender);
            }
        }
        if (selfOccurrence >= targetOccurrence) return 15;
        else return 5;
    }

    /**
     * @return the relationship tip
     */
    public String getRelationshipTips() {
        return getText(getChineseZodiac() + "+" + getConstellation());
    }

    /**
     * @return the trait of that particular constellation
     */
    public String getConstellationTraits() {
        return getText(getConstellation());
    }

    /**
     * @return the trait of that particular chinese zodiac
     */
    public String getChineseZodiacTraits() {
        return getText(getChineseZodiac());
    }

    /**
     * @return the constellation based on the target birth month and day
     */
    public String getConstellation() {
        return constellation(targetBirthMonth, targetBirthDay);
    }

    /**
     * @return the chinese zodiac based on the year of birth
     */
    public String getChineseZodiac() {
        return chineseZodiac(ownYOB);
    }

    /**
     * Calculate the result based on algorithm 1
     */
    public void calculateT6X1() {
        if (ownName.length() == targetName.length()) {
            compatibilityScore = 100;
            prediction = "Wow, you and " + targetName + " share the same length of name, that is a sign that you two share the same shortcomings, you two would make a great couple.";
        } else {
            compatibilityScore = 0;
            prediction = "Unfortunately, you and " + targetName + " does not share the same length of name, therefore it would be hard for you two to work together, you can try the other algorithm to see if it gives a better result.";
        }
    }

    /**
     * @return the compatibility from the class
     */
    public int getCompatibilityScore() {
        return compatibilityScore;
    }

    /**
     * @return the prediction from the class
     */
    public String getPrediction() {
        return prediction;
    }

    /**
     * @param year
     * @return the chinese zodiac
     */
    public String chineseZodiac(int year) {
        return switch (year % 12) {
            case 0 -> "Monkey";
            case 1 -> "Rooster";
            case 2 -> "Dog";
            case 3 -> "Pig";
            case 4 -> "Rat";
            case 5 -> "Ox";
            case 6 -> "Tiger";
            case 7 -> "Rabbit";
            case 8 -> "Dragon";
            case 9 -> "Snake";
            case 10 -> "Horse";
            case 11 -> "Sheep";
            default -> "";
        };
    }

    /**
     * @param month
     * @param day
     * @return the zodiac
     */
    public static String constellation(int month, int day) {
        String constellation = "";
        if (month == 1 && day >= 20 || month == 2 && day <= 18) {
            constellation = "Aquarius";
        }
        if (month == 2 && day >= 19 || month == 3 && day <= 20) {
            constellation = "Pisces";
        }
        if (month == 3 && day >= 21 || month == 4 && day <= 19) {
            constellation = "Aries";
        }
        if (month == 4 && day >= 20 || month == 5 && day <= 20) {
            constellation = "Taurus";
        }
        if (month == 5 && day >= 21 || month == 6 && day <= 21) {
            constellation = "Gemini";
        }
        if (month == 6 && day >= 22 || month == 7 && day <= 22) {
            constellation = "Cancer";
        }
        if (month == 7 && day >= 23 || month == 8 && day <= 22) {
            constellation = "Leo";
        }
        if (month == 8 && day >= 23 || month == 9 && day <= 22) {
            constellation = "Virgo";
        }
        if (month == 9 && day >= 23 || month == 10 && day <= 23) {
            constellation = "Libra";
        }
        if (month == 10 && day >= 24 || month == 11 && day <= 22) {
            constellation = "Scorpio";
        }
        if (month == 11 && day >= 23 || month == 12 && day <= 21) {
            constellation = "Sagittarius";
        }
        if (month == 12 && day >= 22 || month == 1 && day <= 19) {
            constellation = "Capricorn";
        }
        return constellation;
    }

}
