package br.unifor.euresolvo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.unifor.euresolvo.Bean.PostBean;
import br.unifor.euresolvo.R;

/**
 * Created by Mbyte on 11/09/2017.
 */

public class PostAdapter extends ArrayAdapter<PostBean> implements View.OnClickListener{

    private ArrayList<PostBean> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtTitle;
        TextView txtDescription;
    }

    public PostAdapter(ArrayList<PostBean> data, Context context) {
        super(context, R.layout.post_list_item, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        PostBean postBean = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.post_list_item, parent, false);
            viewHolder.txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
            viewHolder.txtDescription = (TextView) convertView.findViewById(R.id.txtDescription);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtTitle.setText(postBean.getTitulo());
        viewHolder.txtDescription.setText(postBean.getDescricao());
        // Return the completed view to render on screen
        return convertView;
    }

}
