package singh2.ravneet.hakiassignment.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import singh2.ravneet.hakiassignment.R;

public class RaHome extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.ra_home, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        TextView tvDateTime = (TextView) getView().findViewById(R.id.date_time);
        long date = System.currentTimeMillis();

        SimpleDateFormat sdf = new SimpleDateFormat("MMM MM dd, yyyy h:mm a");
        String dateString = sdf.format(date);

        tvDateTime.setText(dateString);

        //spinner
        ArrayList<String> spinner_list = new ArrayList<>();
        String yourFilePath = getActivity().getFilesDir() + "/" + "ravneet.txt";
        File file = new File( yourFilePath );

        //Read text from file
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                spinner_list.add(line);
            }
            br.close();
        }
        catch (IOException e) {
            Log.e(getActivity().getPackageName(), e.getStackTrace().toString());
        }

        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, spinner_list);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner mSpinner = getView().findViewById(R.id.spinner);
        mSpinner.setAdapter(mAdapter);
    }
}
