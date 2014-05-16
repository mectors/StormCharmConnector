package com.ubuntu.stormcharmconnector;

import java.io.Reader;
import java.util.List;

/**
 * Get a list of StormCharmConnection 
 * Usage:
 * StormCharmConnector connector = new CassandraStormConnector();
 * List<StormCharmConnection> connections = connector.getConnections();
 * StormCharmConnection connection = connections.get(0);
 * String host = connection.getHost();
 * Integer port = connection.getPort():
 * String user = connection.getUser();
 * String password = connection.getPassword(); 
 */
public interface StormCharmConnector 
{
	public List<StormCharmConnection> getConnections() throws NoConnectionsException;
	public List<StormCharmConnection> getConnections(Reader reader) throws NoConnectionsException;
}
