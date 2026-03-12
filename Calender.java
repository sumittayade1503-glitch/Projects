import java.util.Scanner;

public class Calender {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int month = 0;
		int m = 0;
		boolean v = false;
		int year = 0;

		// year validation logic
		while (v == false) {
			System.out.println("This calender can only predict Anno Domini dates");
			System.out.println("Enter year range in 1 to 9999:");
			year = sc.nextInt();
			if (year >= 1 && year <= 9999) {
				v = true;
			} else {
				System.out.println("Invalid year.");
				v = false;
			}
		}

		// leap year validation logic
		boolean isLeapYear = false;
		if (year % 400 == 0) {
			isLeapYear = true;
		} else if (year % 100 == 0) {
			isLeapYear = false;
		} else if (year % 4 == 0) {
			isLeapYear = true;
		}

		// month validation logic
		while (m == 0) {
			System.out.println("Enter month between 1-12:");
			month = sc.nextInt();
			if (month > 12 || month < 1) {
				System.out.println("Invalid month");
				m = 0;
			} else {
				m = 1;
			}
		}

		// maxday decision logic
		int maxDays = 0;
		switch (month) {
		case 1, 3, 5, 7, 8, 10, 12:
			maxDays = 31;
			break;
		case 4, 6, 9, 11:
			maxDays = 30;
			break;
		case 2:
			maxDays = 29;
			break;
		}

		if (month == 2 && isLeapYear != true) {
			maxDays = 28;
		}

		int d = 0, centuryCode = 0, leapYearCorrectionForJanAndFeb = 0;
		int date = 0;

		// date validation logic
		while (d == 0) {
			System.out.println("Enter a valid date (1-" + maxDays + "):");
			date = sc.nextInt();
			if (date <= maxDays && date > 0) {
				d = 1;
			} else {
				d = 0;
			}
		}

		System.out.println("\nDate entered is: " + date + "-" + month + "-" + year);

		int a = 0;
		int[] monthCode = { 0, 1, 4, 4, 0, 2, 5, 0, 3, 6, 1, 4, 6 };
		a = monthCode[month];

		// Century code calculation
		int l = year % 400;
		if (l > -1 && l < 100)
			centuryCode = 6;
		else if (l >= 100 && l < 200)
			centuryCode = 4;
		else if (l >= 200 && l < 300)
			centuryCode = 2;
		else
			centuryCode = 0;

		// Leap year correction for Jan and Feb
		if (isLeapYear == true) {
			if (month == 1 || month == 2)
				leapYearCorrectionForJanAndFeb = -1;
			else
				leapYearCorrectionForJanAndFeb = 0;
		}

		// extraction of the last two digits
		int lastTwoDigit = year % 100;

		int yearDivideFourCode = lastTwoDigit / 4;
		int yearModulusSevenCode = lastTwoDigit % 7;

		int sum = date + a + yearDivideFourCode + yearModulusSevenCode + leapYearCorrectionForJanAndFeb + centuryCode;

		String day = "";
		switch (sum % 7) {
		case 1:
			day = "Sunday";
			break;
		case 2:
			day = "Monday";
			break;
		case 3:
			day = "Tuesday";
			break;
		case 4:
			day = "Wednesday";
			break;
		case 5:
			day = "Thursday";
			break;
		case 6:
			day = "Friday";
			break;
		case 0:
			day = "Saturday";
			break;
		}

		System.out.println("The calculated day of the week is: " + day);
		sc.close();
	}
}
