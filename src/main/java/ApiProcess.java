/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author azelv
 */

public class ApiProcess {



    public int ConnectToApi(URL url)  {
        int responsecode = 0;
        try {

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            //Github Limitini artirmak icin gerekli olan ayarlamalar
            String encoded = Base64.getEncoder().encodeToString(("m3hkt1"+":"+"mekhti11").getBytes(StandardCharsets.UTF_8));  //Java 8
            conn.setRequestProperty("Authorization", "Basic "+encoded);
            conn.connect();
            //Getting the response code
             responsecode = conn.getResponseCode();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return responsecode;
    } 
    //Get Most Download Repo function return Arraylist which includes most download repositories
    public List<User> getMostDownloadedRepo() throws InterruptedException, MalformedURLException {
        //I choose 5 repositories by myself  
        List<String> mostDownloadedRepo= new ArrayList<>(Arrays.asList("dubbo", "kafka", "skywalking","echarts","spark"));
        return getMostCommitUsers(mostDownloadedRepo);
    }
    
    public List<User> getMostCommitUsers(List<String> mostDownloadedRepo ) throws InterruptedException, MalformedURLException {
        List<User> users = new ArrayList<>();
        URL url;
        Scanner scanner = null;
        String repoName;
        //Get all Repositories most Commit Users
        try {
            for (int j = 0; j < 5; j++) {
                repoName = mostDownloadedRepo.get(j);
                url = new URL("https://api.github.com/repos/" + "apache" + "/" + repoName + "/contributors?sort=commit");
                int responseCode = ConnectToApi(url);
                    String inline = "";
                    scanner = new Scanner(url.openStream());
                    while (scanner.hasNext()) {
                        inline += scanner.nextLine();
                    }
                    JSONParser parse = new JSONParser();
                    Object obj = parse.parse(inline);
                    JSONArray jsonarray = (JSONArray) obj;
                    for (int i = 0; i < 10; i++) {
                        JSONObject jsonObject = (JSONObject) jsonarray.get(i);
                        User user = new User();
                        user.setProject(repoName);
                        user.setContributions((int) ((Number) jsonObject.get("contributions")).longValue());
                        user.setUserName((String) jsonObject.get("login"));
                        users.add(user);
                    }
                    scanner.close();
                }
        }catch(Exception e){
            System.out.println("Error at get Most commit Users");
            e.printStackTrace();
        }
        return getUserData(users);
    }
    public List<User> getUserData(List<User>users) throws InterruptedException {
        //Get user data from api and add company name and location to user class
        URL url;
        Scanner scanner = null;
        try {
            for (int i = 0; i < users.size(); i++) {
                url = new URL("https://api.github.com/users/" + users.get(i).getUserName());
                int responseCode = ConnectToApi(url);
                    String inline = "";
                    scanner = new Scanner(url.openStream());
                    while (scanner.hasNext()) {
                        inline += scanner.nextLine();
                    }
                    System.out.println(inline);
                    JSONParser parse = new JSONParser();
                    Object obj = parse.parse(inline);
                    JSONObject jsonObject = (JSONObject) obj;
                    users.get(i).setCompany((String) jsonObject.get("company"));
                    users.get(i).setLocation((String) jsonObject.get("location"));
                }
                scanner.close();


        }
        catch (Exception e){
            System.out.println("Error at getUserData");
            e.printStackTrace();
        }
        return users;
    }
    
}
