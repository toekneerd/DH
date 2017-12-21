package diffie.helman;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
import static diffie.helman.DiffieHelman.getInt;
import static diffie.helman.DiffieHelman.power;
import java.io.*;
import java.net.*;
import java.util.Scanner;
/**
 *
 * @author alvaro
 */
public class Server 
{   
    static ServerSocket ss;
    static Socket s;
    //static DataInputStream datain;
    //static DataOutputStream dataout;
    
    public static void main(String args[])throws Exception
    {
     
        Server srv =  new Server();
        srv.run();
    }
    public void run()throws Exception
    {
        Scanner scan = new Scanner(System.in);
        
        ss = new ServerSocket(1201);
        s = ss.accept();
        
        InputStreamReader in = new InputStreamReader(s.getInputStream());
        PrintStream out = new PrintStream(s.getOutputStream());
        BufferedReader read = new BufferedReader(in);
        
        
        String recvQ = read.readLine();
        System.out.println("Server recieved q: " + recvQ);
        String recvAlpha = read.readLine();
        System.out.println("Server recieved alpha: " + recvAlpha);
        String recvAPubKey = read.readLine();
        System.out.println("Server recieved A's public key Y(A): " + recvAPubKey);
        String recvEmail = read.readLine();
        System.out.println("Email received: "+ recvEmail);
        
        
        
        System.out.println("Enter your private key X(B):");
        String BPrivKey = scan.next();
        int BPrivKeyInt = Integer.parseInt(BPrivKey);
        
        int intPubKeyRecv = Integer.parseInt(recvAPubKey);
        int intAlphaRecv = Integer.parseInt(recvAlpha);
        int intQRecv = Integer.parseInt(recvQ);
        int BPubKey = power(intAlphaRecv,BPrivKeyInt) % intQRecv;
        System.out.println("B's public key Y(B): " +BPubKey);
        
        String serverEmail = "DHServer@gmail.com";
        System.out.println("Sending public key Y(B) and email to client");
        out.println(BPubKey);
        out.println(serverEmail);
        
        int sharedPrivKey = power(intPubKeyRecv,BPrivKeyInt) % intQRecv;
        System.out.println("Shared private key: "+sharedPrivKey);
        
        s.close();
    }
    
}