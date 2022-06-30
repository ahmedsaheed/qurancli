/*
 * (C) Copyright 2022 quranCLI.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *     ...
 */

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciithemes.a8.A8_Grids;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import javazoom.jl.player.Player;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Help.Ansi;
import picocli.CommandLine.Option;

import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.Callable;


@Command(name = "quranCLI ", mixinStandardHelpOptions = true, version = "quranCLI 1.0",

        footer = "\nCopyright(c) quranCLI 2022",
        description = """
                      ______ ______
                    _/      Q      \\_
                   // ~~ ~~ | ~~ ~  \\\\
                  // ~ ~ ~~ | ~~~ ~~ \\\\
                 //________.|.________\\\\
                `----------`-'----------'
                       AL-QURAN CLI        \s
                A simple tool to Read, Search and Recite the Quran.
                """)
class quranCLI implements Callable<Integer> {

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


    public static void GetSurah(int surahNumber) throws IOException {
        SurahNumErr(surahNumber);
        String url = "https://api.alquran.cloud/v1/surah/" + surahNumber;
        StringBuilder verses = new StringBuilder();
        AsciiTable at = new AsciiTable();
        try {
            JSONObject jsonObject = new JSONObject(makeRequest(url));
            JSONObject data = jsonObject.getJSONObject("data");
            String name = data.getString("name");
            //BUILD CONTAINER FOR THE SURAH NAME
            at.addRule();
            at.addRow("SURAH_NAME: "+name).setTextAlignment(TextAlignment.CENTER);
            at.addRule();
            //BUILD CONTAINER FOR THE SURAH CONTENT
            JSONArray ayahs = data.getJSONArray("ayahs");
            for (int i = 0; i < ayahs.length(); i++) {
                JSONObject ayah = ayahs.getJSONObject(i);
                String text = ayah.getString("text");
                at.addRow(  text +"\n\n").setTextAlignment(TextAlignment.LEFT);
            }
            at.addRule();

        } catch (Exception e) {
            System.out.println(e);
        }

        at.getContext().setGrid(A8_Grids.lineDoubleBlocks());
        System.out.println(at.render(80));

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

    public static String getEnglishTranslation(int surahNumber){
        SurahNumErr(surahNumber);
        String url = "https://api.alquran.cloud/v1/surah/"+surahNumber+"/en.ahmedraza";
        AsciiTable at = new AsciiTable();
        try{
            JSONObject jsonObject = new JSONObject(makeRequest(url));
            JSONObject data = jsonObject.getJSONObject("data");
            String name = data.getString("englishName");
            at.addRule();
            at.addRow("English Name: "+name).setTextAlignment(TextAlignment.CENTER);
            at.addRule();
            JSONArray ayah = data.getJSONArray("ayahs");
            StringBuilder englishTranslation = new StringBuilder();
            for(int i = 0; i < ayah.length(); i++) {
                JSONObject ayah_data = ayah.getJSONObject(i);
                String text = ayah_data.getString("text");
                at.addRow(text).setTextAlignment(TextAlignment.LEFT);
                englishTranslation.append(text).append("\n\n");
            }
            at.addRule();
            System.out.println(at.render());
            return englishTranslation.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void SurahNumErr(int surahNumber) {
        if (surahNumber > 114 || surahNumber < 1) {
            System.out.println("Surah Number is not valid");
            AsciiTable err = new AsciiTable();
            err.addRule();
            err.addRow("Surah Number is not valid, try in range 1 to 114").setTextAlignment(TextAlignment.CENTER);
            err.addRule();
            System.out.println(err.render());
            System.exit(0);
        }
    }

    public static void getAudioTranslation(int surahNumber) throws IOException {
        String url = "https://api.alquran.cloud/v1/surah/"+surahNumber+"/en.walk";
        String responses = makeRequest(url);
        ArrayList<String> Audiourl = new ArrayList<String>();
        getEnglishTranslation(surahNumber);
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

    }
    public static void search(String keyword) throws IOException {

        String url = "http://api.alquran.cloud/v1/search/"+keyword+"/all/en";

        try {
            String response = makeRequest(url);
            JSONObject jsonObject = new JSONObject(response);
            JSONObject data = jsonObject.getJSONObject("data");
            JSONArray matched = data.getJSONArray("matches");
            AsciiTable at = new AsciiTable();
//            at.addRule();
//            at.addRow("Search Results").setTextAlignment(TextAlignment.CENTER);
            at.addRule();
            at.addRow("Surah Name","Ayat", "Found Text");
            at.addRule();
                for (int i = 0; i < matched.length(); i++) {
                    JSONObject surah = matched.getJSONObject(i);
                    JSONObject surah_metadata = surah.getJSONObject("surah");
                    String name = surah_metadata.getString("name");
                    String Englishname = surah_metadata.getString("englishName");
                    String text = surah.getString("text");
                    int ayat = surah.getInt("numberInSurah");
                    at.addRow(Englishname, ayat ,text);
                    at.addRule();

                }
            System.out.println(at.render());

            }catch(Exception e){
                AsciiTable err = new AsciiTable();
                err.addRule();
                err.addRow("No matches found for word `" +keyword +"`").setTextAlignment(TextAlignment.CENTER);
                err.addRule();
                System.out.println(err.render());
                System.exit(0);

            }
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

    @Option(names = {"-t", "--translation"}, description = "Translate surah to english")
    boolean t;

    @Option(names = {"-at", "--AudioTranslation"}, description = "Translate surah to english and play audio")
    boolean at;




    @Override
    public Integer call() throws Exception {

        if(a) {
            getAudio(surahNumber);
        }else if(t){
            getEnglishTranslation(surahNumber);
        }
        else if(at){
            getAudioTranslation(surahNumber);
        }
        else if(query != null) {
            search(query);
        }else {
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
        System.exit(exitCode);
    }
}
