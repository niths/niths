package no.niths.common.constants;


/**
 * Constants used for validation 
 *
 */
public class ValidationConstants {

    public static final String

 // Regular word
    W    =
        "\\wæøåÆØÅ,",

    // Space and words
    SW   =
        "(\\s)?" + '[' + W,

    EW   =
        "\\+",

    SEW   =
        SW + EW + "]",

    // Large text (description)
    LARGE   =
        "(" +
            '[' + W + ']' +
            '(' + SEW + "+)+" +
        "){1,250}",

    ACCESS_POINT_ADDRESS =
        "([0-9A-F]{2}:){5}([0-9A-F]{2})",

    // Regular text (name)
    REGULAR =
        "([\\wæøåÆØÅ][\\wæøåÆØÅ\\s,]*[\\wæøåÆØÅ]){1,50}",

    COURSE =
        "(" +
            '[' + W + ']' +
            '[' + W + "]*" +
            '[' + EW + "]{1,2}" +
        "){1,50}";
}