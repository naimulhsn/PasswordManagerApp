package com.skillhunternaim.anotherloser.passwordmanager;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.Context.CLIPBOARD_SERVICE;

public class ListAdapter extends ArrayAdapter {
    Context mContext;
    private ArrayList<HashMap<String, String>> passList;
    public ListAdapter(Context context, ArrayList<HashMap<String, String>> passwords) {
        super(context,R.layout.custom_list,passwords);
        this.mContext=context;
        this.passList=passwords;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater= LayoutInflater.from(mContext);
        View view=layoutInflater.inflate(R.layout.custom_list,parent,false);

        TextView title=view.findViewById(R.id.custom_title);
        final TextView website=view.findViewById(R.id.custom_website);
        final TextView pass=view.findViewById(R.id.custom_password);

        ImageButton btnAddress=view.findViewById(R.id.custom_copy_address);
        ImageButton btnPass=view.findViewById(R.id.custom_copy_pass);

        title.setText(passList.get(position).get("title").trim());
        website.setText(passList.get(position).get("website").trim());
        pass.setText(passList.get(position).get("password").trim());

        btnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=website.getText().toString().trim();
                ClipboardManager myClipboard;
                myClipboard = (ClipboardManager)mContext.getSystemService(CLIPBOARD_SERVICE);
                ClipData myClip;
                myClip = ClipData.newPlainText("address", text);
                myClipboard.setPrimaryClip(myClip);
                Toast.makeText(mContext,"Web Address Copied to Clipboard",Toast.LENGTH_SHORT).show();
            }
        });

        btnPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=pass.getText().toString().trim();
                ClipboardManager myClipboard;
                myClipboard = (ClipboardManager)mContext.getSystemService(CLIPBOARD_SERVICE);
                ClipData myClip;
                myClip = ClipData.newPlainText("password", text);
                myClipboard.setPrimaryClip(myClip);
                Toast.makeText(mContext,"Password Copied to Clipboard",Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}