
public class ModifyDataMethods {
	static DbMethods dbm = new DbMethods();
	static ConsoleMethods cm = new ConsoleMethods();
	static GetDataMethods gdm = new GetDataMethods();
	
	public void changeGuestEmailAddress() {
		int id = Integer.parseInt(cm.readString("Adja meg a vendeg ID-t: "));
		if (!gdm.checkIfGuest_idExists(id)) {
			cm.SM("Nincs ilyen vendeg az adatbazisban");
		} else {
			String email = cm.ReadData("Adja meg az uj email cimet: ");
			dbm.modifyGuestEmail(id, email);
		}
	}
	
	public void insertNewGuest() {
		String name = cm.readString("Nev: ");
		int age = Integer.parseInt(cm.readString("Kor: "));
		String gender = cm.readString("Nem: ");
		String arrival = cm.readSQLDate("Erkezes idopontja (YYYY-MM-DD): ");
		String email = cm.ReadData("Email: "); //lehet ures
		int phone = Integer.parseInt(cm.readString("Telefon: "));
		dbm.newGuest(name, age, gender, arrival, email, phone);
	}

	public void insertNewPartner() {
		int guest_id = Integer.parseInt(cm.readString("Meghivo ID: "));
		String name = cm.readString("Nev: ");
		int age = Integer.parseInt(cm.readString("Kor: "));
		String gender = cm.readString("Nem: ");
		String arrival = cm.readSQLDate("Erkezes idopontja (YYYY-MM-DD): ");
		dbm.newPartner(guest_id, name, age, gender, arrival);
	}
	
	public void removeGuest(int id) {
		if (gdm.checkIfGuest_idExists(id)) {
			if(gdm.checkIfPartnerIdExists(gdm.getGuestsPartnerID(id))) {
				cm.SM("A vendeghez partner is tartozik, torles elott eloszor a partnert kell torolni");
			} else {
				dbm.deleteGuestByID(id);
			}
		} else {
			cm.SM("Nincs ilyen vendeg az adatbazisban");
		}

	}

	public void removePartner(int id) {
		if (gdm.checkIfPartnerIdExists(id)) {
			dbm.deletePartnerByID(id);
		} else {
			cm.SM("Nincs ilyen partner az adatbazisban");
		}
	}
}
