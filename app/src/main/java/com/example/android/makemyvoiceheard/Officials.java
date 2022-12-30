package com.example.android.makemyvoiceheard;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName="officials")
public class Officials {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="officialsID")
    private Integer mOfficialsID;

    @ColumnInfo(name="senator1Name")
    private String mSenator1Name;

    @ColumnInfo(name="senator1URL")
    private String mSenator1URL;

    @ColumnInfo(name="senator1PhotoURL")
    private String mSenator1PhotoURL;

    @ColumnInfo(name="senator1AddressLine1")
    private String mSenator1AddressLine1;

    @ColumnInfo(name="senator1AddressLine2")
    private String mSenator1AddressLine2;

    @ColumnInfo(name="senator1Party")
    private String mSenator1Party;

    @ColumnInfo(name="senator1Phone")
    private String mSenator1Phone;

    @ColumnInfo(name="senator2Name")
    private String mSenator2Name;

    @ColumnInfo(name="senator2URL")
    private String mSenator2URL;

    @ColumnInfo(name="senator2PhotoURL")
    private String mSenator2PhotoURL;

    @ColumnInfo(name="senator2AddressLine1")
    private String mSenator2AddressLine1;

    @ColumnInfo(name="senator2AddressLine2")
    private String mSenator2AddressLine2;

    @ColumnInfo(name="senator2Party")
    private String mSenator2Party;

    @ColumnInfo(name="senator2Phone")
    private String mSenator2Phone;

    @ColumnInfo(name="representativeName")
    private String mRepresentativeName;

    @ColumnInfo(name="representativeURL")
    private String mRepresentativeURL;

    @ColumnInfo(name="representativePhotoURL")
    private String mRepresentativePhotoURL;

    @ColumnInfo(name="representativeAddressLine1")
    private String mRepresentativeAddressLine1;

    @ColumnInfo(name="representativeAddressLine2")
    private String mRepresentativeAddressLine2;

    @ColumnInfo(name="representativeParty")
    private String mRepresentativeParty;

    @ColumnInfo(name="representativePhone")
    private String mRepresentativePhone;


    public Officials(String senator1Name, String senator1URL, String senator1PhotoURL, String senator1AddressLine1,
                 String senator1AddressLine2, String senator1Party, String senator1Phone, String senator2Name,
                 String senator2URL, String senator2PhotoURL, String senator2AddressLine1,
                 String senator2AddressLine2, String senator2Party, String senator2Phone,
                 String representativeName, String representativeURL, String representativePhotoURL, String representativeAddressLine1,
                 String representativeAddressLine2, String representativeParty, String representativePhone) {
        mSenator1Name = senator1Name;
        mSenator1URL  = senator1URL;
        mSenator1PhotoURL = senator1PhotoURL;
        mSenator1AddressLine1 = senator1AddressLine1;
        mSenator1AddressLine2 = senator1AddressLine2;
        mSenator1Party = senator1Party;
        mSenator1Phone = senator1Phone;
        mSenator2Name = senator2Name;
        mSenator2URL  = senator2URL;
        mSenator2PhotoURL = senator2PhotoURL;
        mSenator2AddressLine1 = senator2AddressLine1;
        mSenator2AddressLine2 = senator2AddressLine2;
        mSenator2Party = senator2Party;
        mSenator2Phone = senator2Phone;
        mRepresentativeName = representativeName;
        mRepresentativeURL = representativeURL;
        mRepresentativePhotoURL = representativePhotoURL;
        mRepresentativeAddressLine1 = representativeAddressLine1;
        mRepresentativeAddressLine2 = representativeAddressLine2;
        mRepresentativeParty = representativeParty;
        mRepresentativePhone = representativePhone;
    }


    public Integer getOfficialsID() {
        return mOfficialsID;
    }

    public void setOfficialsID(Integer officialsID) {
        mOfficialsID = officialsID;
    }

    public String getSenator1Name() {
        return mSenator1Name;
    }

    public String getSenator1URL() {
        return mSenator1URL;
    }

    public String getSenator1PhotoURL() {
        return mSenator1PhotoURL;
    }

    public String getSenator1AddressLine1() {
        return mSenator1AddressLine1;
    }

    public String getSenator1AddressLine2() {
        return mSenator1AddressLine2;
    }

    public String getSenator1Party() {
        return mSenator1Party;
    }

    public String getSenator1Phone() {
        return mSenator1Phone;
    }

    public String getSenator2Name() {
        return mSenator2Name;
    }

    public String getSenator2URL() {
        return mSenator2URL;
    }

    public String getSenator2PhotoURL() {
        return mSenator2PhotoURL;
    }

    public String getSenator2AddressLine1() {
        return mSenator2AddressLine1;
    }

    public String getSenator2AddressLine2() {
        return mSenator2AddressLine2;
    }

    public String getSenator2Party() {
        return mSenator2Party;
    }

    public String getSenator2Phone() {
        return mSenator2Phone;
    }

    public String getRepresentativeName() {
        return mRepresentativeName;
    }

    public String getRepresentativeURL() {
        return mRepresentativeURL;
    }

    public String getRepresentativePhotoURL() {
        return mRepresentativePhotoURL;
    }

    public String getRepresentativeAddressLine1() {
        return mRepresentativeAddressLine1;
    }

    public String getRepresentativeAddressLine2() {
        return mRepresentativeAddressLine2;
    }

    public String getRepresentativeParty() {
        return mRepresentativeParty;
    }

    public String getRepresentativePhone() {
        return mRepresentativePhone;
    }

    public void setSenator1Name(String senator1Name) {
        mSenator1Name = senator1Name;
    }

    public void setSenator1URL(String senator1URL) {
        mSenator1URL = senator1URL;
    }

    public void setSenator1PhotoURL(String senator1PhotoURL) {
        mSenator1PhotoURL = senator1PhotoURL;
    }

    public void setSenator1AddressLine1(String senator1AddressLine1) {
        mSenator1AddressLine1 = senator1AddressLine1;
    }

    public void setSenator1AddressLine2(String senator1AddressLine2) {
        mSenator1AddressLine2 = senator1AddressLine2;
    }

    public void setSenator1Party(String senator1Party) {
        mSenator1Party = senator1Party;
    }

    public void setSenator2Name(String senator2Name) {
        mSenator2Name = senator2Name;
    }

    public void setSenator2URL(String senator2URL) {
        mSenator2URL = senator2URL;
    }

    public void setSenator2PhotoURL(String senator2PhotoURL) {
        mSenator2PhotoURL = senator2PhotoURL;
    }

    public void setSenator2AddressLine1(String senator2AddressLine1) {
        mSenator2AddressLine1 = senator2AddressLine1;
    }

    public void setSenator2AddressLine2(String senator2AddressLine2) {
        mSenator2AddressLine2 = senator2AddressLine2;
    }

    public void setSenator2Party(String senator2Party) {
        mSenator2Party = senator2Party;
    }

    public void setSenator2Phone(String senator2Phone) {
        mSenator2Phone = senator2Phone;
    }

    public void setRepresentativeName(String representativeName) {
        mRepresentativeName = representativeName;
    }

    public void setRepresentativeURL(String representativeURL) {
        mRepresentativeURL = representativeURL;
    }

    public void setRepresentativePhotoURL(String representativePhotoURL) {
        mRepresentativePhotoURL = representativePhotoURL;
    }

    public void setRepresentativeAddressLine1(String representativeAddressLine1) {
        mRepresentativeAddressLine1 = representativeAddressLine1;
    }

    public void setRepresentativeAddressLine2(String representativeAddressLine2) {
        mRepresentativeAddressLine2 = representativeAddressLine2;
    }

    public void setRepresentativeParty(String representativeParty) {
        mRepresentativeParty = representativeParty;
    }

}
