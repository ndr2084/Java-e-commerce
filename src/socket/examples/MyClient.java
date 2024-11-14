package socket.examples;

//A Java program for a Client 
import java.net.*;
import java.util.Scanner;
import java.io.*; 

public class MyClient {
	// initialize socket and input output streams
	private Socket socket		 = null;
	//private DataInputStream input = null;

	private Scanner scanner = null;
	private DataOutputStream out	 = null;
	private DataInputStream in	 = null;

	// constructor to put ip address and port
	public MyClient(String address, int port) {
		// establish a connection
		try { 
			socket = new Socket(address, port); 
			System.out.println("Connected");

			// takes input from terminal
			//input = new DataInputStream(System.in);

			in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			out = new DataOutputStream(socket.getOutputStream());
			scanner = new Scanner(System.in);

		}
		catch(UnknownHostException u) { 
			System.out.println(u); 
		} 
		catch(IOException i) { 
			System.out.println(i); 
		}

		// string to read message from input
		String clientInput = "";

		// keep reading until "Over" is input
		while (!clientInput.equals("Over")) {
			try {
				//s = input.readLine();
				System.out.print("Type your input and then hit Return, or type the keyword \"Over\" and then hit return to exit: ");
				clientInput = scanner.nextLine();
				out.writeUTF(clientInput);

				String server_response = in.readUTF();
				System.out.println("Server: " + server_response);
			} 
			catch(IOException i) { 
				System.out.println(i); 
			} 
		}

		// close the connection
		try {
			//input.close();
			in.close();
			scanner.close();
			out.close(); 
			socket.close(); 
		} 
		catch(IOException i) { 
			System.out.println(i); 
		} 
	} 

	public static void main(String args[]) { 
		MyClient client = new MyClient("127.0.0.1", 5000); 
	} 
} 
