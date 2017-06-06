package cws;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;

public class HotelArrays2 {
	static String[] hotel = new String[10];
	static int[] sortroomnum = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };// creating
																	// array for
																	// sorting
																	// room
																	// numbers
	static String st = "";
	static StringTokenizer str = new StringTokenizer(st);
	String temp;
	static String roomName;
	static int roomNum;
	static String key;
	static String name;
	static File filewriter = new File("hotel.txt");

	private static void ViewEmptyRooms(String[] hotel) {
		for (int x = 0; x < 10; x++) {
			if (hotel[x].equals("e"))

				System.out.println("room " + (x + 1) + "is empty");
		}
	}

	private static void searchRoomNum(String[] hotel) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter customer name of which room you want to be searched: ");
		name = input.nextLine();
		for (int o = 0; o < hotel.length; o++) {
			if (hotel[o].equalsIgnoreCase(roomName))
				System.out.println(name + " occupies Room " + Integer.toString(roomNum));

		}

	}

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		initialise(hotel);
		while (true) {
			out.println("************Welcome to HotelArrays*************");
			out.println("Enter \"A\" to add customers into the hotel database");
			out.println("Enter \"B\" to sort customers into the hotel database");
			out.println("Enter \"W\" to save customer records into the hotel database");
			out.println("Enter \"S\" to search for customers from the hotel database");
			out.println("Enter \"D\" to delete customers from the hotel database");
			out.println("Enter \"E\" to view empty rooms");
			out.println("Enter \"O\" to view occupied rooms");
			out.println("Enter \"L\" to load and view customers records from the hotel database");
			out.println("Enter \"Q\" to quit the program");

			String user = sc.next();
			if (user.equalsIgnoreCase("a")) {
				Add(hotel);
			} else if (user.equalsIgnoreCase("q")) {
				System.exit(0);
			} else if (user.equalsIgnoreCase("w")) {
				writetoFile(hotel);
			} else if (user.equalsIgnoreCase("b")) {
				Sorting(hotel);
			} else if (user.equalsIgnoreCase("s")) {
				searchRoomNum(hotel);
			} else if (user.equalsIgnoreCase("d")) {
				deleteCustomer(hotel);
			} else if (user.equalsIgnoreCase("e")) {
				ViewEmptyRooms(hotel);
			} else if (user.equalsIgnoreCase("o")) {
				ViewOccupiedRooms(hotel);
			} else if (user.equalsIgnoreCase("l")) {
				LoadFile(hotel);
			} else {
				out.println("Enter a single letter only");
				System.exit(0);
			}

		}

	}

	private static void LoadFile(String[] hotel) throws IOException {

		FileReader file = new FileReader("hotel.txt");// reading from a file
		BufferedReader bw = new BufferedReader(file);
		String line = bw.readLine();// passing data from text into string
									// variable
		StringTokenizer tokenize = new StringTokenizer(line, ";");// separating
																	// numbers
																	// and names
																	// by
																	// semi-colon
		for (int i = 1; i < hotel.length; i++) {
			roomNum = Integer.parseInt(tokenize.nextToken());
			roomName = tokenize.nextToken();

			// System.out.println(roomNum + roomName);

		}

		hotel[roomNum] = roomName;
		out.println("Successfully loaded");
		out.println(line);

	}

	private static void ViewOccupiedRooms(String[] hotel2) {
		// TODO Auto-generated method stub
		for (int x = 0; x < 10; x++)
			if (hotel[x].equals("e")) {// if no one is occuping the room display
										// the following message
				System.out.println("room " + (x + 1) + " occupied by no one");
			} else {

				System.out.println("room " + (x + 1) + " occupied by " + hotel[x]);
			}

	}

	private static void initialise(String hotelRef[]) {
		for (int x = 0; x < 10; x++)
			hotelRef[x] = "e";
		System.out.println("initilise ");
	}

	private static void deleteCustomer(String[] hotel) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		System.out.println("Enter customer name of which room you want to be deleted: ");
		name = input.nextLine();
		for (int O = 0; O < hotel.length; O++) {
			if (hotel[O].equalsIgnoreCase(name))// if hotel array equals entered
												// value display following
												// message
				hotel[O] = "-----";
			out.println();

		}
		out.println("Successfully deleted");

	}

	private static void writetoFile(String[] hotel) throws IOException {
		// TODO Auto-generated method stub
		try {

			FileWriter fw;
			fw = new FileWriter(filewriter, false);
			PrintWriter pr = new PrintWriter(filewriter);
			for (int x = 0; x < hotel.length; x++) {

				pr.print((x + 1) + ";" + hotel[x] + ";");

			}

			pr.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("No such file exists.");

		}

	}

	private static void Add(String[] hotel) throws IOException {
		// TODO Auto-generated method stub
		InputStreamReader stream = new InputStreamReader(System.in);// using
																	// inputstreamreader
																	// and
																	// buffer to
																	// display
																	// first
																	// name and
																	// last name
		BufferedReader br = new BufferedReader(stream);
		System.out.println("Enter room number (1-10) or 11 to stop:");

		roomNum = Integer.parseInt(br.readLine());
		if (roomNum == 0 || roomNum > 10) {
			System.exit(0);
		}
		System.out.println("Enter name for room " + roomNum + " :");

		roomName = br.readLine();
		if (roomNum == 0 || roomNum > 10) {
			System.exit(0);
		}

		hotel[roomNum - 1] = roomName;
		sortroomnum[roomNum - 1] = roomNum;

	}

	private static void Sorting(String[] hotel) {

		boolean sort = true; // This will determine when the sort is finished
		String temp;
		int temp2;

		while (sort) {
			sort = false;
			for (int j = 0; j < hotel.length - 1; j++) {
				if (hotel[j].compareToIgnoreCase(hotel[j + 1]) > 0) { // This is
																		// where
																		// we
																		// begin
																		// the
																		// ascending

					temp = hotel[j];
					hotel[j] = hotel[j + 1]; // swapping
					hotel[j + 1] = temp;

					temp2 = sortroomnum[j];
					sortroomnum[j] = sortroomnum[j + 1];
					sortroomnum[j + 1] = temp2;

					sort = true;
				}
			}
		}

		for (int x = 0; x < 10; x++)
			if (hotel[x].equals("e")) {
				System.out.println("room " + sortroomnum[x] + " occupied by no one");
			} else {

				System.out.println("room " + sortroomnum[x] + " occupied by " + hotel[x]);
			}

	}
}