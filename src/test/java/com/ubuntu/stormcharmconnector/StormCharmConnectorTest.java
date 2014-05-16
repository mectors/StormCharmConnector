package com.ubuntu.stormcharmconnector;

import java.io.StringReader;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class StormCharmConnectorTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public StormCharmConnectorTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( StormCharmConnectorTest.class );
    }

    /**
     * Rigourous Test :-)
     * @throws Exception 
     */
    public void testApp() throws Exception
    {
    	String test = 
    			"mongo:\n" +
    			"    - {host: testip,port: 123,user: test,password: ok}\n" +
    			"    - {host: testip2,port: 345}\n" +
    			"mysql:\n" + 
    			"    - {host: testip,port: 123,user: test,password: ok}\n" +
    			"cassandra:\n" +
    			"    - {host: testip,port: 123,user: test,password: ok}\n";
        StormCharmConnector mysql = new MySQLStormConnector();
        StormCharmConnector cassandra = new CassandraStormConnector();
        StormCharmConnector mongo = new MongoStormConnector();
        List<StormCharmConnection> conns = mysql.getConnections(new StringReader(test));
        assertEquals("testip",conns.get(0).getHost());
        assertEquals(new Integer(123),conns.get(0).getPort());
        assertEquals("test",conns.get(0).getUser());
        assertEquals("ok",conns.get(0).getPassword());
        conns = cassandra.getConnections(new StringReader(test));
        assertEquals("testip",conns.get(0).getHost());
        assertEquals(new Integer(123),conns.get(0).getPort());
        assertEquals("test",conns.get(0).getUser());
        assertEquals("ok",conns.get(0).getPassword());
        conns = mongo.getConnections(new StringReader(test));
        assertEquals("testip",conns.get(0).getHost());
        assertEquals(new Integer(123),conns.get(0).getPort());
        assertEquals("test",conns.get(0).getUser());
        assertEquals("ok",conns.get(0).getPassword());
        assertEquals("testip2",conns.get(1).getHost());
        assertEquals(new Integer(345),conns.get(1).getPort());
        assertNull(conns.get(1).getUser());
        assertNull(conns.get(1).getPassword());
        
    }
}
