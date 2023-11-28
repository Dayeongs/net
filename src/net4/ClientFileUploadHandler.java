package net4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ClientFileUploadHandler implements Handler {
	
	@Override
	public void handle(DataInputStream in, DataOutputStream out) throws IOException {
		System.out.println("### 파일서버에게 파일업로드를 요청함...");
		
		out.writeInt(Cmd.REQ_UPLOAD);
	}

}
