import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.IOException;
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

    protected static String makeRequest(String url) throws IOException {
        String responses = "";
        try {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
            Response response = client.newCall(request).execute();
            responses = response.body().string();
        } catch (Exception e) {
            System.out.println(e);
        }
        return responses;
    }

    static String GetJuz(int Juz) {
        String responses = "";

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://api.alquran.cloud/v1/juz/" + Juz)
                    .build();
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
            responses = response.body().string();
        } catch (Exception e) {
            System.out.println(e);
        }

        return responses;
    }

    public static String GetSurah(int surahNumber) throws IOException {
        String url = "http://api.alquran.cloud/v1/surah/" + surahNumber;
        StringBuilder verses = new StringBuilder();
        try {
            JSONObject jsonObject = new JSONObject(makeRequest(url));
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

     public static void main(String[] args) throws IOException {
        //GetFullQuran();
        //System.out.println(GetJuz(1, "quran-uthmani"));
        //System.out.println(GetSurah(1));
        System.out.println(GetSurah(1));
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
        System.out.println(getQuran.GetSurah(surahNumber));
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