package nopoint;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class CalculateTaxExemptCodeAlignmentTest {

    private CalculateTaxExemptCodeAlignment calculateTaxExemptCodeAlignment;

    @Before
    public void init() {
        calculateTaxExemptCodeAlignment = new CalculateTaxExemptCodeAlignment();
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculate_mustBeExceptionOnNullString() {
        calculateTaxExemptCodeAlignment.calculate(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculate_mustBeExceptionOnEmptyString() {
        calculateTaxExemptCodeAlignment.calculate("", "null");
    }

    @Test
    public void calculate_mustBe3WithAllZeros() {
        String expect = "3";

        String actual = calculateTaxExemptCodeAlignment.calculate("00000124", "");
        assertThat(actual, equalTo(expect));
    }

    @Test
    public void calculate_mustBe0With2OnesInFirstCheckedPart() {
        String expect = "0";

        String actual = calculateTaxExemptCodeAlignment.calculate("11000124", "");
        assertThat(actual, equalTo(expect));
    }

    @Test
    public void calculate_mustBe0With2OnesInSecondCheckedPart() {
        String expect = "0";

        String actual = calculateTaxExemptCodeAlignment.calculate("00110124", "");
        assertThat(actual, equalTo(expect));
    }

    @Test
    public void calculate_mustBe1With1OfOddParamsNotEqualTo0() {
        String expect = "1";

        String actual = calculateTaxExemptCodeAlignment.calculate("10000124", "");
        assertThat(actual, equalTo(expect));
    }

    @Test
    public void calculate_mustBe0With1OfOddParamsNotEqualTo0And3ParamEqualTo1AndStateEqualToBC() {
        String expect = "0";

        String actual = calculateTaxExemptCodeAlignment.calculate("00100124", "BC");
        assertThat(actual, equalTo(expect));
    }

    @Test
    public void calculate_mustBe1With1OfOddParamsNotEqualTo0And3ParamEqualTo1AndStateNotEqualToBC() {
        String expect = "1";

        String actual = calculateTaxExemptCodeAlignment.calculate("01100124", "CB");
        assertThat(actual, equalTo(expect));
    }

    @Test
    public void calculate_mustBe2With1OfEvenParamsNotEqualTo0() {
        String expect = "2";

        String actual = calculateTaxExemptCodeAlignment.calculate("01010124", "");
        assertThat(actual, equalTo(expect));
    }

    @Test
    public void calculate_mustBe1With1OfEvenParamsNotEqualTo0And4ParamEqualTo1AndStateEqualToBC() {
        String expect = "1";

        String actual = calculateTaxExemptCodeAlignment.calculate("01010124", "BC");
        assertThat(actual, equalTo(expect));
    }

    @Test
    public void calculate_mustBe2With1OfEvenParamsNotEqualTo0And4ParamEqualTo1AndStateNotEqualToBC() {
        String expect = "2";

        String actual = calculateTaxExemptCodeAlignment.calculate("01010124", "CB");
        assertThat(actual, equalTo(expect));
    }

}