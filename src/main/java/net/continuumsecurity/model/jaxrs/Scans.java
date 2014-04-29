package net.continuumsecurity.model.jaxrs;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * 
 * 
 * @author Stephen de Vries
 * @author Adam Parsons
 * @version 0.0.2, 04/29/14
 * @since 1.0.0
 */
public class Scans {
    
    private List<Scan> scan;

    @XmlElementWrapper(name="scanList")
    @XmlElement(name="scan")
    public List<Scan> getScan() {
        return scan;
    }

    public void setScan(List<Scan> scan) {
        this.scan = scan;
    }
    
}
