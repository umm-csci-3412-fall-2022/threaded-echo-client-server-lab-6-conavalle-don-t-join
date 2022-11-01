package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	
	// REPLACE WITH PORT PROVIDED BY THE INSTRUCTOR
	public static final int PORT_NUMBER = 6013; 
	public static void main(String[] args) throws IOException, InterruptedException {
		EchoServer server = new EchoServer();
		server.start();
	}

	private void start() throws IOException, InterruptedException {
	try{
		ServerSocket sock = new ServerSocket(PORT_NUMBER);
		while (true) {
			Socket client = sock.accept();
        		System.out.println("Got a request!");
        		OutputStream os = client.getOutputStream();
        		InputStream is = client.getInputStream();
			int c;
      // echos what the client sends back to the client
      while ((c = is.read()) != -1) {
          os.write((byte)c);
          os.flush();
      }
      //Disconnect from the client
      client.close();
      System.out.println("Client Disconnected");

	}
    } catch (Exception e) {
	    System.err.println("Exception:  " + e);
      }

			// Put your code here.
			// This should do very little, essentially:
			//   * Construct an instance of your runnable class
			//   * Construct a Thread with your runnable
			//      * Or use a thread pool
	}		//   * Start that thread
	
}
