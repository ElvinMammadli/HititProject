/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author azelv
 */
public class Main  {
    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) throws IOException, InterruptedException {
        String filename="HititProject.txt";
        char stop='a';
        String inline;
        List<User> users ;
        File f = new File(filename);
        if(f.exists() && !f.isDirectory())
            f.delete();
        ApiProcess api = new ApiProcess();
        users = api.getMostDownloadedRepo();
        for (User user : users) {
            //create string which included user informations
            inline = "repo:" + user.getProject() + " user:" + user.getUserName() + " location:" + user.getLocation() + " company:" + user.getCompany() +
                    " contributions:" + user.getContributions();
            try (FileWriter myWriter = new FileWriter(filename, true);
                 BufferedWriter bufferedWriter = new BufferedWriter(myWriter);
                 PrintWriter out = new PrintWriter(bufferedWriter)) {
                out.println(inline);
            } catch (IOException e) {
                System.out.println("Writer error Error!");
                e.printStackTrace();
            }

        }
        System.out.println("Press Q for quit");
        while( stop!='Q') {
            Scanner reader = new Scanner(System.in);
            stop = reader.next().charAt(0);

        }


    }
}