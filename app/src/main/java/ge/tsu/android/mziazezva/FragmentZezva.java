package ge.tsu.android.mziazezva;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import ge.tsu.android.mziazezva.Sms.Messages;
import ge.tsu.android.mziazezva.Sms.SmsItem;
import ge.tsu.android.mziazezva.data.Storage;
import ge.tsu.android.mziazezva.data.StorageImpl;

public class FragmentZezva extends Fragment {

   // private static String saveData;
    private ListView mSms;
    private MessageArrayAdapter messageArrayAdapter;
    private static ArrayList<SmsItem> eachMessage;

    public static String NOTIFICATION = "ge.tsu.chat.NOTIFICATION-Zezva";
    public static String NOTIFICATION_DATA = "ZezvaData";
    private Callback mCallback;
    private FragmentMesageCatcherBroadcast fragmentMesageCatcherBroadcast;
    private EditText messageForSend;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.view_fragment_chat,container,false);
        messageForSend=view.findViewById(R.id.textforsend);

        mSms=view.findViewById(R.id.messagesId);
        messageArrayAdapter=new MessageArrayAdapter(this.getContext(),0,new ArrayList<SmsItem>());
        mSms.setAdapter(messageArrayAdapter);

        view.findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction(NOTIFICATION);
                intent.putExtra(NOTIFICATION_DATA,messageForSend.getText().toString());
                getActivity().sendBroadcast(intent);

                    addSmsFromZezvaToStorage();
                    addSmsFromMziaToStorage();


                //messageArrayAdapter.notifyDataSetChanged();
            }
        });
        initBroadcast();
        Storage storage=new StorageImpl();
        Object object=storage.getObject(this.getContext(), Messages.SMS_STORAGE_KEY,Messages.class);
        if(object!=null){
            Messages messages= (Messages) object;
            this.eachMessage=messages.getMessageinZezvachat();
            messageArrayAdapter.addAll(this.eachMessage);
        }
        return view;
    }

    void addSmsFromZezvaToStorage(){
        if(messageForSend.getText().toString()!=null){
            SmsItem smsItem=new SmsItem();
            smsItem.setSms(messageForSend.getText().toString());
            smsItem.setAuthor("Zezva");
            Storage storage=new StorageImpl();

            Object object=storage.getObject(getContext(),Messages.SMS_STORAGE_KEY,Messages.class);

            Messages messages;
            if(object!=null){
                messages= (Messages) object;
            }
            else {
                messages=new Messages();
            }
            messages.getMessageinZezvachat().add(smsItem);
            storage.add(getContext(),Messages.SMS_STORAGE_KEY,messages);

        }
    }

    void addSmsFromMziaToStorage(){
        if(messageForSend.getText().toString()!=null){
            SmsItem smsItem=new SmsItem();
            smsItem.setSms(messageForSend.getText().toString());
            smsItem.setAuthor("Zezva");
            Storage storage=new StorageImpl();

            Object object=storage.getObject(getContext(),Messages.SMS_STORAGE_KEY,Messages.class);

            Messages messages;
            if(object!=null){
                messages= (Messages) object;
            }
            else {
                messages=new Messages();
            }
            messages.getMessagesinMziachat().add(smsItem);
            storage.add(getContext(),Messages.SMS_STORAGE_KEY,messages);
        }

    }

    void initBroadcast(){
        fragmentMesageCatcherBroadcast=new FragmentMesageCatcherBroadcast();
        IntentFilter filter=new IntentFilter();
        filter.addAction(FragmentMzia.NOTIFICATION);
        getActivity().registerReceiver(fragmentMesageCatcherBroadcast,filter);
    }
    public interface Callback{
        void onSmsSent(String onesms);
    }
    public void setCallback(Callback mCallback){this.mCallback=mCallback;}

    public static class FragmentMesageCatcherBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.hasExtra(FragmentMzia.NOTIFICATION_DATA)){

                String dataText=intent.getStringExtra(FragmentMzia.NOTIFICATION_DATA);
                //saveData=dataText;
                String string="to Zezva "+dataText;
//                Log.d("recived to Zezva", dataText);
                Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
//                  recivedTextview.setText(dataText);
            }
        }
    }
    class MessageArrayAdapter extends ArrayAdapter<SmsItem> {

        private Context mContext;
        private TextView mMessage;

        public MessageArrayAdapter(@NonNull Context context, int resource, @NonNull List<SmsItem> objects) {
            super(context, resource, objects);
            mContext=context;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            SmsItem current=getItem(position);
            LayoutInflater inflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view=inflater.inflate(R.layout.view_sms_item,parent,false);

            if(current.getAuthor().equals("Mzia")){
                mMessage=view.findViewById(R.id.resivedSmsArea);
                mMessage.setText(current.getSms());
            } else {
                mMessage=view.findViewById(R.id.SendedSmsArea);
                mMessage.setText(current.getSms());
            }
            return view;
        }
    }
}
