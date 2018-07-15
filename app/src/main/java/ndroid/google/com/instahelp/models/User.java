package ndroid.google.com.instahelp.models;

public class User {
    public String uid;
    public String email;
    public String name;
    public String firebaseToken;

    public User(){

    }

    public String getName() {
        return name;
    }

    public String getUid() {
        return uid;
    }

    public String getFirebaseToken() {
        return firebaseToken;
    }

    public String getEmail() {
        return email;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setFirebaseToken(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String uid, String email, String firebaseToken){
        this.uid = uid;
        this.email = email;
        this.firebaseToken = firebaseToken;
    }
}