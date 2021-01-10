package com.aaronevan.binusezyfoody.helper;

public class Database {
    public static final String Database = "BinusEzy";
    public static final int databaseVersion = 1;

    //table user
    public static final String tableUser = "User";
    //isi table user
    public static final String IdUser = "UserId ";
    public static final String Saldo = "Saldo ";

    public static final String Make_Table_User() {
        return "CREATE TABLE " + tableUser + "( "
                + IdUser + "INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Saldo + "int "
                + ");";
    }

    //table resto
    public static final String tableResto = "Resto";
    //isi table resto
    public static final String IdResto = "RestoId";
    public static final String NamaResto = "NamaResto ";
    public static final String LongResto = "Long ";
    public static final String LatResto = "Lat ";

    public static final String Make_Table_Resto() {
        return "CREATE TABLE " + tableResto + "( "
                + IdResto + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NamaResto + "text, "
                + LongResto + "double, "
                + LatResto + "double "
                + ");";
    }

    //table product
    public static final String tableProduct = "Product";
    //isi table product
    public static final String IdProduct = "ProductId ";
    //    public static final String IdResto = "RestoId";
    public static final String NamaProduct = "Nama ";
    public static final String JenisProduct = "Jenis";
    public static final String ImageProduct = "Image ";
    public static final String HargaProduct = "Harga ";
    public static final String StockProduct = "Stock ";

    public static final String Make_Table_Product() {
        return "CREATE TABLE " + tableProduct + "( "
                + IdProduct + "INTEGER PRIMARY KEY AUTOINCREMENT, "
                + IdResto + " text, "
                + NamaProduct + "text, "
                + JenisProduct + " text, "
                + ImageProduct + "int, "
                + HargaProduct + "int, "
                + StockProduct + "int, "
                + "FOREIGN KEY " + "(" + IdResto + ") " + " REFERENCES " + tableResto + " (" + IdResto + ")"
                + ");";
    }

    //table transaksi
    public static final String tableTransaksi = "Transaksi";
    //isi table transaksi
    public static final String IdTransaction = "TransaksiId ";
    public static final String Alamat = "Alamat ";
    public static final String Tanggal = "Tanggal ";
    public static final String Total = "Total ";


    public static final String Make_Table_Transaksi() {
        return "CREATE TABLE " + tableTransaksi + "( "
                + IdTransaction + "INTEGER PRIMARY KEY AUTOINCREMENT, "
                + IdUser + "text, "
                + Alamat + "text, "
                + Tanggal + "text, "
                + Total + "text, "
                + "FOREIGN KEY " + "(" + IdUser + ") " + "REFERENCES " + tableUser + " (" + IdUser + ")"
                + ");";
    }

    //table transaksi
    public static final String tableDetail = "Detail ";
    //isi table transaksi
    public static final String IdDetail = "DetailId ";
    public static final String Quantity = "Quantity ";

    public static final String Make_Table_Detail() {
        return "CREATE TABLE " + tableDetail + "( "
                + IdDetail + "INTEGER PRIMARY KEY AUTOINCREMENT, "
                + IdTransaction + "text, "
                + IdProduct + "text, "
                + Quantity + "text, "
                + "FOREIGN KEY " + "(" + IdTransaction + ") " + "REFERENCES " + tableTransaksi + " (" + IdProduct + ")"
                + "FOREIGN KEY " + "(" + IdProduct + ") " + "REFERENCES " + tableProduct + " (" + IdProduct + ")"
                + ");";
    }

}
