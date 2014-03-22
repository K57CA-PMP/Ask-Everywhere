package k57ca.pmp.askeverywhere;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MySimpleArrayAdapter extends ArrayAdapter<String> {
  private final Context context;
  private final String[] titles;

  public MySimpleArrayAdapter(Context context, String[] titles) {
    super(context, R.layout.question, titles);
    this.context = context;
    this.titles = titles;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View rowView = inflater.inflate(R.layout.question, parent, false);
    TextView title = (TextView) rowView.findViewById(R.id.title);
    ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
    title.setText(titles[position]);
//    imageView.setImageResource(R.drawable.sof_black);
    // change the icon for Windows and iPhone
    int x = (int)(Math.random() * 2) + 1;;
    if (x == 1) {
      imageView.setImageResource(R.drawable.sof_black);
    } else {
      imageView.setImageResource(R.drawable.quora);
    }
    
    

    return rowView;
  }
} 