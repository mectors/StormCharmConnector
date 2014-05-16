/**
 * 
 */
package com.ubuntu.stormcharmconnector;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

/**
 * @author maarten
 * This class reads a yaml file called connections.yaml that is located in /opt/storm/latest/conf.
 * The subordinate StormDeployer charm will update this file with the appropriate connections.
 * Expected format for connections.yaml
 * type:\n
 *    - {host: testip,port: 123,user: test,password: ok}\n
 *    - {host: testip2,port: 345}\n
 * type2:\n
 *    - {host: testip,port: 123,user: test,password: ok}\n
 *    - {host: testip2,port: 345}\n
 */
public abstract class AbstractStormCharmConnector implements
		StormCharmConnector {
	private String type;
	private static final String CONNECTIONS = "/opt/storm/latest/conf/connections.yaml";
	private static final String HOST="host";
	private static final String PORT="port";
	private static final String USER="user";
	private static final String PASSWORD="password";
	private long lastmodified;
	private List<StormCharmConnection> connections;
	
	public AbstractStormCharmConnector(String type)
	{
		this.type=type;
	}
	
	/* (non-Javadoc)
	 * @see com.ubuntu.stormcharmconnector.StormCharmConnector#getConnections()
	 */
	public List<StormCharmConnection> getConnections() throws NoConnectionsException {
		FileReader fr=null;
		try {
			File file = new File(CONNECTIONS);
			if(connections != null && file.lastModified() == lastmodified)
			{
				return connections;
			}
			fr = new FileReader(file);
			connections = getConnections(fr);
			lastmodified=file.lastModified();
			return connections;
		} catch (FileNotFoundException e) {
			throw new NoConnectionsException("Connections file not found or not readable",e);
		} finally
		{
			try {
				if(fr !=null)
					fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public List<StormCharmConnection> getConnections(Reader connectionsfile) throws NoConnectionsException
	{
		List<StormCharmConnection> connections = new ArrayList<StormCharmConnection>();
		Yaml yaml = new Yaml();
		try
		{
			@SuppressWarnings("unchecked")
			Map<String,List<Map<String,Object>>> map = (Map<String,List<Map<String,Object>>>) yaml.load(connectionsfile);
			@SuppressWarnings("unchecked")
			List<Map<String,Object>> conns = map.get(type);

			for(Map<String,Object> conn:conns)
			{
				String host = (String)conn.get(HOST);
				Integer port = (Integer)conn.get(PORT);
				String user = (String)conn.get(USER);
				String password = (String)conn.get(PASSWORD);
				StormCharmConnection stormconn = new StormCharmConnection(host,port,user,password);
				connections.add(stormconn);
			}
		}catch(Exception e)
		{
			throw new NoConnectionsException("Connections file not found or not readable",e);
		}
		return connections;
	}

}
