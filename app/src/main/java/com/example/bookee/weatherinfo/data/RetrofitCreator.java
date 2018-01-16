package com.example.bookee.weatherinfo.data;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCreator {
    //todo Kada ovako zakomentarises neku konstantu ili kod, obavezno napisi zasto ga nisi obrisao i napisi datum kada si ga zakomentarisao. Desice se da niko nece da obrise to jer misle da je jos potrebno, a ti ga ne koristis vec 2 godine. Trust me....
   // private static final String BASE_URL_PRODUCTION = "http://samples.openweathermap.org/data/2.5/";
    private static final  String BASE_URL_LIVE = "http://api.openweathermap.org/data/2.5/";

    private static final String API_KEY = "e201d03b0ded25f6ba5dc1fa35f016ea";
  //  private static final String API_KEY_2 = "b10b5cef962cf5f44e3a4880429d9c45";

    /*todo imas dosta mesta gde imas po nekoliko linija izmedju delova koda. kao ovde.
    Neko je pravilo da se:
        - srodne promenljive pisu jedna ispod druge
        - ako ima potrebe, ubaci liniju izmedju raznorodnih promenljivih
        - izmedju metoda samo jedna ili linija

        Na primer:

        private int counter;
        private String label;

        private SomeObject obj;
        private SomeAnotherClass another;

        public Ctor(){
            //some content
        }

        public void someMethod(){
            //some content
        }




*/
   static  Retrofit createRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL_LIVE)
                .addConverterFactory(GsonConverterFactory.create());
        return builder.build();
    }

    public static String getApiKey() {

        return  API_KEY;
    }
}
