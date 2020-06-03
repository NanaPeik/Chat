package ge.tsu.android.mziazezva.Sms;

import java.util.ArrayList;

public class Messages {
    public static String SMS_STORAGE_KEY="sms_storage";
    private ArrayList<SmsItem> messagesinMziachat=new ArrayList<>();
    private ArrayList<SmsItem> messageinZezvachat=new ArrayList<>();

    public ArrayList<SmsItem> getMessagesinMziachat() {
        return messagesinMziachat;
    }

    public void setMessagesinMziachat(ArrayList<SmsItem> messagesinMziachat) {
        this.messagesinMziachat = messagesinMziachat;
    }

    public ArrayList<SmsItem> getMessageinZezvachat() {
        return messageinZezvachat;
    }

    public void setMessageinZezvachat(ArrayList<SmsItem> messageinZezvachat) {
        this.messageinZezvachat = messageinZezvachat;
    }

}
