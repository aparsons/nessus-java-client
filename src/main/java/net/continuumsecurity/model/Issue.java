package net.continuumsecurity.model;

import java.util.List;

/**
 * 
 * 
 * @author Stephen de Vries
 * @author Adam Parsons
 * @version 0.0.2, 04/29/14
 * @since 1.0.0
 */
public class Issue {
    
    private int pluginID;
    private int port;
    private int severity;
    private String protocol;
    private List<String> hosts;
    private String description;
    private String solution;
    private String output;
    private String synopsis;
    private String pluginName;
    
    public String getPluginName() {
        return pluginName;
    }

    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;
    }

    public int getPluginID() {
        return pluginID;
    }

    public void setPluginID(int pluginID) {
        this.pluginID = pluginID;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public List<String> getHosts() {
        return hosts;
    }

    public void setHosts(List<String> hosts) {
        this.hosts = hosts;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getSolution() {
        return solution;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getOutput() {
        return output;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getSynopsis() {
        return synopsis;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("ID: ").append(pluginID).append("\n")
                    .append("Name: ").append(pluginName).append("\n")
                .append("Description: ").append(description).append("\n")
                .append("Severity: ").append(severity).append("\n").toString();

    }
}
