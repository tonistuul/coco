package ee.sk.smartid;

import ee.sk.smartid.ExtendedAuthenticationRequestBuilder;
import ee.sk.smartid.rest.SessionStatusPoller;
import ee.sk.smartid.rest.SmartIdRestConnector;
import org.glassfish.jersey.client.ClientConfig;

import java.util.concurrent.TimeUnit;

public class SmartIdAuthenticationClient {

    private String relyingPartyUUID;
    private String relyingPartyName;
    private String hostUrl;
    private ClientConfig networkConnectionConfig;
    private TimeUnit pollingSleepTimeUnit = TimeUnit.SECONDS;
    private long pollingSleepTimeout = 1L;
    private TimeUnit sessionStatusResponseSocketOpenTimeUnit;
    private long sessionStatusResponseSocketOpenTimeValue;

    /**
     * Gets an instance of the authentication request builder
     *
     * @return authentication request builder instance
     */
    public ExtendedAuthenticationRequestBuilder createAuthentication() {
        SmartIdRestConnector connector = new SmartIdRestConnector(hostUrl, networkConnectionConfig);
        SessionStatusPoller sessionStatusPoller = createSessionStatusPoller(connector);
        ExtendedAuthenticationRequestBuilder builder = new ExtendedAuthenticationRequestBuilder(connector, sessionStatusPoller);
        populateBuilderFields(builder);
        return builder;
    }

    /**
     * Sets the UUID of the relying party
     * <p>
     * Can be set also on the builder level,
     * but in that case it has to be set explicitly
     * every time when building a new request.
     *
     * @param relyingPartyUUID UUID of the relying party
     */
    public void setRelyingPartyUUID(String relyingPartyUUID) {
        this.relyingPartyUUID = relyingPartyUUID;
    }

    /**
     * Gets the UUID of the relying party
     *
     * @return UUID of the relying party
     */
    public String getRelyingPartyUUID() {
        return relyingPartyUUID;
    }

    /**
     * Sets the name of the relying party
     * <p>
     * Can be set also on the builder level,
     * but in that case it has to be set
     * every time when building a new request.
     *
     * @param relyingPartyName name of the relying party
     */
    public void setRelyingPartyName(String relyingPartyName) {
        this.relyingPartyName = relyingPartyName;
    }

    /**
     * Gets the name of the relying party
     *
     * @return name of the relying party
     */
    public String getRelyingPartyName() {
        return relyingPartyName;
    }

    /**
     * Sets the base URL of the Smart-ID backend environment
     * <p>
     * It defines the endpoint which the client communicates to.
     *
     * @param hostUrl base URL of the Smart-ID backend environment
     */
    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
    }

    /**
     * Sets the network connection configuration
     * <p>
     * Useful for configuring network connection
     * timeouts, proxy settings, request headers etc.
     *
     * @param networkConnectionConfig Jersey's network connection configuration instance
     */
    public void setNetworkConnectionConfig(ClientConfig networkConnectionConfig) {
        this.networkConnectionConfig = networkConnectionConfig;
    }

    /**
     * Sets the timeout for each session status poll
     * <p>
     * Under the hood each operation (authentication, signing, choosing
     * certificate) consists of 2 request steps:
     * <p>
     * 1. Initiation request
     * <p>
     * 2. PollRequest status request
     * <p>
     * PollRequest status request is a long poll method, meaning
     * the request method might not return until a timeout expires
     * set by this parameter.
     *  <p>
     * Caller can tune the request parameters inside the bounds
     * set by service operator.
     * <p>
     * If not provided, a default is used.
     *
     * @param timeUnit time unit of the {@code timeValue} argument
     * @param timeValue time value of each status poll's timeout.
     */
    public void setSessionStatusResponseSocketOpenTime(TimeUnit timeUnit, long timeValue) {
        sessionStatusResponseSocketOpenTimeUnit = timeUnit;
        sessionStatusResponseSocketOpenTimeValue = timeValue;
    }

    /**
     * Sets the timeout/pause between each session status poll
     *
     * @param unit time unit of the {@code timeout} argument
     * @param timeout timeout value in the given {@code unit}
     */
    public void setPollingSleepTimeout(TimeUnit unit, long timeout) {
        pollingSleepTimeUnit = unit;
        pollingSleepTimeout = timeout;
    }

    private void populateBuilderFields(SmartIdRequestBuilder builder) {
        builder.withRelyingPartyUUID(relyingPartyUUID);
        builder.withRelyingPartyName(relyingPartyName);
    }

    private SessionStatusPoller createSessionStatusPoller(SmartIdRestConnector connector) {
        SessionStatusPoller sessionStatusPoller = new SessionStatusPoller(connector);
        sessionStatusPoller.setPollingSleepTime(pollingSleepTimeUnit, pollingSleepTimeout);
        sessionStatusPoller.setResponseSocketOpenTime(sessionStatusResponseSocketOpenTimeUnit, sessionStatusResponseSocketOpenTimeValue);
        return sessionStatusPoller;
    }
}
