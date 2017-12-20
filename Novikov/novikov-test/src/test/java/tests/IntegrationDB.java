package tests;

import com.fujitsu.fs.utils.PropertiesRetriever;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.Assert;
import org.junit.Test;

public class IntegrationDB extends DBTestCase {

    public IntegrationDB(String name){

        super(name);

        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,PropertiesRetriever.getConnection_URL());
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,PropertiesRetriever.getDriver());
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,PropertiesRetriever.getUsername());
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,PropertiesRetriever.getPassword());
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA,"Users");

    }

    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSet(getClass().getResourceAsStream(("/src/test/resources/users_test.xml")));
    }


    @Test
    public void testFirst() throws Exception{

        //Fetch database data
        IDataSet databaseDataSet = getConnection().createDataSet();
        //fetch table from all data
        ITable actualTable = databaseDataSet.getTable("Users");

        Assert.assertTrue(actualTable.getRowCount() > 0);

    }

//    @Test
//    protected void testSecond() throws Exception {
//
//         ITable expectedTable = getDataSet().getTable("Users");
//
//
//
//    }


}
