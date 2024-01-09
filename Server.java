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
           
            //Membuat objek PrintWriter untuk menulis data ke soket klien 
            //(clientSocket.getOutputStream()) dengan opsi autoflush diaktifkan.
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            //Deklarasi variabel command sebagai string
            String command;
            // selama perintah tidak null (kosong) maka 
            //cetak perintah yang dikirimkan dari client
            while ((command = in.readLine()) != null) {
                System.out.println("Perintah : " + command);
            
                // Eksekusi perintah CMD dan kirim output ke client
                executeCommand(command, out);
            }

            // Tutup koneksi dan resources
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     // Deklarasi fungsi untuk mengeksekusi perintah
private static void executeCommand(String command, PrintWriter out) {
    try {
        // Membuat objek ProcessBuilder untuk eksekusi perintah
        ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command);
        processBuilder.redirectErrorStream(true);

        // Memulai proses eksekusi perintah
        Process process = processBuilder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        // Membaca dan mencetak keluaran perintah
        String line;

        //selama perintah terbaca tidak kosong maka tetap jalankan
        while ((line = reader.readLine()) != null) {
            out.println(line);
        }

        // Menunggu proses eksekusi perintah selesai
        process.waitFor();
        
        // Mencetak pesan bahwa eksekusi perintah telah selesai
        out.println("Server: Eksekusi perintah selesai.");
    } catch (IOException | InterruptedException e) {
        // Menangani pengecualian dengan mencetak jejak tumpukan dan pesan kesalahan
        e.printStackTrace();
        out.println("Server: Gagal mengeksekusi perintah.");
    }
}
}
