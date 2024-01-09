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
