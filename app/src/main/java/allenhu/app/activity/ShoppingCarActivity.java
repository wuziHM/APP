package allenhu.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import allenhu.app.R;
import allenhu.app.adapter.ProductAdapter;
import allenhu.app.base.BaseActivity;
import allenhu.app.bean.Product;
import allenhu.app.util.Constant;
import allenhu.app.util.LogUtil;

public class ShoppingCarActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_car);

        ListView lvProducts = (ListView) findViewById(R.id.lvProducts);
        lvProducts.addHeaderView(getLayoutInflater().inflate(R.layout.product_list_header, lvProducts, false));

        ProductAdapter productAdapter = new ProductAdapter(this);
        LogUtil.e("PRODUCT_LIST长度:" + Constant.PRODUCT_LIST.size());
        productAdapter.updateProducts(Constant.PRODUCT_LIST);

        lvProducts.setAdapter(productAdapter);

        lvProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Product product = Constant.PRODUCT_LIST.get(position - 1);
                Intent intent = new Intent(ShoppingCarActivity.this, ProductActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("product", product);
                LogUtil.e("View product: " + product.getName());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
