
public class Program {
	static DbMethods dbm = new DbMethods();
	static ConsoleMethods cm = new ConsoleMethods();
	
	public static void main(String[] args) {
		dbm.Reg();

	}

	static void autoInsert()
	{
		String rendszam = cm.ReadData("Kerem a rendszamot: ");
		String tipus = cm.ReadData("Kerem a tipust: ");
		String szin = cm.ReadData("Kerem a szint: ");
		String kor = cm.ReadData("Kerem az auto korat: ");
		String ar = cm.ReadData("Kerem az arat: ");
		String tulaj = cm.ReadData("Kerem a tulajt: ");

		String sqlp ="INSERT INTO auto Values('"+rendszam+"', '"+
		tipus+"', '"+szin+"', '"+kor+"', '"+ar+", "+tulaj+")";

		dbm.CommandExec(sqlp);
	}

	static void tulajInsert()
	{
		String tkod = cm.ReadData("Kerem a rendszamot: ");
		String nev = cm.ReadData("Kerem a tipust: ");
		String cim = cm.ReadData("Kerem a szint: ");
		String telefon = cm.ReadData("Kerem az auto korat: ");


		String sqlp ="INSERT INTO tulajdonos Values('"+tkod+"', '"+
		nev+"', '"+cim+"', '"+telefon+"');";

		dbm.CommandExec(sqlp);
	}

	static void autoList()
	{
		
	}

	static void menu()
	{
		dbm.SM("\nValassz tablat!\n");
		dbm.SM("0. Kilepes");
		dbm.SM("1. auto");
		dbm.SM("2. tulajdonos");
		dbm.SM("\n");

		String ms_table = cm.ReadData("Valaszod?");
		int m_table = -1;
		if(test(ms_table, 2)) m_table = StringToInt(ms_table);

		dbm.SM("\nMit szeretnel elerni?\n");
		dbm.SM("0. Kilepes");
		dbm.SM("1. Beszuras");
		dbm.SM("2. Listazas");
		dbm.SM("3. Torles\n");

		String ms_op = cm.ReadData("Valaszod?");
		int m_op = -1;
		if(test(ms_op, 3)) m_op = StringToInt(ms_op);

		switch (m_table) {

			case 0:	System.exit(0);	break;

			case 1:
				dbm.SM("Auto tabla modositasa.");
				switch (m_op) {
					case 0: System.exit(0); break;
					case 1: autoInsert(); break;
					case 2: /* LISTAZAS FV */; break;
					case 3: /* TORLES FV */; break;
					default: break;
				}
				break;

			case 2:
				dbm.SM("Tulajdonos tabla modositasa.");
				switch (m_op) {
				case 0: System.exit(0); break;
				case 1: tulajInsert(); break;
				case 2: /* LISTAZAS FV */; break;
				case 3: /* TORLES FV */; break;
				default: break;
				}
				break;

			default: break;
		}

	}

	static boolean test(String testString, int max) 
	{
		if (testString.length() == 0) 
		{
			dbm.SM("Ures bemenet. Probald ujra.");
			return false;
		}
		else try
		{
			int a = Integer.valueOf(testString);
			if(a >= 0 && a <= max) return true;
			else 
			{
				dbm.SM("Helytelen ertek. Probald ujra.");
				return false;
			}
		} catch (NumberFormatException nfe)
		{
			dbm.SM("Helytelen bemenet. Probald ujra.");
			return false;
		}
	}
	
	static int StringToInt(String inputString)
	{
		int x = 0;
		try {
			x=Integer.valueOf(inputString);
		} catch (NumberFormatException nfe) {}
		return x;
	}
}
