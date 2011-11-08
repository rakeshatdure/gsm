/*
 * Copyright 2005, 2008 PayPal, Inc. All Rights Reserved.
 *
 * DoDirectPayment NVP example; last modified 08MAY23.
 *
 * Process a credit card payment.
 * Add: commons-httpclient-3.0.1.jar
 *      paypal_base.jar
 *      xerces.jar
 */
package UTIL;

import com.paypal.sdk.core.nvp.NVPDecoder;
import com.paypal.sdk.core.nvp.NVPEncoder;
import com.paypal.sdk.profiles.APIProfile;
import com.paypal.sdk.profiles.ProfileFactory;
import com.paypal.sdk.services.NVPCallerServices;
import com.paypal.sdk.exceptions.PayPalException;
/**
 * PayPal Java SDK sample code
 */
public class PaypalUtils
{

            private static NVPCallerServices caller = null;
            public static NVPDecoder DoDirectPaymentCode(String paymentAction,String amount,String cardType,
								String acct,String expdate,String cvv2, String firstName,
								String lastName, String street, String city, String state,
								String zip, String countryCode)
            {
                    NVPEncoder encoder = new NVPEncoder();
                    NVPDecoder decoder = new NVPDecoder();

                    try
                    {

                    caller = new NVPCallerServices();
                    APIProfile profile = ProfileFactory.createSignatureAPIProfile();
                            /*
                             WARNING: Do not embed plaintext credentials in your application code.
                             Doing so is insecure and against best practices.
                             Your API credentials must be handled securely. Please consider
                             encrypting them for use in any production environment, and ensure
                             that only authorized individuals may view or modify them.
                             */
                    // Set up your API credentials, PayPal end point, API operation and version.
                    profile.setAPIUsername("sell_1306985675_biz_api1.gmail.com");
                    profile.setAPIPassword("1306985688");
                    profile.setSignature("AUIFzAQrCNwrxgS5D--vO4hNv3quACNcljTvzg0UD0Y2.VTs8T5MmfcY");
                    profile.setEnvironment("sandbox");
                    profile.setSubject("");
                    caller.setAPIProfile(profile);

                            encoder.add("METHOD","DoDirectPayment");

                    // Add request-specific fields to the request string.
                            encoder.add("PAYMENTACTION",paymentAction);
                            encoder.add("AMT",amount);
                            encoder.add("CREDITCARDTYPE",cardType);
                            encoder.add("ACCT",acct);
                            encoder.add("EXPDATE",expdate);
                            encoder.add("CVV2",cvv2);
                            encoder.add("FIRSTNAME",firstName);
                            encoder.add("LASTNAME",lastName);
                            encoder.add("STREET",street);
                            encoder.add("CITY",city);
                            encoder.add("STATE",state);
                            encoder.add("ZIP",zip);
                            encoder.add("COUNTRYCODE",countryCode);
                            encoder.add("CURRENCYCODE", "USD");

                    // Execute the API operation and obtain the response.
                            String NVPRequest = encoder.encode();
                            String NVPResponse =(String) caller.call(NVPRequest);
                            decoder.decode(NVPResponse);

                    } catch(Exception ex)
                    {
                            ex.printStackTrace();
                    }
                    return decoder;
            }

	    public static boolean isError(NVPDecoder dec) {
               
                String strAck = dec.get("ACK");
                System.out.println("ACK:"+strAck);
                return strAck != null && !(strAck.equals("Success") || strAck.equals("SuccessWithWarning"));
                
	    }

           

}
