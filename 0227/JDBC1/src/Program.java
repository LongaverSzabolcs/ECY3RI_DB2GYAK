
public class Program {
	static DbMethods dbm = new DbMethods();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		dbm.Reg();
		
		/*
		String sqlp = "CREATE TABLE Auto (Rendszam char(6) primary key, Tipus char(30)," 
		+ "Szin char (20), Kor number(3), Ar number(10))";
		dbm.CommandExec(sqlp);
		
		Ezt mar egyszer lefuttattam!
		*/
		
		String sqlp = "INSERT INTO Auto Values('ABC500', 'Opel Corsa', 'piros', 8, 800000);";
		dbm.CommandExec(sqlp);
		
		sqlp = "INSERT INTO Auto Values('BBM104', 'Suzuki Swift', 'piros', 5, 1500000);";
		dbm.CommandExec(sqlp);
		
		sqlp = "INSERT INTO Auto Values('CHR411', 'Renault Twingo', 'piros', 12, 700000);";
		dbm.CommandExec(sqlp);
		
		//dbm.Disconnect(dbm.Connect());
		
		//dbm.ReadAllData();
		
		
	}

}
