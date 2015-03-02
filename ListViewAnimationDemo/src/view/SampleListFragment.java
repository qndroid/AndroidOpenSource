package view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.listviewanimationdemo.R;

import constant.Constant;

public class SampleListFragment extends Fragment {

	private ListView listView;
	private ItemClick itemClickListener;

	public SampleListFragment(ItemClick itemClickListener) {
		this.itemClickListener = itemClickListener;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.list, null);
		listView = (ListView) view.findViewById(R.id.list);
		listView.setOnItemClickListener(listener);
		return view;
	}

	private OnItemClickListener listener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			itemClickListener.onClick(position);
		}
	};

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		SampleAdapter adapter = new SampleAdapter(getActivity());
		for (int i = 0; i < Constant.TITLES_2.length; i++) {
			adapter.add(new SampleItem(Constant.TITLES_2[i]));
		}
		listView.setAdapter(adapter);
	}

	private class SampleItem {
		public String tag;

		public SampleItem(String tag) {
			this.tag = tag;
		}
	}

	public class SampleAdapter extends ArrayAdapter<SampleItem> {

		public SampleAdapter(Context context) {
			super(context, 0);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(
						R.layout.row, null);
			}
			TextView title = (TextView) convertView
					.findViewById(R.id.row_title);
			title.setText(getItem(position).tag);

			return convertView;
		}

	}

	public interface ItemClick {

		public void onClick(int position);
	}
}
