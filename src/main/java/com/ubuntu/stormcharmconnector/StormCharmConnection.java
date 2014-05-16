/**
 * 
 */
package com.ubuntu.stormcharmconnector;

/**
 * @author maarten
 * Provides access to the host, port, user and password of a Charm Relation
 */
public class StormCharmConnection {
	public StormCharmConnection(String host, Integer port, String user, String password)
	{
		this.host=host;
		this.port=port;
		this.user=user;
		this.password=password;
	}
	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}
	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}
	/**
	 * @return the port
	 */
	public Integer getPort() {
		return port;
	}
	/**
	 * @param port the port to set
	 */
	public void setPort(Integer port) {
		this.port = port;
	}
	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	private String host;
	private Integer port;
	private String user;
	private String password;
}
