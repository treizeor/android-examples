import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {

	public static void main(String[] args) throws IOException {
		// 创建一个ServerSocket，用于监听客户端的Socket的连接请求
		ServerSocket ss = new ServerSocket(20001);
		while (true) {
			// 每当接收到客户端Socket的请求，服务器也对应产生一个Socket
			Socket s = ss.accept();
			System.out.println("--accept--");
			OutputStream os = s.getOutputStream();
			os.write("Hello, you got me bless!".getBytes("utf-8"));
			// 关闭输出流， 关闭socket
			os.close();
			s.close();
		}
	}

}
