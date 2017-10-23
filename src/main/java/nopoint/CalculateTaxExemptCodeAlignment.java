package nopoint;

import org.springframework.stereotype.Service;

@Service
public class CalculateTaxExemptCodeAlignment {

    public String calculate(final String taxExemptFlags, final String provinceState) {
        if (!checkStringForBeingValid(taxExemptFlags)) {
            throw new IllegalArgumentException();
        }

        int taxExemptGSTPST = -1;
        int param1 = Integer.parseInt(taxExemptFlags.substring(0, 1));
        int param2 = Integer.parseInt(taxExemptFlags.substring(1, 2));
        int param3 = Integer.parseInt(taxExemptFlags.substring(2, 3));
        int param4 = Integer.parseInt(taxExemptFlags.substring(3, 4));

        if (param1 == 0 && param2 == 0 && param3 == 0 && param4 == 0) {
            taxExemptGSTPST = 3;
        } else if ((param1 == 1 && param2 == 1) || (param3 == 1 && param4 == 1)) {
            taxExemptGSTPST = 0;
        } else if (param1 != 0 || param3 != 0) {
            taxExemptGSTPST = 1;
            if (param3 == 1 && "BC".equals(provinceState)) {
                taxExemptGSTPST = 0;
            }
        } else if (param2 != 0 || param4 != 0) {
            taxExemptGSTPST = 2;
            if (param4 == 1 && "BC".equals(provinceState)) {
                taxExemptGSTPST = 1;
            }
        }
        return Integer.toString(taxExemptGSTPST);
    }

    private boolean checkStringForBeingValid(String taxExemptFlags) {
        return taxExemptFlags != null && taxExemptFlags.length() > 3;
    }
}
