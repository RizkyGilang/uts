package ac.id.uts.models;

public class transaksi {

    private String nama;
    private String hargab;
    private String uang;
    private String kembalian;


    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHargab() {
        return hargab;
    }

    public void setHargab(String hargab) {
        this.hargab = hargab;
    }

    public String getUang() {
        return uang;
    }

    public void setUang(String uang) {
        this.uang = uang;
    }

    public String getKembalian() {
        return kembalian;
    }

    public void setKembalian(String kembalian) {
        this.kembalian = kembalian;
    }

    public transaksi(){

    }
    public String toString() {
        return " "+nama+"\n" +
                " "+hargab +"\n" +
                " "+uang +"\n" +
                " "+kembalian;
    }

    public transaksi(String nm, String hb, String u, String kem){
        nama = nm;
        hargab = hb;
        uang = u;
        kembalian = kem;

    }
}
