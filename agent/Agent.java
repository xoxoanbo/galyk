import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import javax.sql.*;

public class Agent 
{
    
    protected static final String GOLDILOCKS_DRIVER_CLASS = "sunje.goldilocks.jdbc.GoldilocksDriver";
    protected static final String URL_BASIC = "jdbc:goldilocks://192.168.0.81:22581/test";
    protected static final String URL_NAMED = "jdbc:goldilocks://192.168.0.81:22581/test?program=MySample";
    protected static final String URL_FOR_DEBUGGING = URL_BASIC + "?global_logger=console&trace_log=on&query_log=on&protocol_log=on";

    public static Connection createConnectionByDriverManager(String id, String password) throws SQLException
    {
        try
        {
            Class.forName(GOLDILOCKS_DRIVER_CLASS);
        }
        catch (ClassNotFoundException sException)
        {
        }

        return DriverManager.getConnection(URL_BASIC, "TEST", "test");
    }

    public static Connection createConnectionByDataSource(String id, String password) throws SQLException
    {
        sunje.goldilocks.jdbc.GoldilocksDataSource sDataSource = new sunje.goldilocks.jdbc.GoldilocksDataSource();
        
        sDataSource.setDatabaseName("test");
        sDataSource.setServerName("127.0.0.1");
        sDataSource.setPortNumber(22581);
        sDataSource.setUser(id);
        sDataSource.setPassword(password);

        return sDataSource.getConnection();
    }

	public static void main(String[] args) throws SQLException 
    
    {

		Runtime rt = Runtime.getRuntime();
		Process p = null;
		Process p2 = null;
		
		try 
        
        {
			
			p = rt.exec("top -b -n 1");
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			
			String cl = null;
			while ((cl = in.readLine()) != null) 
            {
				System.out.println(cl);
			}
			
			System.out.println();
			in.close();
			
		} 
        catch (IOException e) 
        {
			e.printStackTrace();
		}
		
		try 
        {
			
			p2 = rt.exec("sar");
			BufferedReader in = new BufferedReader(new InputStreamReader(p2.getInputStream()));
			
			String cl = null;
			while ((cl = in.readLine()) != null) 
            {
				System.out.println(cl);
			}
			
			System.out.println();
			in.close();
			
		} 
        catch (IOException e) 
        {
			e.printStackTrace();
		}

        Connection con = createConnectionByDriverManager("TEST", "test");
        Statement stmt = con.createStatement();
        stmt.execute("CREATE TABLE SAMPLE_TABLE ( ID INTEGER, NAME CHAR(20) )");
        PreparedStatement pstmt = con.prepareStatement("INSERT INTO SAMPLE_TABLE VALUES (?, ?)");
        pstmt.setInt(1, 100);
        pstmt.setString(2, "Tom");
        pstmt.executeUpdate();
        pstmt.setInt(1, 200);
        pstmt.setString(2, "Jerry");
        pstmt.executeUpdate();
        ResultSet rs = stmt.executeQuery("SELECT * FROM SAMPLE_TABLE");
        while (rs.next())
        {
            System.out.println("ID = " + rs.getInt(1) + ": " + rs.getString(2));
        }
        rs.close();
        stmt.close();
        pstmt.close();
        con.close();

        Connection con2 = createConnectionByDataSource("TEST", "test");
        Statement stmt2 = con2.createStatement();
        stmt2.execute("DROP TABLE SAMPLE_TABLE");
        stmt2.close();
        con2.close();

	}
}
