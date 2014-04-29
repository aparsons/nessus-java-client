package net.continuumsecurity;

import java.util.List;
import java.util.Map;
import javax.security.auth.login.LoginException;

import net.continuumsecurity.model.Issue;
import net.continuumsecurity.model.jaxrs.Host;
import net.continuumsecurity.model.jaxrs.Port;
import net.continuumsecurity.model.jaxrs.ReportItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * 
 * 
 * @author Stephen de Vries
 * @author Adam Parsons
 * @version 0.0.2, 04/29/14
 * @since 1.0.0
 */
@Ignore("Requires local nessus instance")
public class ReportClientTest {
    NessusReportClient client;
    String nessusUrl = "https://localhost:8834";
    String user = "continuum";
    String password = "continuum";
    String policyName = "test";
    String scanUuid = "e2e44ca3-7a0e-f6f8-73fd-04b127ef3f18f82ed1c65a88a7f8";
    String hostname = "127.0.0.1";
    int port = 22;
    int pluginID = 10267;


    @Before
    public void setup() {
        client = new NessusReportClient(nessusUrl);
    }
    @Test
    public void testGetHostsFromReport() throws LoginException {
        client.login(user,password);
        List<Host> hosts = client.getHostsFromReport(scanUuid);
        assertThat(hosts.size(), equalTo(1));
        assertThat(hosts.get(0).getHostname(), equalTo(hostname));
    }

    @Test
    public void testGetPortsFromHost() throws LoginException {
        client.login(user,password);
        List<Port> ports = client.getPortsFromHost(scanUuid,hostname);
        assertThat(ports.size(),greaterThan(2));
    }

    @Test
    public void testGetDetailsFromPort() throws LoginException {
        client.login(user,password);
        List<ReportItem> details = client.getFindingsFromPort(scanUuid,hostname,22,"tcp");
        assertThat(details.size(),greaterThan(0));
    }

    @Test
    public void testGetIssuesByPluginId() throws LoginException {
        client.login(user,password);
        Map<Integer,Issue> issues = client.getAllIssuesSortedByPluginId(scanUuid);
        assertThat(issues.get(pluginID).getSynopsis(), equalTo("An SSH server is listening on this port."));
    }


}
