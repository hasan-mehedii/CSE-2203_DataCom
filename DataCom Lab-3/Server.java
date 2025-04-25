import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static String bitDeStuffing(String stuffedString) {
        StringBuilder destuffed = new StringBuilder();
        int count = 0;

        for (int i = 0; i < stuffedString.length(); i++) {
            char bit = stuffedString.charAt(i);

            if (bit == '1') {
                count++;
                destuffed.append(bit);
            }
            else if (bit == '0') {
                if (count == 5) {
                    count = 0;
                } else {
                    destuffed.append(bit);
                    count = 0;
                }
            }
            if (count == 5) {
                count = 0;
                i++;
            }
        }
        return destuffed.toString();
    }

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(5000);
        System.out.println("Server is connected at port no: " + ss.getLocalPort());
        System.out.println("Server is connecting\n");
        System.out.println("Waiting for the client\n");

        Socket s = ss.accept();
        System.out.println("Client request is accepted at port no: " + s.getPort());

        System.out.println("Server’s Communication Port: " + s.getLocalPort());

        DataInputStream input = new DataInputStream(s.getInputStream());
        DataOutputStream output = new DataOutputStream(s.getOutputStream());

        String str = "";
        while (true) {
            str = input.readUTF();
            if (str.equals("stop")) {
                System.out.println("Server is stopping the communication.");
                break;
            }
            System.out.println("Client Says (Stuffed String): " + str);

            String destuffedString = bitDeStuffing(str);
            System.out.println("De-stuffed String: " + destuffedString);
            output.writeUTF(destuffedString);
        }

        s.close();
        ss.close();
        input.close();
        output.close();
    }
}
