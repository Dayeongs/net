package net1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client1 {
	
	public static void main(String[] args) throws IOException {
		
		// Socket 객체를 생성한다.
		// 이 Socket 객체는 IP 주소가 192.168.0.7이고 port 번호가 30000번으로
		// 연결요청을 보낸다.
		Socket socket = new Socket("192.168.0.7", 30000);
		
		// 서버측의 소켓과 연결된 OutputStream, InputStream 객체를 획득하기
		OutputStream os = socket.getOutputStream();
		InputStream is = socket.getInputStream();
		
		// 서버측 소켓과 텍스트 데이터를 주고받기 위해서
		// PrintWriter, BufferedReader 객체와 연결함.
		PrintWriter writer = new PrintWriter(os, true); // true는 Auto flush가 되게 하기 위해서
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		
		// 서버측 소켓으로 텍스트 메세지 보내기
		writer.println("홍길동");
		
		// 서버가 보낸 메세지 수신하기
		String message = reader.readLine();
		System.out.println("서버가 보낸 응답 메세지: " + message);
	}

}
