//package Quran;
//
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//import org.json.*;
//
//
//public class getQuran {
//    static void GetFullQuran(){
//
//        try{
//            OkHttpClient client = new OkHttpClient();
//            Request request = new Request.Builder()
//                    .url("https://api.alquran.cloud/v1/quran/quran-uthmani")
//                    .build();
//            Response response = client.newCall(request).execute();
//            System.out.println(response.body().string());
//        }catch (Exception e){
//            System.out.println(e);
//        }
//
//    }
//    static String GetJuz(int Juz, String edition){
//        String responses = "";
//
//        try{
//            OkHttpClient client = new OkHttpClient();
//            Request request = new Request.Builder()
//                    .url("http://api.alquran.cloud/v1/juz/" + Juz + "/" + edition)
//                    .build();
//            Response response = client.newCall(request).execute();
//            System.out.println(response.body().string());
//            responses = response.body().string();
//            System.out.printf("\u0627\u0644\u0642\u0631\u0622\u0646 \u0627\u0644\u0643\u0631\u064a\u0645 \u0628\u0631\u0633\u0645 \u0627\u0644\u0639\u062b\u0645\u0627\u0646\u064a");
//        }catch (Exception e){
//            System.out.println(e);
//        }
//
//        return  responses;
//    }
//
//    public static String GetSurah(int surahNumber){
//        String responses = "";
//
//        try{
//            OkHttpClient client = new OkHttpClient();
//            Request request = new Request.Builder()
//                    .url("http://api.alquran.cloud/v1/surah/"+ surahNumber)
//                    .build();
//            Response response = client.newCall(request).execute();
//            //System.out.println(response.body().string());
//            responses = response.body().string();
//        }catch (Exception e){
//            System.out.println(e);
//        }
//
//        return  responses;
//    }
//    public static String ConvertSurahResponse(String response){
//        StringBuilder verses = new StringBuilder();
//        try{
//            JSONObject jsonObject = new JSONObject(response);
//            JSONObject data = jsonObject.getJSONObject("data");
//            //GET SURAH ARABIC NAME
//            String name = data.getString("name");
//             //GET THE ARABIC TEXT OF THE SURAH
//            JSONArray ayahs = data.getJSONArray("ayahs");
//            for (int i = 0; i < ayahs.length(); i++) {
//                JSONObject ayah = ayahs.getJSONObject(i);
//                String text = ayah.getString("text");
////                System.out.println(text);
//                verses.append(text).append("\n");
//            }
//
//        }catch (Exception e){
//            System.out.println(e);
//        }
//        return verses.toString();
//    }
//
//
//
//    public static void main(String[] args) {
//    //    getQuran.GetFullQuran();
//       // getQuran.GetJuz(30, "quran-uthmani");
//        System.out.println(getQuran.ConvertSurahResponse(getQuran.GetSurah(2)));
//        getQuran.ConvertSurahResponse(getQuran.GetSurah(2));
//    }
//}
//
