
public class Program {
	static DbMethods dbm = new DbMethods();
	static ConsoleMethods cm = new ConsoleMethods();
	static GetDataMethods gdm = new GetDataMethods();
	static ModifyDataMethods mdm = new ModifyDataMethods();
	
	public static void main(String[] args) {

		dbm.Reg();
		
		//menu loop
		do {
			cm.SM("== MENU ==");
			cm.SM("1. Vendeg felvitel");
			cm.SM("2. Vendeg torles");
			cm.SM("3. Partner felvitel");
			cm.SM("4. Partner torles");
			cm.SM("5. Vendegek listazasa");
			cm.SM("6. Partnerek listazasa");
			cm.SM("7. e-mail cim nelkuli vendegek listazasa");
			cm.SM("8. partnerrel nem rendelkezo vendegek listazasa");
			cm.SM("9. Vendeg e-mail modositasa");
			cm.SM("10. kilepes");
			int choice = Integer.parseInt(cm.ReadData("Valasszon egy menupontot: "));
			switch (choice) {
				case 1:
					mdm.insertNewGuest();
					break;
				case 2:
					int guest_id = cm.readInt("Adja meg a torolni kivant vendeg azonositojat: ");
					mdm.removeGuest(guest_id);
					break;
				case 3:
					mdm.insertNewPartner();
					break;
				case 4:
					int partnerID = cm.readInt("Adja meg a torolni kivant partner azonositojat: ");
					mdm.removePartner(partnerID);
					break;
				case 5:
					dbm.listAllGuests();
					break;
				case 6:
					dbm.listAllPartners();
					break;
				case 7:
					dbm.listAllGuestsWithoutEmail();
					break;
				case 8:
					dbm.listAllGuestsWithoutPartners();
					break;
				case 9:
					mdm.changeGuestEmailAddress();
					break;
				case 10:
					System.exit(0);
					break;
				default:
					cm.SM("Hibas menupont");
					break;
			}			
		} while (true);
	}
}
