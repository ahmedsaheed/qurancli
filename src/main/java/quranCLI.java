import javazoom.jl.player.Player;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.Callable;


@Command(name = "quranCLI", mixinStandardHelpOptions = true, version = "quranCLI 1.0",
        footer = "\nCopyright(c) quranCLI 2022",
        description = "A simple tool to Read, Search and Recite the Quran\n")
class quranCLI implements Callable<Integer> {
    /*
    TO REBUILD
    javac -cp /Users/ahmedsaheed/.gradle/caches/modules-2/files-2.1/info.picocli/picocli/4.6.1/49a67ee4b4d9722fa60f3f9ffaffa72861c32966/picocli-4.6.1.jar:/Users/ahmedsaheed/.gradle/caches/modules-2/files-2.1/org.apache.commons/commons-lang3/3.12.0/c6842c86792ff03b9f1d1fe2aab8dc23aa6c6f0e/commons-lang3-3.12.0.jar:/Users/ahmedsaheed/.gradle/caches/modules-2/files-2.1/javazoom/jlayer/1.0.1/2bfef7a5a4c9af2184ff74b460b6d7d24349b98a/jlayer-1.0.1.jar:/Users/ahmedsaheed/.gradle/caches/modules-2/files-2.1/com.squareup.okio/okio-jvm/3.0.0/ab5a73fa2ccb4a36b0b5c69fe10b16d0255bcf8/okio-jvm-3.0.0.jar:/Users/ahmedsaheed/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib/1.7.0/a5f42c684ad9003160ef0d0f693ecf0ba7b13549/kotlin-stdlib-1.7.0.jar:/Users/ahmedsaheed/.gradle/caches/modules-2/files-2.1/com.squareup.okhttp3/okhttp/4.10.0/cd63657ac15770ed1420647154c9f44645533bef/okhttp-4.10.0.jar:/Users/ahmedsaheed/.gradle/caches/modules-2/files-2.1/org.json/json/20220320/6df2c050972619466f6dcef7654ef9bcc01dfd0/json-20220320.jar -d /Users/ahmedsaheed/Desktop/Desktop/cmdWithJava/classes /Users/ahmedsaheed/Desktop/Desktop/cmdWithJava/src/main/java/quranCLI.java
    TO EXECUTE
    java -cp .:/Users/ahmedsaheed/.gradle/caches/modules-2/files-2.1/info.picocli/picocli/4.6.1/49a67ee4b4d9722fa60f3f9ffaffa72861c32966/picocli-4.6.1.jar:/Users/ahmedsaheed/.gradle/caches/modules-2/files-2.1/org.apache.commons/commons-lang3/3.12.0/c6842c86792ff03b9f1d1fe2aab8dc23aa6c6f0e/commons-lang3-3.12.0.jar:/Users/ahmedsaheed/.gradle/caches/modules-2/files-2.1/javazoom/jlayer/1.0.1/2bfef7a5a4c9af2184ff74b460b6d7d24349b98a/jlayer-1.0.1.jar:/Users/ahmedsaheed/.gradle/caches/modules-2/files-2.1/com.squareup.okio/okio-jvm/3.0.0/ab5a73fa2ccb4a36b0b5c69fe10b16d0255bcf8/okio-jvm-3.0.0.jar:/Users/ahmedsaheed/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib/1.7.0/a5f42c684ad9003160ef0d0f693ecf0ba7b13549/kotlin-stdlib-1.7.0.jar:/Users/ahmedsaheed/.gradle/caches/modules-2/files-2.1/com.squareup.okhttp3/okhttp/4.10.0/cd63657ac15770ed1420647154c9f44645533bef/okhttp-4.10.0.jar:/Users/ahmedsaheed/.gradle/caches/modules-2/files-2.1/org.json/json/20220320/6df2c050972619466f6dcef7654ef9bcc01dfd0/json-20220320.jar /Users/ahmedsaheed/Desktop/Desktop/cmdWithJava/src/main/java/quranCLI.java
     */

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
    protected String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }
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

    static String GetJuz(int Juz) throws IOException {
        StringBuilder juz = new StringBuilder();
        StringBuilder juzz = new StringBuilder();
        ArrayList<String> Sura = new ArrayList<String>();
        String url = "http://api.alquran.cloud/v1/juz/" + Juz;
        String responses = makeRequest(url);
        JSONObject jsonObject = new JSONObject(responses);
        JSONObject data = jsonObject.getJSONObject("data");
        JSONArray surahs = data.getJSONArray("ayahs");

        for(int i = 0; i < surahs.length(); i++) {
            JSONObject surah = surahs.getJSONObject(i);
            JSONObject surah_metadata = surah.getJSONObject("surah");
            String name = surah_metadata.getString("name");
            if(!Sura.contains(name)) {
                Sura.add(name);
            }
            juz.append(surah.getString("text")).append("\n");
        }
        juzz.append("Juz number " +Juz+" contains ").append(Sura.size() + 1).append(" Suras\n");
        for (String s : Sura) {
            juzz.append(s).append(" | ");
        }
        juzz.append("\n\n" + juz.toString());

        return juzz.toString();
    }

    public static String GetSurah(int surahNumber) throws IOException {
        String url = "http://api.alquran.cloud/v1/surah/" + surahNumber;
        StringBuilder verses = new StringBuilder();
        try {
            JSONObject jsonObject = new JSONObject(makeRequest(url));
            JSONObject data = jsonObject.getJSONObject("data");
            //GET SURAH ARABIC NAME
            String name = data.getString("name");
            //verses.append(name).append("\n");
            int w = 20;

            System.out.println(StringUtils.rightPad("+", w - 1, "-") + "+");
            System.out.println(StringUtils.center(StringUtils.center(name, w - 2), w, "|"));
            System.out.println(StringUtils.rightPad("+", w - 1, "-") + "+");
            System.out.println("\n");
            //GET THE ARABIC TEXT OF THE SURAH
            JSONArray ayahs = data.getJSONArray("ayahs");
            for (int i = 0; i < ayahs.length(); i++) {
                JSONObject ayah = ayahs.getJSONObject(i);
                String text = ayah.getString("text");
//                System.out.println(text);
                verses.append(text).append("\n\n");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        int width = 50;
        System.out.println(StringUtils.rightPad("+", width - 1, "-") + "+");
        System.out.println(StringUtils.center(StringUtils.center(verses.toString(), width - 2), width, "|"));
        System.out.println(StringUtils.rightPad("+", width - 1, "-") + "+");
        return verses.toString();
    }

    public static String getAudio(int surahNumber) throws IOException {
        String url = "https://api.alquran.cloud/v1/surah/"+surahNumber+"/ar.alafasy";
        String responses = makeRequest(url);
        ArrayList<String> Audiourl = new ArrayList<String>();
        GetSurah(surahNumber);

        try{
            JSONObject jsonObject = new JSONObject(responses);
            JSONObject data = jsonObject.getJSONObject("data");
            JSONArray ayah = data.getJSONArray("ayahs");
            for(int i = 0; i < ayah.length(); i++) {
                JSONObject ayah_data = ayah.getJSONObject(i);
                String audio = ayah_data.getString("audio");
                Audiourl.add(audio);
                playAudio(audio);

            }
        }catch (Exception e) {
            System.out.println(e);
        }


        return responses;
    }

    public record search (String ArabicName, String EnglishName, String FoundText) {}


    public static ArrayList search(String keyword) throws IOException {

        int sum = 0;
        ArrayList<search> Matches = new ArrayList<search>();
        String url = "http://api.alquran.cloud/v1/search/"+keyword+"/all/en";
        String response = makeRequest(url);
        JSONObject jsonObject = new JSONObject(response);
        JSONObject data = jsonObject.getJSONObject("data");
        JSONArray matched = data.getJSONArray("matches");
        for(int i = 0; i < matched.length(); i++) {
            JSONObject surah = matched.getJSONObject(i);
            JSONObject surah_metadata = surah.getJSONObject("surah");
            String name = surah_metadata.getString("name");
            String Englishname = surah_metadata.getString("englishName");
            String text = surah.getString("text");
            int wordCount = surah.getInt("numberInSurah");
//             System.out.println(name);
//             System.out.println(Englishname);
//             System.out.println(text);
//             System.out.println(wordCount);
//             sum += wordCount;
//             System.out.println(sum);
            Matches.add(new search(name, Englishname, text));

        }

        return Matches;
    }

    public static void playAudio(String path) {

        try {
            URL url = new URL(path);
            BufferedInputStream BIS = new BufferedInputStream(url.openStream());

            Player player = new Player(BIS);
            player.play();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Option(names = {"-s", "--surah-number"}, description = "find a surah by it's number in range 1..114")
    private int surahNumber;

    @Option(names = {"-q", "--query"}, description = "Search a keyword in the quran")
    private String query;
    @Option(names = {"-a", "--audio"}, description = "plays audio version of surah")
    boolean a;



    @Override
    public Integer call() throws Exception { // your business logic goes here...

        if(a) {
            getAudio(surahNumber);
        }else if(query != null) {
            System.out.println(search(query).toString());
        }else{
            if (surahNumber < 1 || surahNumber > 114) {
                System.out.println("Opps ! The Quran has 114 Surahs.\nUse \"quranCLI [command] --help\" for more information about a command.");
                return 0;
            }
            GetSurah(surahNumber);
        }
        return 0;
    }

    static CommandLine.Help.ColorScheme colorScheme = new CommandLine.Help.ColorScheme.Builder()
            .commands    (CommandLine.Help.Ansi.Style.bold, CommandLine.Help.Ansi.Style.underline)    // combine multiple styles
            .options     (CommandLine.Help.Ansi.Style.fg_yellow)                // yellow foreground color
            .parameters  (CommandLine.Help.Ansi.Style.fg_yellow)
            .optionParams(CommandLine.Help.Ansi.Style.italic)
            .errors      (CommandLine.Help.Ansi.Style.fg_red, CommandLine.Help.Ansi.Style.bold)
            .stackTraces (CommandLine.Help.Ansi.Style.italic)
            .applySystemProperties() // optional: allow end users to customize
            .build();

//CommandLine.usage(annotatedObject, System.out, colorScheme);


    // this example implements Callable, so parsing, error handling and handling user
    // requests for usage help or version help can be done with one line of code.
    public static void main(String... args) {
        int exitCode = new CommandLine(new quranCLI()).setColorScheme(colorScheme).execute(args);
//        System.exit(exitCode);
    }
}
