
public class Program {
	static DbMethods dbm = new DbMethods();
	static ConsoleMethods cm = new ConsoleMethods();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		dbm.Reg();
		
		dbm.SM("Rekord adatainek beolvasasa, rekord beszurasa");
		
		String rendszam = cm.ReadData("Kerem a rendszamot: ");
		String tipus = cm.ReadData("Kerem a tipust: ");
		String szin = cm.ReadData("Kerem a szint: ");
		String kor = cm.ReadData("Kerem az auto korat: ");
		String ar = cm.ReadData("Kerem az arat: ");
		
		/*
		String sqlp ="INSERT INTO Auto Values('"+rendszam+"', '"+
		tipus+"', '"+szin+"', '"+kor+"', '"+ar+")";
		
		dbm.CommandExec(sqlp);
		*/
		
		dbm.Insert(rendszam, tipus, szin, kor, ar);
		//meghivhatnam ezen belulrol is a cm.ReadData() metodust,
		//de oly felesleges ezt megegyszer leirnom szerintem.
	}

}
