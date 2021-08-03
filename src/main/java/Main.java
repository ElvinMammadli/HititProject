/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author azelv
 */
public class Main  {
    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) throws IOException {
        String inline;
        List<User> users = new ArrayList<>();
        ApiProcess api = new ApiProcess();
        users = api.getMostDownloadedRepo();
        for (User user : users) {
            inline = "repo:" + user.getProject() + " user:" + user.getUserName() + " location:" + user.getLocation() + " company:" + user.getCompany() +
                    " contributions:" + user.getContributions();
            System.out.println(inline);
            try (FileWriter myWriter = new FileWriter("HititProject.txt", true);
                 BufferedWriter bufferedWriter = new BufferedWriter(myWriter);
                 PrintWriter out = new PrintWriter(bufferedWriter)) {
                out.println(inline);
            } catch (IOException e) {
                System.out.println("Writer error Error!");
                e.printStackTrace();
            }
        }


    }
}