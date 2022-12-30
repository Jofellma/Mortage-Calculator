package mortage.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MortageTest {

    Mortage mortage = new Mortage("John Doe", 10000, 3, 5);


    @Test
    void paymentCalcTest() {

        float totLoan = mortage.getTotLoan();
        float interest = mortage.getInterest();
        int years = mortage.getYears();

        mortage.calcMonthlyPay();

        Assertions.assertEquals(179.69, mortage.getMonthlyPay());
    }

    @Test
    void roundingTest(){

        double roundedDec = mortage.round2Dec(2.532);

        Assertions.assertEquals(2.53, roundedDec);
    }

    @Test
    void toPowerTest() {

        double base = 4;
        double squared = mortage.toPower(base, 2);
        double cubed = mortage.toPower(base, 3);

        Assertions.assertEquals(16, squared);
        Assertions.assertEquals(64, cubed);
    }
}