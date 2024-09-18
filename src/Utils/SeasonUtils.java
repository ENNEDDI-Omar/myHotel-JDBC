package Utils;

import Enums.SeasonType;

import java.time.LocalDate;
import java.time.Month;

public class SeasonUtils {

    public static SeasonType getSeasonForDate(LocalDate date) {
        Month month = date.getMonth();
        switch (month) {
            case DECEMBER:
            case JANUARY:
            case FEBRUARY:
                return SeasonType.low; // Hiver
            case MARCH:
            case APRIL:
            case MAY:
                return SeasonType.high; // Printemps
            case JUNE:
            case JULY:
            case AUGUST:
                return SeasonType.high; // Été
            case SEPTEMBER:
            case OCTOBER:
            case NOVEMBER:
                return SeasonType.low; // Automne
            default:
                throw new IllegalArgumentException("Mois invalide : " + month);
        }
    }
}
