import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetDataMethods {
	static DbMethods dbm = new DbMethods();
	static ConsoleMethods cm = new ConsoleMethods();
	
	public int getMaxGuestID() {
		Connection conn = dbm.Connect();
		int maxID = 0;
		String sqlp = "SELECT MAX(id) FROM guests;";
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sqlp);
			if (rs.next()) {
				maxID = rs.getInt(1);
			}
		} catch (SQLException e) {
			cm.SM("getMaxGuestID: " + e.getMessage());
		}
		dbm.Disconnect(conn);
		return maxID;
	}

	public int getMaxPartnerID() {
		Connection conn = dbm.Connect();
		int maxID = 0;
		String sqlp = "SELECT MAX(id) FROM partners;";
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sqlp);
			if (rs.next()) {
				maxID = rs.getInt(1);
			}
		} catch (SQLException e) {
			cm.SM("getMaxPartnerID: " + e.getMessage());
		}
		dbm.Disconnect(conn);
		return maxID;
	}
	
	public boolean checkIfGuest_idExists(int id) {
		Connection conn = dbm.Connect();
		boolean exists = false;
		//nincs idom arra, hogy ennel ertelmesebb metodust irjak csak azert, hogy valahol a forraskodban legyen egy ketfazisu parancs
		String sqlp = "SELECT * FROM guests WHERE id= ?;";
		try {
			PreparedStatement s = conn.prepareStatement(sqlp);
			s.setInt(1, id);
			ResultSet rs = s.executeQuery();
			if (rs.next()) exists = true;
		} catch (SQLException e) {
			cm.SM("checkIfGuest_idExists: " + e.getMessage());
		}
		dbm.Disconnect(conn);
		return exists;
	}

	public boolean checkIfPartnerIdExists(int id) {
		Connection conn = dbm.Connect();
		boolean exists = false;
		//ditto.
		String sqlp = "SELECT * FROM partners WHERE id= ?;";
		try {
			PreparedStatement s = conn.prepareStatement(sqlp);
			s.setInt(1, id);
			ResultSet rs = s.executeQuery();
			if (rs.next()) exists = true;
		} catch (SQLException e) {
			cm.SM("checkIfPartnerIdExists: " + e.getMessage());
		}
		dbm.Disconnect(conn);
		return exists;
	}

	public int getGuestsPartnerID(int id) {
		Connection conn = dbm.Connect();
		int partnerID = 0;
		String sqlp = "SELECT id FROM partners WHERE guest_id=" + id + ";";
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sqlp);
			if (rs.next()) {
				if (rs.getInt("id") != 0) {
					partnerID = rs.getInt("id");
				} else {
					partnerID = -1;
				}
			}
		} catch (SQLException e) {
			cm.SM("getGuestsPartnerID: " + e.getMessage());
		}
		dbm.Disconnect(conn);
		return partnerID;
	}
	
}
