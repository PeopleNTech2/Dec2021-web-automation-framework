package dabase;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import utility.Utilities;

import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectDB {

    Properties prop = Utilities.loadProperties("../config.properties");

    public static MongoDatabase mongoDatabase = null;

    public static Connection connect = null;
    public static Statement statement = null;
    public static PreparedStatement ps = null;
    public static ResultSet resultSet = null;


    public Connection connectToMySql() {
        String driverClass = prop.getProperty("MYSQLJDBC.driver");
        String url = prop.getProperty("MYSQLJDBC.url");
        String userName = prop.getProperty("MYSQLJDBC.userName");
        String password = prop.getProperty("MYSQLJDBC.password");
        try {
            Class.forName(driverClass);
            connect = DriverManager.getConnection(url,userName,password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Database is connected");
        return connect;
    }
    public MongoDatabase connectToMongoDB() {
        String databaseName = prop.getProperty("mongodb.db.name");
        MongoClient mongoClient = new MongoClient();
        mongoDatabase = mongoClient.getDatabase(databaseName);
        System.out.println("Database Connected");
        return mongoDatabase;
    }
    public List<String> readMysqlDataBaseColumn(String tableName, String columnName){
        List<String> data = new ArrayList<String>();

        try {
            connectToMySql();
            statement = connect.createStatement();
            resultSet = statement.executeQuery("select * from " + tableName);
            data = getResultSetData(resultSet, columnName);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            close();
        }
        return data;
    }
    private void close() {
        try{
            if(resultSet != null){
                resultSet.close();
            }
            if(statement != null){
                statement.close();
            }
            if(connect != null){
                connect.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private List<String> getResultSetData(ResultSet resultSet, String columnName) throws SQLException {
        List<String> dataList = new ArrayList<String>();
        while(resultSet.next()){
            String itemName = resultSet.getString(columnName);
            dataList.add(itemName);
        }
        return dataList;
    }

    public void insertDataFromArrayToMySql(int[] ArrayData, String tableName, String columnName)
    {
        try {
            connectToMySql();
            ps = connect.prepareStatement("DROP TABLE IF EXISTS `"+tableName+"`;");
            ps.executeUpdate();
            ps = connect.prepareStatement("CREATE TABLE `"+tableName+"` (`ID` int(11) NOT NULL AUTO_INCREMENT,`SortingNumbers` bigint(20) DEFAULT NULL,  PRIMARY KEY (`ID`) );");
            ps.executeUpdate();
            for (int aArrayData : ArrayData) {
                ps = connect.prepareStatement("INSERT INTO " + tableName + " ( " + columnName + " ) VALUES(?)");
                ps.setInt(1, aArrayData);
                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertDataFromStringToMySql(String ArrayData,String tableName, String columnName)
    {
        try {
            connectToMySql();
            ps = connect.prepareStatement("INSERT INTO "+tableName+" ( "+columnName+" ) VALUES(?)");
            ps.setString(1,ArrayData);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> directDatabaseQueryExecute(String passQuery,String dataColumn){
        List<String> data = new ArrayList<String>();
        try {
            connectToMySql();
            statement = connect.createStatement();
            resultSet = statement.executeQuery(passQuery);
            data = getResultSetData(resultSet, dataColumn);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            close();
        }
        return data;
    }

    public void insertProfileToMySql(String tableName, String columnName1, String columnName2)
    {
        try {
            connectToMySql();
            ps = connect.prepareStatement("INSERT INTO "+tableName+" ( " + columnName1 + "," + columnName2 + " ) VALUES(?,?)");
            ps.setString(1,"John Doe");
            ps.setInt(2,3590);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConnectDB cdb = new ConnectDB();
        cdb.connectToMySql();
    }
}
