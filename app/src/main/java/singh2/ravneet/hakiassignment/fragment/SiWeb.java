package singh2.ravneet.hakiassignment.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

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
                        try {

                            InputStream is = new ByteArrayInputStream(response.getBytes("UTF-8"));
                            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                            Document doc = dBuilder.parse(is);
                            Element element=doc.getDocumentElement();
                            element.normalize();

                            NodeList nList = doc.getElementsByTagName("Table");

                            Node node = nList.item(0);
                            if (node.getNodeType() == Node.ELEMENT_NODE) {
                                Element element2 = (Element) node;
//                                tv1.setText(tv1.getText()+"\nName : " + getValue("name", element2)+"\n");
//                                tv1.setText(tv1.getText()+"Surname : " + getValue("surname", element2)+"\n");
//                                tv1.setText(tv1.getText()+"-----------------------");
                                String msg = "\nName : " + getValue("CITY", element2)+"\n";
                                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                            }

                        } catch (ParserConfigurationException | IOException | SAXException e) {
                            e.printStackTrace();
                        }
                    }

                    private String getValue(String name, Element element) {
                        NodeList nodeList = element.getElementsByTagName(name).item(0).getChildNodes();
                        Node node = nodeList.item(0);
                        return node.getNodeValue();
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
