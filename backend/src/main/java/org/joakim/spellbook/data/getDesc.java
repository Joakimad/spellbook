package org.joakim.spellbook.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class getDesc {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("backend/src/main/java/org/joakim/spellbook/data/names.csv"));
        String line;
        StringBuilder result = new StringBuilder();
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            line = line.replace(" ", "-").toLowerCase();
            String path = "https://www.dnd5eapi.co/api/spells/" + line;
            JSONObject obj = null;
            try {
                obj = readJsonFromUrl(path);
            } catch (Exception ignore) {

            }
            if (obj != null) {
                result.append(obj.getString("name")).append(";");
                JSONArray descArr = obj.getJSONArray("desc");
                String desc = descArr.getString(0).replace("\"","") + ";";
                result.append(desc);

                if (obj.has("higher_level")) {
                    JSONArray higherArr = obj.getJSONArray("higher_level");
                    String higher = higherArr.getString(0).replace("\"","");
                    result.append(higher);
                }
                result.append(";\n");
            }
            else {
                result.append("-\n");
                System.out.println("not found");
            }
        }
        FileWriter fw = new FileWriter("backend/src/main/java/org/joakim/spellbook/data/desc.csv");
        fw.write(result.toString());
        fw.close();


//        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//        conn.setRequestMethod("GET");
//        conn.connect();
//        int responsecode = conn.getResponseCode();
//        System.out.println(responsecode);
//        StringBuilder inline = new StringBuilder();
//        if(responsecode == 200) {
//            Scanner sc = new Scanner(url.openStream());
//            while(sc.hasNext())
//            {
//                inline.append(sc.nextLine());
//            }
//            System.out.println(inline);
//            sc.close();
//
//            System.out.println(inline);
//
//
//            FileWriter fw = new FileWriter("backend/src/java/org/joakim/spellbook/data/names.csv");
//            fw.write("test");
//            fw.close();
//        }
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
