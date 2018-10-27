package ee.sk.smartid;

import ee.sk.smartid.exception.TechnicalErrorException;
import ee.sk.smartid.rest.SessionStatusPoller;
import ee.sk.smartid.rest.SmartIdConnector;
import ee.sk.smartid.rest.dao.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class ExtendedAuthenticationRequestBuilder extends AuthenticationRequestBuilder {

    private static final Logger logger = LoggerFactory.getLogger(SmartIdClient.class);

    public ExtendedAuthenticationRequestBuilder(SmartIdConnector connector, SessionStatusPoller sessionStatusPoller) {
        super(connector, sessionStatusPoller);
    }

    public String initiateAuthentication() {
        validateParameters();
        AuthenticationSessionRequest request = createAuthenticationSessionRequest();
        AuthenticationSessionResponse response = getAuthenticationResponse(request);
        return response.getSessionId();
    }

    public SessionStatus getSessionStatus(String sessionId) {
        SessionStatusRequest request = new SessionStatusRequest(sessionId);
        return getConnector().getSessionStatus(request);
    }

    public SessionStatus getSessionStatus(String sessionId, TimeUnit sessionStatusResponseSocketOpenTimeUnit, long sessionStatusResponseSocketOpenTimeValue) {
        SessionStatusRequest request = new SessionStatusRequest(sessionId);
        request.setResponseSocketOpenTime(sessionStatusResponseSocketOpenTimeUnit, sessionStatusResponseSocketOpenTimeValue);
        return getConnector().getSessionStatus(request);
    }

    public SmartIdAuthenticationResponse getAuthenticationResponse(SessionStatus sessionStatus) {
        validateResponse(sessionStatus);
        SmartIdAuthenticationResponse authenticationResponse = createSmartIdAuthenticationResponse(sessionStatus);
        return authenticationResponse;
    }

    private AuthenticationSessionRequest createAuthenticationSessionRequest() {
        AuthenticationSessionRequest request = new AuthenticationSessionRequest();
        request.setRelyingPartyUUID(this.getRelyingPartyUUID());
        request.setRelyingPartyName(this.getRelyingPartyName());
        request.setCertificateLevel(this.getCertificateLevel());
        request.setHashType(this.getHashTypeString());
        request.setHash(this.getHashInBase64());
        request.setDisplayText(this.getDisplayText());
        request.setNonce(this.getNonce());
        return request;
    }

    private AuthenticationSessionResponse getAuthenticationResponse(AuthenticationSessionRequest request) {
        if (isNotEmpty(getDocumentNumber())) {
            return getConnector().authenticate(getDocumentNumber(), request);
        } else {
            NationalIdentity identity = getNationalIdentity();
            return getConnector().authenticate(identity, request);
        }
    }

    private void validateResponse(SessionStatus sessionStatus) {
        if (sessionStatus.getSignature() == null) {
            logger.error("Signature was not present in the response");
            throw new TechnicalErrorException("Signature was not present in the response");
        }
        if (sessionStatus.getCertificate() == null) {
            logger.error("Certificate was not present in the response");
            throw new TechnicalErrorException("Certificate was not present in the response");
        }
    }

    private SmartIdAuthenticationResponse createSmartIdAuthenticationResponse(SessionStatus sessionStatus) {
        SessionResult sessionResult = sessionStatus.getResult();
        SessionSignature sessionSignature = sessionStatus.getSignature();
        SessionCertificate certificate = sessionStatus.getCertificate();

        SmartIdAuthenticationResponse authenticationResponse = new SmartIdAuthenticationResponse();
        authenticationResponse.setEndResult(sessionResult.getEndResult());
        authenticationResponse.setSignedHashInBase64(getHashInBase64());
        authenticationResponse.setHashType(getHashType());
        authenticationResponse.setSignatureValueInBase64(sessionSignature.getValueInBase64());
        authenticationResponse.setAlgorithmName(sessionSignature.getAlgorithm());
        authenticationResponse.setCertificate(CertificateParser.parseX509Certificate(certificate.getValue()));
        authenticationResponse.setRequestedCertificateLevel(getCertificateLevel());
        authenticationResponse.setCertificateLevel(certificate.getCertificateLevel());
        return authenticationResponse;
    }

}
