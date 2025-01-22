
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class EchoServer {

    private static final int SERVER_PORT = 9999;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            System.out.println(SERVER_PORT + "포트 Echo Server Running...");

            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    System.out.println(LocalDateTime.now() + " :  연결됨" + socket);
                    socket.getInputStream().transferTo(socket.getOutputStream());
                } catch (IOException e) {
                    System.err.println("클라이언트 처리 중 오류 : " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("서버 초기화 오류: " + e.getMessage());
        }

    }

}
