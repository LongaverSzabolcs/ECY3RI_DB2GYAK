import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbMethods {
	static ConsoleMethods cm = new ConsoleMethods();
	static GetDataMethods gdm = new GetDataMethods();
	
	/* Driver regisztracio. Ennek kell lefutnia leghamarabb! */
	public void Reg() {
		try {
		Class.forName("org.sqlite.JDBC");
		cm.SM("Sikeres driver regisztralas");
		} catch(Exception ex) {
			cm.SM(ex.getMessage());
		}
	}

	/* Csatlakozas az adatbazishoz */
	public Connection Connect() {
		Connection conn = null;
		// Itt a DB fajl eleresi utvonalat adom meg
		
		String url = "jdbc:sqlite:E:/!Egyetem/4_FELEV/Adatb2/Feleves/sqlite/felevi";
		try {
			conn = DriverManager.getConnection(url);
			//SM("Sikeres kapcsolodas");
			return conn;
		} catch(Exception ex) {
			cm.SM(ex.getMessage());
			return conn;
		}
	}
	
	/* Lecsatlakozas */
	public void Disconnect(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
				//SM("Sikeres lekapcsolodas");
			} catch (Exception ex) {
				cm.SM(ex.getMessage());
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
			//SM("Command OK!");
		} catch (SQLException e) {
			cm.SM("Command: " + sqlp);
			cm.SM("CommandExec: " + e.getMessage());
		}
		Disconnect(conn);
	}

	//innen kezdve kezdodik a sajat logika
	
	public void newGuest(String name, int age, String gender, String arrival, String email, int phone) {
		Connection conn = Connect();
		if (email.isEmpty()) {
			email = "NULL";
		} else {
			email = "\'" + email + "\'";
		}
		int id = gdm.getMaxGuestID() + 1;
		String sqlp = "INSERT INTO guests (id, name, age, gender, arrival, email, phone) VALUES " +
		"("+ id +", \'" + name + "\', " + age + ", \'" + gender + "\', \'" + arrival + "\', " + email + ", " + phone + ");";
		try {
			Statement s = conn.createStatement();
			s.execute(sqlp);
			cm.SM("Command OK!");
		} catch (SQLException e) {
			cm.SM("Command: " + sqlp);
			cm.SM("CommandExec: " + e.getMessage());
		}
		Disconnect(conn);
	}

	public void newPartner(int guest_id, String name, int age, String gender, String arrival) {
		Connection conn = Connect();
		int id = gdm.getMaxPartnerID() + 1;
		if(gdm.checkIfGuest_idExists(guest_id)) {
			String sqlp = "INSERT INTO partners (id, guest_id, name, age, gender, arrival) VALUES "+
			"(" + id +", "+ guest_id + ", \'" + name + "\', " + age + ", \'"+ gender + "\', \'" + arrival + "\');";
			try {
				Statement s = conn.createStatement();
				s.execute(sqlp);
				cm.SM("Command OK!");
			} catch (SQLException e) {
				cm.SM("Command: " + sqlp);
				cm.SM("CommandExec: " + e.getMessage());
			}
		} else {
			cm.SM("Nincs ilyen ID-ju vendeg");
		}
		Disconnect(conn);
	}

	public void deletePartnerByID(int id) {
		Connection conn = Connect();
		String sqlp = "DELETE FROM partners WHERE id=" + id + ";";
		try {
			Statement s = conn.createStatement();
			s.execute(sqlp);
			cm.SM("Command OK!");
		} catch (SQLException e) {
			cm.SM("Command: " + sqlp);
			cm.SM("CommandExec: " + e.getMessage());
		}
		Disconnect(conn);
	}

	public void deleteGuestByID(int id) {
		Connection conn = Connect();
		String sqlp = "DELETE FROM guests WHERE id=" + id + ";";
		try {
			Statement s = conn.createStatement();
			s.execute(sqlp);
			cm.SM("Command OK!");
		} catch (SQLException e) {
			cm.SM("Command: " + sqlp);
			cm.SM("CommandExec: " + e.getMessage());
		}
		Disconnect(conn);
	}

	

	public void listAllGuests() {
		Connection conn = Connect();
		String sqlp = "SELECT * FROM guests;";
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sqlp);
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String gender = rs.getString("gender");
				String arrival = rs.getString("arrival");
				String email = rs.getString("email");
				int phone = rs.getInt("phone");
				cm.SM("(" + id + "), " + name + ", kor: " + age + ", nem: " + gender + ", erkezes idopontja: " + arrival + ", elerhetosegek: " + email + ", " + phone);
			}
		} catch (SQLException e) {
			cm.SM("listAllGuests: " + e.getMessage());
		}
		Disconnect(conn);
	}

	public void listAllPartners() {
		Connection conn = Connect();
		String sqlp = "SELECT * FROM partners;";
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sqlp);
			while (rs.next()) {
				int id = rs.getInt("id");
				int guest_id = rs.getInt("guest_id");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				int age = rs.getInt("age");
				String arrival = rs.getDate("arrival").toString();
				cm.SM("(" + id + "), meghívó ID-je: " + guest_id + ", " + name + ", kor: " + age + ", nem: "+ gender + ", erkezes idopontja: " + arrival);
			}
		} catch (SQLException e) {
			cm.SM("listAllPartners: " + e.getMessage());
		}
		Disconnect(conn);
	}

	public void listAllGuestsWithPartners() {
		Connection conn = Connect();
		String sqlp = "SELECT * FROM guests INNER JOIN partners ON guests.id = partners.guest_id;";
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sqlp);
			while (rs.next()) {
				String name = rs.getString("name");

				if (name == null) {
					name = "(nincs email)";
				}

				int age = rs.getInt("age");
				String gender = rs.getString("gender");
				String arrival = rs.getDate("arrival").toString();
				String email = rs.getString("email");
				int phone = rs.getInt("phone");
				cm.SM(name + ", kor: " + age + ", nem: " + gender + ", erkezes idopontja: " + arrival + ", elerhetosegek: " + email + ", tel: " + phone);
			}
		} catch (SQLException e) {
			cm.SM("listAllGuestsWithPartners: " + e.getMessage());
		}
		Disconnect(conn);
	}

	public void listAllGuestsWithoutPartners() {
		Connection conn = Connect();
		String sqlp = "SELECT * FROM guests LEFT JOIN partners ON guests.id = partners.guest_id WHERE partners.guest_id IS NULL;";
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sqlp);
			while (rs.next()) {
				String name = rs.getString(2);

				if (name == null) {
					name = "(nincs email)";
				}

				int age = rs.getInt(3);
				String gender = rs.getString(4);
				String arrival = rs.getDate(5).toString();
				String email = rs.getString(6);
				int phone = rs.getInt(7);
				cm.SM(name + ", kor: " + age + ", nem: " + gender + ", erkezes idopontja: " + arrival + ", elerhetosegek: " + email + ", tel: " + phone);
			}
		} catch (SQLException e) {
			cm.SM("listAllGuestsWithoutPartners: " + e.getMessage());
		}
		Disconnect(conn);
	}


	public void listAllGuestsWithoutEmail() {
		Connection conn = Connect();
		String sqlp = "SELECT * FROM guests WHERE email IS NULL;";
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sqlp);
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String gender = rs.getString("gender");
				String arrival = rs.getDate("arrival").toString();
				int phone = rs.getInt("phone");
				cm.SM("(" + id + "), " + name + ", kor: " + age + ", nem: " + gender + ", erkezes idopontja: " + arrival + ", tel: " + phone);
			}
		} catch (SQLException e) {
			cm.SM("listAllGuestsWithoutEmail: " + e.getMessage());
		}
		Disconnect(conn);
	}

	public void modifyGuestEmail(int id, String email) {
		Connection conn = Connect();
		String sqlp = "UPDATE guests SET email = \'" + email + "\' WHERE id = " + id + ";";
		try {
			Statement s = conn.createStatement();
			s.execute(sqlp);
			//SM("Command OK!");
		} catch (SQLException e) {
			cm.SM("Command: " + sqlp);
			cm.SM("CommandExec: " + e.getMessage());
		}
		Disconnect(conn);
	}
}
