import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ServerThread implements Runnable {
	// 定义当前线程所处理的Socket
	Socket s = null;
	// 该线程所处理的Socket对于的输入流
	BufferedReader br = null;

	public ServerThread(Socket s) {
		// TODO Auto-generated constructor stub
		this.s = s;
		try {
			br = new BufferedReader(new InputStreamReader(s.getInputStream(),
					"utf-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		// TODO Auto-generated method stub
		try {
			String content = null;
			// 采用循环不断从Socket中读取客户端发来的数据
			while ((content = readFromClient()) != null) {
				for (Socket s : MyServer.socketList) {
					OutputStream os = s.getOutputStream();
					os.write((content + "\n").getBytes("utf-8"));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private String readFromClient() {
		// TODO Auto-generated method stub
		try {
			return br.readLine();
		} catch (Exception e) {
			// 如果捕捉到异常，表面该客户端Socket已经关闭
			// 删除该Socket
			MyServer.socketList.remove(s);
		}
		return null;
	}

}
