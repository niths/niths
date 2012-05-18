package no.niths.domain.misc;

import static org.junit.Assert.assertEquals;

import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.BeforeClass;
import org.junit.Test;

public class SocialLinkTest {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void testShouldCreateNewSocialLink() {
        final String address         = "https://twitter.com/",
                     socialCommunity = "twitter";

        SocialLink socialLink = new SocialLink(address, socialCommunity);

        assertEquals(address, socialLink.getAddress());
        assertEquals(socialCommunity, socialLink.getSocialCommunity());
    }

    @Test
    public void testValidationOfInvalidAddress() {
        final String invalidAddress =
                "https://twitter.com/' OR '1' = '1"; 

        SocialLink socialLink = new SocialLink(
                invalidAddress,
                "twinjection");

        assertEquals(
                validator.validate(
                        socialLink).iterator().next().getInvalidValue(),
                invalidAddress);
    }

    @Test
    public void testValidationOfInvalidSocialCommunity() {
        final String invalidSocialCommunity =
                "<script>alert();</script>";

        final SocialLink socialLink = new SocialLink(
                "https://example.org",
                invalidSocialCommunity);

        assertEquals(
                validator.validate(
                        socialLink).iterator().next().getInvalidValue(),
                invalidSocialCommunity);
    }
}
