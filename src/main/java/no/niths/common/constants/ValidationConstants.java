package no.niths.common.constants;


/**
 * Constants used for validation 
 *
 */
public class ValidationConstants {

    public static final String

 // Regular word
    W    =
        "\\wæøå,",

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

    // Regular text (name)
    REGULAR =
        "([\\wæøå][\\wæøå\\s,]*[\\wæøå]){1,50}",

    COURSE =
        "(" +
            '[' + W + ']' +
            '[' + W + "]*" +
            '[' + EW + "]{1,2}" +
        "){1,50}";
}