package net.continuumsecurity.client;

import java.util.concurrent.atomic.AtomicLong;
import javax.security.auth.login.LoginException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import net.continuumsecurity.model.jaxrs.NessusReply;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * 
 * @author Stephen de Vries
 * @author Adam Parsons
 * @version 0.0.2, 04/29/14
 * @since 1.0.0
 */
public abstract class AbstractNessusClient {
    
    private static final Log LOG = LogFactory.getLog(AbstractNessusClient.class);
    private static final AtomicLong SEQUENCE_COUNTER = new AtomicLong(0);
    
    private final Client client;
    
    protected WebTarget target;
    protected String token;
    
    public AbstractNessusClient(String nessusUrl) {
        client = NessusClientFactory.buildInsecureSSLClient();
        target = client.target(nessusUrl);
    }

    public void login(String username, String password) throws LoginException {
        WebTarget loginTarget = target.path("/login");
        Form form = new Form();
        form.param("login", username);
        form.param("password", password);
        form.param("seq", generateSeqNum());

        NessusReply reply = loginTarget.request(MediaType.APPLICATION_XML_TYPE).post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), NessusReply.class);

        if (!reply.getStatus().equalsIgnoreCase("OK")) {
            throw new LoginException("Error logging in");
        }
        
        token = reply.getContents().getToken();
        LOG.info("Login OK. Token: " + token);
    }

    public void logout() {
        WebTarget logoutTarget = target.path("/logout");
        Form form = prepopulateForm();
        
        sendRequestAndCheckError(logoutTarget,form);
    }

    protected String generateSeqNum() {
        return String.valueOf(SEQUENCE_COUNTER.incrementAndGet());
    }

    protected NessusReply sendRequestAndCheckError(WebTarget target, Form form) {
        NessusReply reply = target.request(MediaType.APPLICATION_XML_TYPE).post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), NessusReply.class);
        
        if (!reply.getStatus().equalsIgnoreCase("OK")) {
            throw new NessusException("Error: Got status: " + reply.getStatus() + " for request to: " + target.getUri());
        }
        
        return reply;
    }

    protected String getStringResponse(WebTarget target, Form form) {
        return target.request(MediaType.APPLICATION_XML_TYPE).post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);
    }

    protected Form prepopulateForm() {
        Form form = new Form();
        form.param("seq", generateSeqNum());
        form.param("token", token);
        
        return form;
    }
    
}
