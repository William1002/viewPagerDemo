package nd.com.viewpagerdemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import  nd.com.viewpagerdemo.ImageDownLoad.ImageCallBack;

public class MainActivity extends Activity {

    private ViewPager viewPager;
    // 加载显示内容
    private List<View> content;
    // 加载显示标题
    private List<String> title;

    private LayoutInflater inflater;

    private String[] items = {
            "http://ww1.sinaimg.cn/bmiddle/005Euc1ggw1etwyvf75wij30b408cjrk.jpg",
            "http://ww1.sinaimg.cn/bmiddle/005Euc1ggw1etwz5wkzyvj306808cglm.jpg",
            "http://ww2.sinaimg.cn/bmiddle/005Euc1ggw1etwz5x484ij307y0640sn.jpg"
             };
    private MyPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) this.findViewById(R.id.viewpager);
        inflater = LayoutInflater.from(this);
        content = new ArrayList<View>();
        title = new ArrayList<String>();
        for (int i = 1; i < 4; i++) {
            View view = inflater.inflate(R.layout.item, null);
            content.add(view);
            title.add("图片集" + i);
        }
        adapter = new MyPagerAdapter();
        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public class MyPagerAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // TODO Auto-generated method stub
            System.out.println("--path-->>" + items[position]);
            View view = content.get(position);
            final ImageView imageView = (ImageView) view
                    .findViewById(R.id.imageView1);
            // imageView.setImageBitmap(bm);
            new ImageDownLoad().loadImage(items[position], new ImageCallBack() {
                @Override
                public void getImageContent(Bitmap bitmap) {
                    // TODO Auto-generated method stub
                    imageView.setImageBitmap(bitmap);
                }
            });
            ((ViewPager) container).addView(view);
            return view;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // TODO Auto-generated method stub
            return title.get(position);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return content.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return (arg0 == arg1);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // TODO Auto-generated method stub
            // super.destroyItem(container, position, object);
            ((ViewPager) container).removeView(content.get(position));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}