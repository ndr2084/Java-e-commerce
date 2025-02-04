package socket.examples;

//A Java program for a Server 
import java.net.*; 
import java.io.*;
import java.util.Scanner;

public class MyServer { 
	//initialize socket and input stream 
	private Socket		 socket = null; 
	private ServerSocket server = null;

	private Scanner scanner = null;
	private DataOutputStream out	 = null;
	private DataInputStream in	 = null;


	// constructor with port 
	public MyServer(int port) { 
		// starts server and waits for a connection 
		try { 
			server = new ServerSocket(port); 
			System.out.println("Server started"); 

			System.out.println("Waiting for a client ..."); 

			socket = server.accept();
			System.out.println("Client accepted");

			in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			out = new DataOutputStream(socket.getOutputStream());
			scanner = new Scanner(System.in);


			String line = "";

			// reads message from client until "Over" is sent 
			while (!line.equals("Over")) {
				try {

					line = in.readUTF();
					System.out.println("The Server echoes the line the client sent: " + "\""  + line + "\"");

					String serverInput = scanner.nextLine();
					out.writeUTF(serverInput);
				} 
				catch(IOException i) { 
					System.out.println(i); 
				} 
			} 
			System.out.println("Closing connection"); 

			// close connection 
			socket.close(); 
			in.close();
			out.close();
			scanner.close();
		} 
		catch(IOException i) { 
			System.out.println(i); 
		} 
	} 

	public static void main(String args[]) { 
		MyServer server = new MyServer(5000); 
	} 
} 

