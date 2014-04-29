package net.continuumsecurity;

import net.continuumsecurity.client.PolicyNotFoundException;
import net.continuumsecurity.client.ScanNotFoundException;
import org.junit.Before;
import org.junit.Test;

import javax.security.auth.login.LoginException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import org.junit.Ignore;

/**
 * 
 * 
 * @author Stephen de Vries
 * @author Adam Parsons
 * @version 0.0.2, 04/29/14
 * @since 1.0.0
 */
@Ignore("Requires local nessus intance")
public class ScanClientTest {
    NessusScanClient client;
    String nessusUrl = "https://localhost:8834";
    String user = "continuum";
    String password = "continuum";
    String policyName = "test";
    String scanName = "testScan";
    String scanUuid = "e2e44ca3-7a0e-f6f8-73fd-04b127ef3f18f82ed1c65a88a7f8";
    String hostname = "127.0.0.1";
    int port = 22;


    @Before
    public void setup() {
        client = new NessusScanClient(nessusUrl);
    }

    @Test
    public void testLoginWithCorrectCreds() throws LoginException {
        client.login(user,password);
    }

    @Test(expected=LoginException.class)
    public void testLoginWithWrongCreds() throws LoginException {
        client.login(user,"sdfsdfasdf");
    }

    @Test(expected=ScanNotFoundException.class)
    public void testGetScanStatusForNonExistentScan() throws ScanNotFoundException,LoginException {
        client.login(user,password);
        client.getScanStatus("nonexistent333");
    }

    @Test
    public void testGetPolicyIDFromNameWithNameTEST() throws LoginException, PolicyNotFoundException {
        client.login(user,password);
        int id = client.getPolicyIDFromName("test");
        assertNotEquals(0,id);
    }

    @Test
    public void testIsScanRunning() throws LoginException {
        client.login(user,password);
        String scanId = client.newScan(scanName,policyName,hostname);
        boolean status = client.isScanRunning(scanName);
        assertThat(status,is(true));
        try {
            Thread.sleep(60*1*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        status = client.isScanRunning(scanName);
        assertThat(status,is(false));
    }

}
