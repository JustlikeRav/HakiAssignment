package singh2.ravneet.hakiassignment.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import singh2.ravneet.hakiassignment.R;

public class SiWeb extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.si_web, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "http://www.webservicex.net/uszip.asmx/GetInfoByZIP?USZip=93709";
        StringRequest req = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        String lol;
                        processData(response);
                    }

                    private void processData(String response) {
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // handle error response
                    }
                }
        );
        // Add the request to the RequestQueue.
        queue.add(req);
    }
}
