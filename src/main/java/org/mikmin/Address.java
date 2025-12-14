package org.mikmin;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
@Setter
public class Address {
    private int streetNo;
    private String street;
    private String city;
    private Province province;
    private String postalCode;


    public Address(int streetNo, String street, String city, Province province, String postalCode) {
        if (isPostalCodeValid(postalCode)) {
            this.streetNo = streetNo;
            this.street = street;
            this.city = city;
            this.province = province;
            this.postalCode = postalCode;
        }

        this.streetNo = 0;
        this.street = null;
        this.city = null;
        this.province = null;
        this.postalCode = null;
    }

    /**
     * Checks if a given postal code is valid
     * @param postalCode input postal code
     * @return the validity of the postal code
     */
    public static boolean isPostalCodeValid(String postalCode) {
        if (postalCode != null && postalCode.length() == 6) {
            char char1 = postalCode.charAt(0);
            char char2 = postalCode.charAt(1);
            char char3 = postalCode.charAt(2);
            char char4 = postalCode.charAt(3);
            char char5 = postalCode.charAt(4);
            char char6 = postalCode.charAt(5);

            return (Character.isLetter(char1)
                    && Character.isLetter(char3)
                    && Character.isLetter(char5)
                    && Character.isDigit(char2)
                    && Character.isDigit(char4)
                    && Character.isDigit(char6));
        }
        return false;
    }



    public enum Province{
        AB,
        MB,
        QC,
        SK,
        BC,
        NB,
        NS,
        PE,
        NL,
        ON,
        NT,
        NU,
        YT
    }
}
