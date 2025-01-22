
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {

    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 9999;

    public static void main(String[] args) {
        System.out.println("에코 클라이언트 실행 중...");
        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT)) {
            System.out.println("서버에 연결 완료" + socket);
            try (InputStream inputStream = socket.getInputStream(); OutputStream outputStream = socket.getOutputStream(); Scanner scanner = new Scanner(System.in)) {
                while (true) {
                    System.out.println("메시지 입력 (종료는 exit) : ");
                    String message = scanner.nextLine();
                    if ("exit".equalsIgnoreCase(message)) {
                        System.out.println("클라이언트를 종료합니다.");
                        break;
                    }
                    outputStream.write((message + "\n").getBytes());
                    outputStream.flush();

                    byte[] buffer = new byte[1024];
                    int bytesRead = inputStream.read(buffer);
                    String echoMessage = new String(buffer, 0, bytesRead);
                    System.out.println("서버 응답: " + echoMessage);

                }
            }
        } catch (Exception e) {
            System.err.println("클라이언트 오류:" + e.getMessage());
        }

    }
}
