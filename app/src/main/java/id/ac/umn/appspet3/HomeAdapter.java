package id.ac.umn.appspet3;

import android.content.Context;
import android.content.pm.LabeledIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeAdapter extends FirebaseRecyclerAdapter<HomeModel,HomeAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param //options
     */

    public HomeAdapter(@NonNull FirebaseRecyclerOptions<HomeModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull HomeModel model) {
        holder.type.setText(model.getType());
        holder.alamat.setText(model.getAlamat());

        Glide.with(holder.img.getContext())
                .load(model.getImage())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView type, alamat;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView) itemView.findViewById(R.id.img1);
            type = (TextView) itemView.findViewById(R.id.namatype);
            alamat = (TextView) itemView.findViewById(R.id.alamatrmh);

        }
    }
}
