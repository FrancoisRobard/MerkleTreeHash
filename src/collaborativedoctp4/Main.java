/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collaborativedoctp4;

import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.JFrame;

/**
 *
 * @author franc
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        buildMerkleTree();
    }
    
    public static String loadFile(){
        JFrame fenetre = new JFrame();
            FileDialog fd = new FileDialog(fenetre, "Choisissez un fichier txt à charger :", FileDialog.LOAD);
            fd.setDirectory("C:\\");
            fd.setFile("*.txt");
            fd.setVisible(true);
            fd.setAlwaysOnTop(true);
            String filename = fd.getFile();
            if(filename == null){
                System.out.print("Vous avez annulé la sélection.");
                System.exit(1);
            }
            else{
                System.out.println("Vous avez choisi : "+fd.getDirectory()+filename);
            }
         return fd.getDirectory()+filename;
    }
    
    private static MerkleTree buildMerkleTree(){
        try{
            String filePath = loadFile();
            InputStream ips=new FileInputStream(filePath); // Open file
            InputStreamReader ipsr=new InputStreamReader(ips); // Open lecture mode
            BufferedReader br=new BufferedReader(ipsr); // Open buffered reader
            String line; // Variable that will contain lines content
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return null;
    }
    
}
