package br.unifor.euresolvo.Bean;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.net.URI;

/**
 * Created by SamuelSantiago on 28/08/2017.
 */

public class UserBean implements Parcelable {


    private int id;
    private String personName;
    private String personEmail;
    private String personId;
    private Uri personPhoto;
    private int personType;


    public UserBean(Parcel in) {
        readFromParcelable(in);
    }

    public UserBean(){
    }

    public UserBean(String personName, String personEmail, String personId, Uri personPhoto){
        this.personName = personName;
        this.personEmail = personEmail;
        this.personId = personId;
        this.personPhoto = personPhoto;
    }

    public UserBean(int id, String personName, String personEmail, String personId, Uri personPhoto, int personType) {
        this.id = id;
        this.personName = personName;
        this.personEmail = personEmail;
        this.personId = personId;
        this.personPhoto = personPhoto;
        this.personType = personType;
    }

    private void readFromParcelable(Parcel in) {
        id = in.readInt();
        personName = in.readString();
        personEmail = in.readString();
        personId = in.readString();
        personPhoto = (Uri)in.readSerializable();
        personType = in.readInt();
    }


    @SuppressWarnings("rawtypes")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public UserBean createFromParcel(Parcel in) {
            return new UserBean(in);
        }

        public UserBean[] newArray(int size) {
            return new UserBean[size];
        }
    };

    /**
     * Describe the kinds of special objects contained in this Parcelable's
     * marshalled representation.
     *
     * @return a bitmask indicating the set of special object types marshalled
     * by the Parcelable.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(personName);
        dest.writeString(personEmail);
        dest.writeString(personId);
        dest.writeSerializable((Serializable) personPhoto);
        dest.writeInt(personType);
    }

    public String toString(){
        return personName + " (" + personEmail + ")";
    }


    public static Parcelable.Creator getCREATOR() {
        return CREATOR;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public void setPersonPhoto(Uri personPhoto) {
        this.personPhoto = personPhoto;
    }

    public void setPersonType(int personType){
        this.personType = personType;
    }

    public int getId() {
        return id;
    }

    public String getPersonName() {
        return personName;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public String getPersonId() {
        return personId;
    }

    public Uri getPersonPhoto() {
        return personPhoto;
    }

    public int getPersonType(){
        return this.personType;
    }
}
