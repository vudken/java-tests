package homeWorks;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Homework {

    @Test
    public void test() {

        carSelling("Toyota", 198000, 1000);

        flightCost(6, 713);

        birthdayDeployment("150991-10818");

        distanceMeasurer(pointExample1, pointExample2);

    }

    //1.
    private void carSelling(String brand, int mileage, int price) {
        System.out.println("Selling a car " + brand + " with " + mileage + "km mileage " + "for " + price + "$");
    }


    //2.
    private void flightCost(int consumption, int distance) {
        double litrePrice = 3.57;
        double consumptionCost = litrePrice * consumption;
        double finalCost = distance / 100 * consumptionCost;
        System.out.println("This flight cost " + finalCost + "$");
    }


    //3.
    private void birthdayDeployment(String personalCode) {
        String personalCodePattern = "\\d{6}-\\d{5}";
        Pattern pattern = Pattern.compile(personalCodePattern);
        Matcher patternMatcher = pattern.matcher(personalCode);
        boolean matches = patternMatcher.matches();

        if (!matches || personalCode.length() < 12) {
            System.out.println("You've added incorrect data.");
            System.exit(-1);
        }

        String[] parts = personalCode.split("-");
        String part1 = parts[0];
        String part2 = parts[1];

        String dayString = part1.substring(0, 2);
        String monthString = part1.substring(2, 4);
        String yearString = part1.substring(4, 6);

        String centuryString = part2.substring(0, 1);

        int day = Integer.parseInt(dayString);
        int monthNumber = Integer.parseInt(monthString);
        int year = Integer.parseInt(yearString);

        int centuryID = Integer.parseInt(centuryString);

        String month = null;
        int centuryInitializer = 0;

        switch (monthNumber) {
            case 1:
                month = "January";
                break;
            case 2:
                month = "February";
                break;
            case 3:
                month = "March";
                break;
            case 4:
                month = "April";
                break;
            case 5:
                month = "May";
                break;
            case 6:
                month = "June";
                break;
            case 7:
                month = "July";
                break;
            case 8:
                month = "August";
                break;
            case 9:
                month = "September";
                break;
            case 10:
                month = "October";
                break;
            case 11:
                month = "November";
                break;
            case 12:
                month = "December";
                break;
            default:
                System.out.println("You've added incorrect data.");
                System.exit(-1);
        }

        if (centuryID == 0) {
            centuryInitializer = 1800;
        } else if (centuryID == 1) {
            centuryInitializer = 1900;
        } else if (centuryID == 2) {
            centuryInitializer = 2000;
        } else {
            System.out.println("You've added incorrect data, either you're dead or maybe not born yet:).");
        }
        // Первая цифра второй части перс. кода определяет в каком веке был рождён человек:
        // 0 = 19-ый, 1 = 20-ый, 2 = 21-ый соответственно.
        int fullYear = centuryInitializer + year;

        int currentYear = 2020;



        if (fullYear > currentYear || day > 31 || day < 1) {
            System.out.println("You've added incorrect data.");
            System.exit(-1);
        }


        System.out.println("You are born on the " + day + "th of " + month + " in " + fullYear + ".");
    }

    //4.
    String[] pointExample1 = {"57.8136°N", "28.3496°E"};//Псков
    String[] pointExample2 = {"33.865143°S", "151.209900°E"};//Sydney

    private void distanceMeasurer(String[] coordinatesA, String[] coordinatesB ) {
        String toStringA = Arrays.toString(coordinatesA);
        String toStringB = Arrays.toString(coordinatesB);


        boolean checkRegExVarA = checkRegEx(toStringA);
        boolean checkRegExVarB = checkRegEx(toStringB);


        String a1 = pointExample1[0].substring(0,pointExample1[0].length() - 2);
        String a2 = pointExample1[1].substring(0,pointExample1[1].length() - 2);
        String b1 = pointExample2[0].substring(0,pointExample1[0].length() - 2);
        String b2 = pointExample2[1].substring(0,pointExample1[1].length() - 2);


        double x1Degrees = Double.parseDouble(a1);
        double y1 = Double.parseDouble(a2);
        double x2Degrees = Double.parseDouble(b1);
        double y2 = Double.parseDouble(b2);


        if (!checkRegExVarA || !checkRegExVarB) {
            System.out.println("You've added incorrect data.");
            System.exit(-1);
        }
        else {
            if(containsSW(coordinatesA) == "SW") {
                x1Degrees = -x1Degrees;
                y1 = -y1;
            }
            else if(containsSW(coordinatesA) == "S") {
                x1Degrees = -x1Degrees;
            }
            else if(containsSW(coordinatesA) == "W") {
                y1 = -y1;
            }


            if(containsSW(coordinatesB) == "SW") {
                x2Degrees = -x2Degrees;
                y2 = -y2;
            }
            else if(containsSW(coordinatesB) == "S") {
                x2Degrees = -x2Degrees;
            }
            else if(containsSW(coordinatesB) == "W") {
                y2 = -y2;
            }
        }


        double x1 = Math.toRadians(x1Degrees);
        double x2 = Math.toRadians(x2Degrees);


        double longitudeDelta = Math.toRadians(y2 - y1);
        double earthAngleRadians = Math.acos(Math.sin(x1) * Math.sin(x2) + Math.cos(x1) * Math.cos(x2) * Math.cos(longitudeDelta));
        double earthAngleDegrees = Math.toDegrees(earthAngleRadians);
        final int R = 6371; // Radius of the earth
        double distance = 2 * Math.PI * R * earthAngleDegrees / 360;

        System.out.println("Distance between point A and point B is " + Math.round(distance) + "km.");
    }


    private boolean checkRegEx(String coordinates) {
        String regEx = "^\\[?(\\d{1,3}\\.?\\d*\\u00B0?[nNsS]),\\s*(\\d{1,3}\\.?\\d*\\u00B0?[wWeE])]?$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher patternMatcher = pattern.matcher(coordinates);
        return patternMatcher.matches();
    }


    private String containsSW(String[] coordinates) {
        String s = "S";
        String w = "W";
        String sw = "SW";

        boolean containsS = Arrays.stream(coordinates)
                .anyMatch(coordinate -> coordinate.contains("s") || coordinate.contains("S"));
        boolean containsW = Arrays.stream(coordinates)
                .anyMatch(coordinate -> coordinate.contains("w") || coordinate.contains("W"));

        if (containsS && containsW) {
            return sw;
        }
        else if (containsS) {
            return s;
        }
        else if (containsW) {
            return w;
        }
        else {
            return null;
        }
    }
}
