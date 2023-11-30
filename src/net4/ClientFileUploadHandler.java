package net4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ClientFileUploadHandler implements Handler {
	
	// 필요한 객체를 연결하기 위한 멤버변수 선언
	private Scanner scanner; 
	// 객체 생성직후 생성자 실행시에 Scanner 객체를 전달받아서 멤버변수에 대입한다.
	public ClientFileUploadHandler(Scanner scanner) {
		this.scanner = scanner;
	}
	
	@Override
	public void handle(DataInputStream in, DataOutputStream out) throws IOException {
		System.out.println("### 파일서버에게 파일업로드를 요청함...");
		// 파일서버에 요청명령어를 전달
		out.writeInt(Cmd.REQ_UPLOAD);
		
		System.out.print("### 업로드할 파일경로 입력: ");
		String path = scanner.nextString();
		
		File file = new File(path);
		String filename = file.getName();
		long size = file.length();
		// 파일서버에 파일명과 파일크기를 전달
		out.writeUTF(filename);
		out.writeLong(size);
		
		FileInputStream fis = new FileInputStream(file);
		int len = 0;
		byte[] buf = new byte[1024];
		while ((len = fis.read(buf)) != -1) {
			// 파일서버에 파일데이터 전달
			out.write(buf, 0, len);
		}
		fis.close();
		
		// 서버가 보낸 응답 처리하기
		int resCmd = in.readInt();
		if (resCmd == Cmd.RES_MESSAGE) {
			String message = in.readUTF();
			System.out.println("### 메세지: " + message);
		}
	}

}
