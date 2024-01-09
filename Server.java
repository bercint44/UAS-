import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            // Buat BufferedReader untuk membaca input dari pengguna
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            // Meminta pengguna untuk memasukkan port
            System.out.print("Masukkan port untuk server: ");
            int port = Integer.parseInt(userInput.readLine());

            // Buat server socket dengan port yang ditentukan pengguna
            ServerSocket serverSocket = new ServerSocket(port);

            System.out.println("Server berjalan, menunggu koneksi...");
            
            // Terima koneksi dari client
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client terhubung!");

            // Buat input dan output stream untuk berkomunikasi dengan client
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String command;
            while ((command = in.readLine()) != null) {
                System.out.println("Perintah : " + command);

                // Eksekusi perintah CMD dan kirim output ke client
                executeCommand(command, out);
            }
