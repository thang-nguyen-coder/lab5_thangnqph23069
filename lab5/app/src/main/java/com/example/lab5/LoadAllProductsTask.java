package com.example.lab5;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoadAllProductsTask extends AsyncTask<String, String, String> {
    Context context;
    ListView lvProducts;
    ProgressDialog pDialog;
    JSONParser jParser;
    ArrayList<Product> listProducts;
    JSONArray products = null;
    AdapterProduct adapterProduct;

    public LoadAllProductsTask(Context context, ListView lvProducts) {
        this.context = context;
        this.lvProducts = lvProducts;
        jParser = new JSONParser();
        listProducts = new ArrayList<>();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    protected String doInBackground(String... strings) {
        List<HashMap<String, String>> params = new ArrayList<>();
        JSONArray jsonArray = jParser.makeHttpRequest(Constants.url_all_products, "GET", params);
        try {
            // Iterate through the JSON Array
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject c = jsonArray.getJSONObject(i);

                // Storing each json item in variable
                String pid = c.getString(Constants.TAG_PID);
                String name = c.getString(Constants.TAG_NAME);

                // creating new Product
                Product product = new Product();
                product.setPid(Integer.parseInt(pid));
                product.setName(name);
                listProducts.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (pDialog.isShowing()) {
            pDialog.dismiss();
        }
        adapterProduct = new AdapterProduct(context, listProducts);
        lvProducts.setAdapter(adapterProduct);
        lvProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String pid = String.valueOf(listProducts.get(i).getPid());
                Intent intent = new Intent(context, EditProductActivity.class);
                intent.putExtra(Constants.TAG_PID, pid);
                ((Activity) context).startActivityForResult(intent, 100);
            }
        });
    }
}
