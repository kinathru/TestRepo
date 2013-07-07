package fb;

import com.json.parsers.JSONParser;
import com.json.parsers.JsonParserFactory;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import javax.swing.JOptionPane;

public class Main {

    public static boolean isstop = false;

    public void startProcess() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                URL url;
                URLConnection urlcon;
                BufferedReader bf;
                String response = null;
                String value = "";
                String gender = "";
                String lastFetched = "";

                String temp = "";
                String prv = "na";
                BufferedReader br = null;
                FileWriter fw = null;
                BufferedWriter bw = null;

                //file paths--------
                String source = Settings.sourcepath;
                String output = Settings.destinationpath;

                //json----------
                JsonParserFactory factory = JsonParserFactory.getInstance();
                JSONParser parser = factory.newJsonParser();
                //json-------

                try {
                    br = new BufferedReader(new FileReader(source));
                    fw = new FileWriter(output);
                    bw = new BufferedWriter(fw);
                } catch (Exception z) {
                    System.out.println("HERE");
                }

                int counter = 0;
                int total = 0;
                try {

                    while ((temp = br.readLine()) != null) {
                        ++total;
                        Settings.totalnuber.setText(total + "");
                        try {

                            try {
                                url = new URL("http://graph.facebook.com/" + temp);
                                urlcon = url.openConnection();
                                Thread.sleep(1500);
                                bf = new BufferedReader(new InputStreamReader(urlcon.getInputStream()));
                                response = bf.readLine();
                                
                            } catch (Exception e) {
                                System.out.println("errrrrrrrrrrrrrr");
                            }

                            Map jsonData = parser.parseJson(response);
                            value = (String) jsonData.get("username");
                            gender = (String) jsonData.get("gender");
                            temp = value + "@facebook.com";
                            lastFetched = value;
                            if (Settings.genderbased) {

                                if ((gender != null) && (!gender.trim().equals("")) && gender.equals(Settings.Gender.toLowerCase())) {
                                    if (value != null) {
                                        bw.write(temp);
                                        bw.newLine();
                                        ++counter;
                                        Settings.lable.setText(counter + "");
                                    }
                                }

                            } else {
                                if (value != null) {
                                    bw.write(temp);
                                    bw.newLine();
                                    ++counter;
                                    Settings.lable.setText(counter + "");
                                }
                            }

                        } catch (IOException n) {

                            System.out.println("Errorz\n");
                            continue;
                        }



                        if (Main.isstop) {
                            Main.isstop = false;
                            break;
                        }

                    }

                    br.close();
                    bw.close();
                    fw.close();
                    if(!lastFetched.trim().equals("")){
                        lastFetched = "Last Fetched : "+lastFetched;
                    }
                    Settings.lastfetchedname.setText(lastFetched.trim());

                } catch (Exception z) {
                    System.out.println("Error\n" + z.toString());

                }
                JOptionPane.showMessageDialog(null, "Finish Fetching Data, Number of results " + counter);
                Thread.interrupted();
            }
        });

        t.start();


    }
}
