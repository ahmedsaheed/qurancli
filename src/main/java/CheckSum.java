import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.concurrent.Callable;

 class getQuran {
    static void GetFullQuran() {

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.alquran.cloud/v1/quran/quran-uthmani")
                    .build();
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    static String GetJuz(int Juz, String edition) {
        String responses = "";

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://api.alquran.cloud/v1/juz/" + Juz + "/" + edition)
                    .build();
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
            responses = response.body().string();
            System.out.printf("\u0627\u0644\u0642\u0631\u0622\u0646 \u0627\u0644\u0643\u0631\u064a\u0645 \u0628\u0631\u0633\u0645 \u0627\u0644\u0639\u062b\u0645\u0627\u0646\u064a");
        } catch (Exception e) {
            System.out.println(e);
        }

        return responses;
    }

    public static String GetSurah(int surahNumber) {
        String responses = "";

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://api.alquran.cloud/v1/surah/" + surahNumber)
                    .build();
            Response response = client.newCall(request).execute();
            //System.out.println(response.body().string());
            responses = response.body().string();
        } catch (Exception e) {
            System.out.println(e);
        }

        return responses;
    }

    public static String ConvertSurahResponse(String response) {
        StringBuilder verses = new StringBuilder();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject data = jsonObject.getJSONObject("data");
            //GET SURAH ARABIC NAME
            String name = data.getString("name");
            verses.append(name).append("\n");
            //GET THE ARABIC TEXT OF THE SURAH
            JSONArray ayahs = data.getJSONArray("ayahs");
            for (int i = 0; i < ayahs.length(); i++) {
                JSONObject ayah = ayahs.getJSONObject(i);
                String text = ayah.getString("text");
//                System.out.println(text);
                verses.append(text).append("\n");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return verses.toString();

    }

     public static void main(String[] args) {
        //GetFullQuran();
        //System.out.println(GetJuz(1, "quran-uthmani"));
        //System.out.println(GetSurah(1));
        System.out.println(ConvertSurahResponse(GetSurah(1)));
     }
}
@Command(name = "Get Surah", mixinStandardHelpOptions = true, version = "checksum 4.0",
        description = "Returns the surah of the given number")
class CheckSum implements Callable<Integer> {

    @Option(names = {"-s", "--surahNumber"}, description = "The surah Number (114,2,1...)", interactive = true)
    private int surahNumber;

    @Override
    public Integer call() throws Exception { // your business logic goes here...
        if (surahNumber < 1 || surahNumber > 114) {
            System.out.println("Opps ! The Quran has 114 Surahs. Let's try again");
        }
        System.out.println(getQuran.ConvertSurahResponse(getQuran.GetSurah(surahNumber)));
        return 0;
    }


    // this example implements Callable, so parsing, error handling and handling user
    // requests for usage help or version help can be done with one line of code.
    public static void main(String... args) {
        int exitCode = new CommandLine(new CheckSum()).execute("-s");
//        System.exit(exitCode);
    }
}

















/*
*
*  java -cp /Users/ahmedsaheed/.gradle/caches/modules-2/files-2.1/info.picocli/picocli/4.6.1/49a67ee4b4d9722fa60f3f9ffaffa72861c32966/picocli-4.6.1.jar /Users/ahmedsaheed/Desktop/Desktop/cmdWithJava/src/main/java/CheckSum.java /Users/ahmedsaheed/Desktop/Desktop/cmdWithJava/hello.txt
 * */