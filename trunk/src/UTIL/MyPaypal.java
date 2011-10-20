/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package UTIL;
import com.paypal.sdk.core.nvp.NVPDecoder;
import com.paypal.sdk.core.nvp.NVPEncoder;
import com.paypal.sdk.exceptions.PayPalException;
import com.paypal.sdk.profiles.APIProfile;
import com.paypal.sdk.profiles.ProfileFactory;
import com.paypal.sdk.services.NVPCallerServices;
/**
 *
 * @author admin
 */
public class MyPaypal {
    private static final String TEST_ENV = "sandbox";
    private static final String DEFAULT_USER_NAME = "sell_1306985675_biz_api1.gmail.com";
    private static final String DEFAULT_PASSWORD = "1306985688";
    private static final String DEFAULT_SIGNATURE = "AUIFzAQrCNwrxgS5D--vO4hNv3quACNcljTvzg0UD0Y2.VTs8T5MmfcY";

    public static NVPCallerServices getDefaultCaller() throws PayPalException {
        APIProfile profile = ProfileFactory.createSignatureAPIProfile();

        profile.setAPIUsername(DEFAULT_USER_NAME);
        profile.setAPIPassword(DEFAULT_PASSWORD);

        profile.setSignature(DEFAULT_SIGNATURE);
        profile.setEnvironment(TEST_ENV);

        NVPCallerServices caller = new NVPCallerServices();
        caller.setAPIProfile(profile);

        return caller;
    }

    public static NVPDecoder doDirectPayment(
            String amount, String creditCardType, String creditCardNumber,
            String expDate, String cvvNumber, String firstName, String lastName,
            String address, String city, String state, String zip) throws PayPalException {

        NVPCallerServices caller = getDefaultCaller();

        NVPEncoder encoder = new NVPEncoder();

        encoder.add("METHOD", "DoDirectPayment");
        encoder.add("PAYMENTACTION", "Sale");
        encoder.add("AMT", amount);
        encoder.add("CREDITCARDTYPE", creditCardType);
        encoder.add("ACCT", creditCardNumber);
        encoder.add("EXPDATE", expDate);
        encoder.add("CVV2", cvvNumber);
        encoder.add("COUNTRYCODE", "US");
        encoder.add("CURRENCYCODE", "USD");

        encoder.add("FIRSTNAME", firstName);
        encoder.add("LASTNAME", lastName);

        encoder.add("STREET", address);
        encoder.add("CITY", city);
        encoder.add("STATE", state);
        encoder.add("ZIP", zip);

        String NVPString = encoder.encode();
        String ppresponse = (String) caller.call(NVPString);

        NVPDecoder resultValues = new NVPDecoder();
        resultValues.decode(ppresponse);

        return resultValues;
    }

    public static boolean isError(NVPDecoder dec) {
        String strAck = dec.get("ACK");
        return strAck != null && !(strAck.equals("Success") || strAck.equals("SuccessWithWarning"));
    }
}
