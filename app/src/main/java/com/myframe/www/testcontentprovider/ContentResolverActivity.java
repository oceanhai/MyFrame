package com.myframe.www.testcontentprovider;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.myframe.www.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ContentResolverActivity extends AppCompatActivity {

    @Bind(R.id.listview)
    ListView listview;

    //  联系人表的uri
    private Uri contactsUri = ContactsContract.RawContacts.CONTENT_URI;
    private Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
    private Uri emailUri = ContactsContract.CommonDataKinds.Email.CONTENT_URI;

    private ContentResolver resolver;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ContentResolverActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_resolver);
        ButterKnife.bind(this);

        init();
        initView();
    }

    private void init() {
        resolver = getContentResolver();
    }

    private void initView() {
        List<Map<String, Object>> contacts = getContacts();
//        ArrayAdapter<String> adapter =
//                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contacts);
        SimpleAdapter adapter = new SimpleAdapter(this,
                contacts,R.layout.item_friend,new String[]{"display_name","phone","email"},
                new int[]{R.id.name,R.id.phone,R.id.email});
        listview.setAdapter(adapter);
    }

    private List<Map<String, Object>> getContacts() {
        Cursor cursor = resolver.query(contactsUri,
                new String[]{"display_name","_id"}, null, null, null);
        List<Map<String, Object>> datas = new ArrayList<>();
        while (cursor.moveToNext()){
            Map<String,Object> friend = new HashMap<>();
//            String display_name = cursor.getString(0);
//            int _id = cursor.getInt(1);
            String display_name = cursor.getString(
                    cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            int _id = cursor.getInt(cursor.getColumnIndex(
                    ContactsContract.Contacts._ID));
            if(!TextUtils.isEmpty(display_name)){
                friend.put("display_name", display_name);//名
            }

            //查电话
            Cursor phoneCursor = resolver.query(phoneUri,
                    new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},
                    "raw_contact_id=?", new String[]{_id + ""}, null);
            String phone = "";
            if(phoneCursor.moveToNext()){
                phone = phoneCursor.getString(
                        phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            }
            friend.put("phone",phone);
            phoneCursor.close();

            //查邮箱
            Cursor emailCursor = resolver.query(emailUri,
                    new String[]{ContactsContract.CommonDataKinds.Email.ADDRESS},
                    "raw_contact_id=?", new String[]{_id + ""}, null);
            String email = "";
            if(emailCursor.moveToNext()){
                email = emailCursor.getString(emailCursor.getColumnIndex(
                                ContactsContract.CommonDataKinds.Email.ADDRESS));
            }
            friend.put("email",email);
            emailCursor.close();

            datas.add(friend);
        }
        return datas;
    }
}
