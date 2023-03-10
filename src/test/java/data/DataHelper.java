package data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {

    private static final Faker fakerEng = new Faker(Locale.ENGLISH);

    private static final Faker fakerRu = new Faker(new Locale("ru", "RU"));

    @Value
    public static class Card {
        String number;
        String month;
        String year;
        String holder;
        String cvc;
    }

    public static Card getValidApprovedCard() {
        return new Card(getApprovedNumber(), getMonth(0), getYear(0),
                getEngHolder(), getCVC());
    }

    public static Card getValidDeclinedCard() {
        return new Card(getDeclinedNumber(), getMonth(0), getYear(0),
                getEngHolder(), getCVC());
    }

    public static Card getEmptyCard() {
        return new Card(getEmptyNumber(), getEmptyMonth(), getEmptyYear(),
                getEmptyHolder(), getEmptyCVC());
    }

    public static Card getValidApprovedCardAndRandomInvalidOtherField() {
        return new Card(getApprovedNumber(), getThirteenMonth(), getYear(10),
                getRuHolder(), getZeroSymbolsCVC());
    }

    public static Card getValidApprovedCardAndEmptyOtherField() {
        return new Card(getApprovedNumber(), getEmptyMonth(), getEmptyYear(),
                getEmptyHolder(), getEmptyCVC());
    }

    public static Card getRandomCardNumberAndValidOtherField() {
        return new Card(getRandomNumber(), getMonth(0), getYear(0),
                getEngHolder(), getCVC());
    }

    public static String getApprovedNumber(){
        return "4444 4444 4444 4441";
    }
    public static String getDeclinedNumber(){
        return "4444 4444 4444 4442";
    }
    public static String getRandomNumber(){
        return fakerEng.numerify("#### #### #### ####");
    }
    public static String getFifteenRandomNumber(){
        return fakerEng.numerify("#### #### #### ###");
    }
    public static String getOneRandomNumber(){
        return fakerEng.numerify("#");
    }
    public static String getEmptyNumber(){
        return "";
    }
    public static String getMonth(int plusMonth) {
        return LocalDate.now().plusMonths(plusMonth).format(DateTimeFormatter.ofPattern("MM"));
    }
    public static String getThirteenMonth() {
        return "13";
    }
    public static String getTwelveMonth() {
        return "12";
    }
    public static String getElevenMonth() {
        return "11";
    }
    public static String getFirstMonth() {
        return "01";
    }
    public static String getSecondMonth() {
        return "02";
    }
    public static String getZeroMonth() {
        return "00";
    }
    public static String getOneSymbolMonth() {
        return "2";
    }

    public static String getEmptyMonth() {
        return "";
    }
    public static String getYear(int plusYear) {
        return LocalDate.now().plusYears(plusYear).format(DateTimeFormatter.ofPattern("yy"));
    }
    public static String getEmptyYear() {
        return "";
    }
    public static String getOneSymbolYear() {
        return "1";
    }
    public static String getEngHolder() {
        return fakerEng.name().name().toUpperCase();
    }

    public static String getChoiceSymbolHolder(String countSymbols) {
        return fakerEng.letterify(countSymbols).toUpperCase();
    }

    public static String getEmptyHolder() {
        return "";
    }

    public static String getSpacesHolder() {
        return "      ";
    }

    public static String getSpecialCharactersHolder() {
        return "#@$%^^&*!.,)(+=-'/|???";
    }

    public static String getNumerifyHolder(String countSymbols) {
        return fakerEng.numerify(countSymbols);
    }

    public static String getEngHolderWithLowerCase() {
        return fakerEng.name().fullName().toLowerCase();
    }

    public static String getRuHolder() {
        return fakerRu.name().name().toUpperCase();
    }
    public static String getCVC() {
        return fakerEng.numerify("###");
    }
    public static String getTwoSymbolsCVC() {
        return fakerEng.numerify("##");
    }
    public static String getOneSymbolCVC() {
        return fakerEng.numerify("#");
    }
    public static String getZeroSymbolsCVC() {
        return "000";
    }
    public static String getEmptyCVC() {
        return "";
    }

}
