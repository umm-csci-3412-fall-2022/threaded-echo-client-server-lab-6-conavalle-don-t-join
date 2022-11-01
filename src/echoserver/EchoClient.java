package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class EchoClient {
	public static final int portNumber = 6013;

	public static void main(String[] args) throws IOException {
		EchoClient client = new EchoClient();
		client.start();
	}

	private void start() throws IOException {
		try {
			Socket socket = new Socket("127.0.0.1", portNumber);
			OutputStream os = socket.getOutputStream();
			InputStream is = socket.getInputStream();

			Thread coolNewAndFreshThread = new Thread(new reader(is));
			coolNewAndFreshThread.start();
			Thread coolerNewerAnderFresherThreader = new Thread(new writer(os));
			coolerNewerAnderFresherThreader.start();
			coolerNewerAnderFresherThreader.join();
			coolNewAndFreshThread.join();
			// flush out the last bit of info beofre we shutdown
			os.flush();
			System.out.flush();
			// We shutdown the connection instead of closing the socket
			socket.shutdownOutput();
			socket.shutdownInput();
		} catch (Exception e) {
			System.err.println("Exception:  " + e);
		}
	}

	public class reader implements Runnable {
		InputStream is;

		reader(InputStream input) {
			is = input;
		}

		@Override
		public void run() {
			try {
				int character;
				while ((character = is.read()) != -1) {
					System.out.write(character);
				}
				System.out.flush();
			} catch (Exception e) {
				System.err.println("The Exception thing");
			}

		}

	}

	public class writer implements Runnable {
		OutputStream os;

		writer(OutputStream output) {
			os = output;
		}

		@Override
		public void run() {
			try {
				int c;
				while ((c = System.in.read()) != -1) {
					os.write((byte) c);
					os.flush();
				}
			} catch (Exception e) {
				System.err.println("Exception:  " + e);
			}
		}
	}
}
