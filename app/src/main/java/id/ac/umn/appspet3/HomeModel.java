package id.ac.umn.appspet3;

public class HomeModel {

    String pemilik, type, bio, alamat, image;
    Long notelp;

    HomeModel(){

    }
    public HomeModel(String pemilik, String type, String bio, String alamat, long notelp, String image){
        this.pemilik = pemilik;
        this.type = type;
        this.bio = bio;
        this.alamat = alamat;
        this.notelp = notelp;
        this.image = image;
    }

    public String getPemilik() {
        return pemilik;
    }

    public void setPemilik(String pemilik) {
        this.pemilik = pemilik;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Long getNotelp() {
        return notelp;
    }

    public void setNotelp(Long notelp) {
        this.notelp = notelp;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
