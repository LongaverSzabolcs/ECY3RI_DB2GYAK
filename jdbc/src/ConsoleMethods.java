import java.util.Scanner;

public class ConsoleMethods {
	Scanner scanInput = new Scanner(System.in);
	
	public String ReadData(String s) {
		String data = "";
		System.out.println(s);
		data = scanInput.nextLine();
		return data;
	}
	
	public void SM(String s) {
		System.out.println(s+"\n");
	}
	
	public boolean checkIfStringIsValidSQLDate(String date) {
		String[] parts = date.split("-");
		if (parts.length != 3) {
			SM("Hibas datum formatum");
			return false;
		}
		int year = Integer.parseInt(parts[0]);
		int month = Integer.parseInt(parts[1]);
		int day = Integer.parseInt(parts[2]);
		if (year != 2025) {
			SM("Hibas ev");
			return false;
		}
		if (month != 6) {
			SM("Hibas honap");
			return false;
		}
		if (day < 1 || day > 10) {
			SM("Hibas nap");
			return false;
		}
		return true;
	}

	public String readString(String label) {
		boolean isValid = false;
		String input = "";
		do {
			input = ReadData(label);
			if (input.isEmpty()) {
				SM("Hibas input");
			} else {
				isValid = true;
			}
		} while (!isValid);
		return input;
	}

	public int readInt(String label) {
		boolean isValid = false;
		int input = 0;
		do {
			String strInput = ReadData(label);
			if (strInput.isEmpty()) {
				SM("Hibas input");
			} else {
				try {
					input = Integer.parseInt(strInput);
					isValid = true;
				} catch (NumberFormatException e) {
					SM("Hibas szam formatum");
				}
			}
		} while (!isValid);
		return input;
	}

	public String readSQLDate(String date) {
		boolean isValid = false;
		String input = "";
		do {
			input = ReadData(date);
			if (input.isEmpty()) {
				SM("Hibas input");
			} else if (!checkIfStringIsValidSQLDate(input)) {
				SM("Rossz datum formatum");
			} else {
				isValid = true;
			}
		} while (!isValid);
		return input;
	}
}
