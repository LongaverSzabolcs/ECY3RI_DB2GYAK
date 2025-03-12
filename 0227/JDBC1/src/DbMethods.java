import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbMethods {
	
	/* Csatlakozas az adatbazishoz */
	public Connection Connect() {
		Connection conn = null;
		// Itt a DB fajl eleresi utvonalat adom meg
		String url = "jdbc:sqlite:E:/!Egyetem/4_FELEV/Adatb2/0227/sqlite/projektdb";
		try {
			conn = DriverManager.getConnection(url);
			SM("Sikeres kapcsolodas");
			return conn;
		} catch(Exception ex) {
			SM(ex.getMessage());
			return conn;
		}
	}
	
	/* Lecsatlakozas */
	public void Disconnect(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
				SM("Sikeres lekapcsolodas");
			} catch (Exception ex) {
				SM(ex.getMessage());
			}
		}
	}
	
	/* SQL parancs vegrehajtasa. Pofonegyszeru, magaert beszel mit csinal. */
	public void CommandExec(String command) {
		Connection conn = Connect();
		String sqlp = command;
		try {
			Statement s = conn.createStatement();
			s.execute(sqlp);
			SM("Command OK!");
		} catch (SQLException e) {
			SM("Command: " + sqlp);
			SM("CommandExec: " + e.getMessage());
		}
		Disconnect(conn);
	}
	
	/* Listazunk mindent es mindenkit */
	public void ReadAllData() {
		String rend="", tip="", szin="", x="\t";
		int kor=0, ar=0;
		String sqlp = "SELECT rendszam, tipus, szin, kor, ar FROM Auto";
		Connection conn = Connect();
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sqlp);
			while(rs.next()) {
				rend = rs.getString("rendszam");
				tip = rs.getString("tipus");
				szin = rs.getString("szin");
				kor = rs.getInt("kor");
				ar = rs.getInt("ar");
				SM(rend+x+tip+x+szin+x+kor+x+ar);
			}
			rs.close();
		} catch (SQLException e) {
			SM("ReadAllData: " + e.getMessage());
		}
		Disconnect(conn);
	}
	
	/* Driver regisztracio. Ennek kell lefutnia leghamarabb! */
	public void Reg() {
		try {
		Class.forName("org.sqlite.JDBC");
		SM("Sikeres driver regisztralas");
		} catch(Exception ex) {
			SM(ex.getMessage());
		}
	}
	
	/* Szegeny ember cw+dupla tabja. Utalom a java-t */
	public void SM(String s) {
		System.out.println(s+"\n");
	}
}
