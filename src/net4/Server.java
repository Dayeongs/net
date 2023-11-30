package net4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
	
	private Map<Integer, Handler> map = new HashMap<>();
	
	public Server() {
		// 요청명령을 key, Handler 구현 객체를 value로 구성해서 Map 객체에 저장
		map.put(Cmd.REQ_FILE_LIST, new ServerFileListHandler());
		map.put(Cmd.REQ_DOWNLOAD, new ServerFileDownloadHandler());
		map.put(Cmd.REQ_UPLOAD, new ServerFileUploadHandler());
	}
	
	public void startup() throws IOException {
		// 클라이언트의 연결요청을 접수받는 ServerSocket을 생성하고, 포트번호를 지정한다.
		ServerSocket serverSocket = new ServerSocket(30000);
		System.out.println("### 파일서버가 시작됨...");
		
		while (true) {
			System.out.println("### 파일서버가 클라이언트의 연결요청을 기다림...");
			Socket socket = serverSocket.accept();	// accept() : 연결요청을 기다리는 메소드
			System.out.println("### 파일서버에 클라이언트의 연결요청이 접수됨...");
			System.out.println("### 파일서버는 클라이언트와 통신할 소켓을 생성함...");
			
			// 제공된 소켓과 연결된 클라이언트와의 통신을 담당하는 스레드 객체
			ServerThread thread = new ServerThread(map, socket);
			thread.start();
		}
	}
//	
//	public void startup() throws IOException {
//		System.out.println("### 파일서버가 가동됨...");
//		ServerSocket serverSocket = new ServerSocket(30000);
//		
//		while (true) {
//			System.out.println("### 파일서버가 클라이언트의 요청을 대기중...");
//			Socket socket = serverSocket.accept();
//			System.out.println("### 파일서버가 클라이언트의 연결요청을 접수받음...");
//			
//			System.out.println("### 파일서버가 클라이언트와 통신할 스트림을 생성함...");
//			DataInputStream in = new DataInputStream(socket.getInputStream());
//			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
//			
//			System.out.println("### 파일서버가 클라이언트의 요청을 분석함...");
//			// 클라이언트가 서버로 보내는 메세지
//			// 3은 Cmd.REQ_FILE_LIST 요청이다.
//			// 2는 Cmd.REQ_FILE_DOWNLOAD 요청이다.
//			// 1은 Cmd.REQ_FILE_UPLOAD 요청이다.
//			// 클라이언트가 서버로 보낸 메세지에서 요청 명령어(1, 2, 3 중 하나)를 읽어온다.
//			int cmd = in.readInt();
//			System.out.println("### 클라이언트 요청: " + cmd);
//			// Map 객체에서 cmd에 해당하는 Handler 객체를 가져온다.
//			// 클라이언트가 서보로 보낸 메세지의 첫번째 부분은 항상 요청 명령어다.
//			// 서버는 메세지에서 요청 명령어를 읽어서 Handler 구현객체를 Map에서 가져온다.
//			Handler handler = map.get(cmd);
//			System.out.println("### 조회된 Handler 구현객체: " + handler.getClass().getName());
//			// 획득된 Handler 구현객체의 handler() 메소드를 실행해서 클라이언트의 요청을 처리한다.
//			handler.handle(in, out);
//		}
//	}
	
	public static void main(String[] args) throws IOException {
		Server server = new Server();
		server.startup();
	}

}
