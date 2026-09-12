package com.baryonminds.revaliyo.utils;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePart;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GmailService {

	private static final String APPLICATION_NAME = "Revaliyo Automation";

	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

	private static final String TOKENS_DIRECTORY_PATH = "tokens";

	private static final List<String> SCOPES = Collections.singletonList(GmailScopes.GMAIL_READONLY);

	private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

	private static Credential getCredentials(final NetHttpTransport httpTransport) throws Exception {

		InputStream in = GmailService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);

		if (in == null) {
			throw new FileNotFoundException("credentials.json not found in resources");
		}

		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY,
				clientSecrets, SCOPES).setDataStoreFactory(new FileDataStoreFactory(new File(TOKENS_DIRECTORY_PATH)))
				.setAccessType("offline").build();

		LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();

		return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
	}

	public static Gmail getService() throws Exception {

		NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();

		Credential credential = getCredentials(httpTransport);

		return new Gmail.Builder(httpTransport, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();
	}

	public static String waitForOtp(
	        String email,
	        Instant otpRequestedTime) throws Exception {

	    Gmail gmail = getService();

	    long timeout = 120_000;
	    long startTime = System.currentTimeMillis();

	    String query =
	            "from:noreply@revaliyo.com "
	            + "to:" + email + " "
	            + "subject:\"Your Revaliyo verification code\"";

	    Pattern pattern = Pattern.compile(
	            "<span[^>]*>\\s*(\\d{6})\\s*</span>",
	            Pattern.CASE_INSENSITIVE);

	    while (System.currentTimeMillis() - startTime < timeout) {

	        ListMessagesResponse response = gmail.users()
	                .messages()
	                .list("me")
	                .setQ(query)
	                .setMaxResults(1L)
	                .execute();

	        if (response.getMessages() != null
	                && !response.getMessages().isEmpty()) {

	            Message message = response.getMessages().get(0);

	            Message fullMessage = gmail.users()
	                    .messages()
	                    .get("me", message.getId())
	                    .setFormat("full")
	                    .execute();

	            long emailTime = fullMessage.getInternalDate();

	            Instant emailReceivedTime =
	                    Instant.ofEpochMilli(emailTime);

	            System.out.println("emailReceivedTime: " + emailReceivedTime);
	            System.out.println("otpRequestedTime: " + otpRequestedTime);

	            if (!emailReceivedTime.isBefore(otpRequestedTime)) {

	                String body = getMessageBody(fullMessage);

	                System.out.println("emailbody: " + body);

	                Matcher matcher = pattern.matcher(body);

	                if (matcher.find()) {
	                    String otp = matcher.group(1);

	                    System.out.println("OTP received: " + otp);

	                    return otp;
	                }
	            }
	        }

	        Thread.sleep(5000);
	    }

	    throw new RuntimeException(
	            "OTP email was not received within 120 seconds");
	}

	private static String getMessageBody(Message message) {

		StringBuilder body = new StringBuilder();

		if (message.getPayload() == null) {
			return "";
		}

		extractMessagePart(message.getPayload(), body);

		return body.toString();
	}

	private static void extractMessagePart(MessagePart part, StringBuilder body) {

		if (part.getBody() != null && part.getBody().getData() != null) {

			String data = part.getBody().getData();

			byte[] decodedBytes = Base64.getUrlDecoder().decode(data);

			body.append(new String(decodedBytes, StandardCharsets.UTF_8));
		}

		if (part.getParts() != null) {

			for (MessagePart child : part.getParts()) {
				extractMessagePart(child, body);
			}
		}
	}
	
	public static String getOTP(String email) throws Exception {
		
		Instant otpRequestedTime = Instant.now().minusSeconds(5);
		
		String OTP = waitForOtp(email, otpRequestedTime);
		
		return OTP;
		
	}
}
