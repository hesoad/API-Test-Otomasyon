import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

public class C10_JsonPathKullanimi {


        @Test
        public void method01(){
            JSONObject kisiBilgleriJsonObj=new JSONObject();
            JSONObject adresjsonObject =new JSONObject();

            JSONArray telefonBilgileriarray=new JSONArray();
            JSONObject cepTelefonujsonObject=new JSONObject();
            JSONObject evTelefonujsonObject=new JSONObject();



            adresjsonObject.put("streetAddres","naist stree");
            adresjsonObject.put("city","Nara");
            adresjsonObject.put("postalCode","630-0192");

            cepTelefonujsonObject.put("type","iphone");
            cepTelefonujsonObject.put("number","0123-4567-8888");

            evTelefonujsonObject.put("type","home");
            evTelefonujsonObject.put("number","0123456789000");

            telefonBilgileriarray.put(cepTelefonujsonObject);
            telefonBilgileriarray.put(evTelefonujsonObject);

            kisiBilgleriJsonObj.put("firstname","John");
            kisiBilgleriJsonObj.put("lastname","Doe");
            kisiBilgleriJsonObj.put("age",26);
            kisiBilgleriJsonObj.put("address",adresjsonObject);
            kisiBilgleriJsonObj.put("phoneNumbers",telefonBilgileriarray);

            System.out.println(kisiBilgleriJsonObj);
            System.out.println("lastname :"+kisiBilgleriJsonObj.get("lastname"));
            System.out.println("firstname :"+kisiBilgleriJsonObj.get("firstname"));
            System.out.println("Cadde : "+ kisiBilgleriJsonObj.getJSONObject("address").get("streetAddres"));

            System.out.println("Sehir : "+ kisiBilgleriJsonObj.getJSONObject("address").get("city"));

            System.out.println("Cep Telefonu Numarasi : "
                    + kisiBilgleriJsonObj
                    .getJSONArray("phoneNumbers")
                    .getJSONObject(0).get("number"));
        }
}
