//package k57ca.pmp.askeverywhere;
//
//import android.app.ListActivity;
//import android.content.Context;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import android.widget.TextView;
//
//public class CheckinList extends ListActivity {
//
//    private LayoutInflater mInflater;
//    public void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//            final ListView list = getListView();
//            mInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            list.setAdapter(new ArrayAdapter<CheckinItem>(this, R.layout.checkin_item, main.Crunch) {
//                  @Override
//                  public View getView(int position, View convertView, ViewGroup parent) {
//                      View row;
//                      if (null == convertView) {
//                            row = mInflater.inflate(R.layout.checkin_item, null);
//                        } else {
//                            row = convertView;
//                        }
//                      TextView name = (TextView) row.findViewById(R.id.name);
//                      name.setText(getItem(position).getVenueName());
//                      TextView time = (TextView) row.findViewById(R.id.time);
//                      time.setText(getItem(position).getTime().toString());
//                      TextView address = (TextView) row.findViewById(R.id.address);
//                      address.setText(getItem(position).getAddress());
//                      TextView crossStreet = (TextView) row.findViewById(R.id.crossStreet);
//                      if(getItem(position).getCrossStreet() != ""){
//                          address.append(", ");
//                          crossStreet.setText(getItem(position).getCrossStreet());
//                      }
//                      return row;
//                  }
//              });
//    }
//
//}