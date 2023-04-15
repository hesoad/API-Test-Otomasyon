package pojos;

public class PojoJsonPlaceholder {
    /*
     Request Body
                {
                "title": "Ahmet",
                "body": "Merhaba",
                "userId": 10,
                "id": 70
                }
     */

    //1) Tum variable’lari "private" olarak olusturalim

    private String title;
    private String body;
    private int userId;
    private int id;

    public String getTitle() {
        return title;
    }
    //2) Tum variable’lar icin getter() ve  setter() metodlari olusturalim
    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
  //3) Tum parametreleri iceren bir constructor olusturalim.

    public PojoJsonPlaceholder(String title, String body, int userId, int id) {
        this.title = title;
        this.body = body;
        this.userId = userId;
        this.id = id;
    }
    //4) Default constructor (parametresiz) olusturalim

    public PojoJsonPlaceholder() {
    }

    //5) toString() metodu olusturalim

    @Override
    public String toString() {
        return "PojoJsonPlaceholder{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", userId=" + userId +
                ", id=" + id +
                '}';
    }
}
