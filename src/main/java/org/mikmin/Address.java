package org.mikmin;

public class Address {

    private int streetNo;
    private String street;
    private String city;
    private Province province;
    private String postalCode;



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
