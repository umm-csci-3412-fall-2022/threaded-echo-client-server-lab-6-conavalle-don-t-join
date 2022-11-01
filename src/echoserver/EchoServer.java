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
		Thread[] listThreads = new Thread[10];
		int index = 0;
		while (true) {
			if(index < 10){
			Socket client = sock.accept();
			System.out.println("Client Accepted");
			listThreads[index] = new Thread(new clientThread(client));
			listThreads[index].start();
			index++;
			}
			else System.out.println("Not cool man"); 
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


	public class clientThread implements Runnable {
		Socket client;
		OutputStream os;
		InputStream is;
		int c;
	
		public clientThread(Socket inClient){
			try{
				this.client = inClient;
				os = client.getOutputStream();
				is = client.getInputStream();
			} catch (Exception e) {
				System.err.println("Exception:  " + e);
			  }
			
		}

		@Override
		public void run (){
			try {
			while((c = is.read()) != -1){
				os.write((byte)c);
				os.flush();
			}
			client.close();
			System.out.println("Client Disconnected");
		} catch (Exception e) {
			System.err.println("Exception:  " + e);
		  }

		}
	
	}	
}
