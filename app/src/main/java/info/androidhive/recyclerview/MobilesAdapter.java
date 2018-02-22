package info.androidhive.recyclerview;

import android.app.Activity;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MobilesAdapter extends RecyclerView.Adapter<MobilesAdapter.MyViewHolder> {
    private Activity context;
    private Cursor dades;

    /**
     * Constructor
     * @param context el context de l'aplicació
     * @param dades cursor amb les dades
     */
    MobilesAdapter(Activity context, Cursor dades) {
        super();
        this.context = context;
        this.dades = dades;
    }

    /**
     * Sobreescriptura del mètode getView per indicar com s'han de mostrar
     * les dades d'una fila del ListView
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        View element = convertView;

        Mobile t = getItem(position);

        if(element == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            element = inflater.inflate(R.layout.mobile_list_row, null);
        }
        TextView lblModel = (TextView)element.findViewById(R.id.model);
        lblModel.setText(t.getModel());

        TextView lblMarca = (TextView)element.findViewById(R.id.marca);
        lblMarca.setText(t.getMarca());

        return element;
    }

    /**
     * Sobreescriptura del mètode getCount que indica quantes dades gestiona
     * l'adaptador.
     */
    public int getCount() {
        return dades.getCount();
    }
    /**
     * Sobreescriptura del mètode getItem que retorna l'objecte que ocupa la 
     * posició indicada amb el paràmetre.
     */
    public Mobile getItem(int pos) {
        Mobile t = new Mobile();
        if(dades.moveToPosition(pos)) {
            t.setCodi(dades.getInt(0));
            t.setModel(dades.getString(1));
            t.setMarca(dades.getString(2));
            t.setPantalla(dades.getString(3));
            t.setHdd(dades.getString(4));
            t.setRam(dades.getString(5));
            t.setCamara(dades.getString(6));
            t.setSo(dades.getString(7));
            t.setAny(dades.getString(8));
            t.setPreu(dades.getString(9));
            t.setImatge(dades.getString(10));
        }
        return t;
    }
    /**
     * Sobreescriptura del mètode getItemId que retorna l'id de l'objecte
     * que ocupa la posició indicad amb el paràmetre.
     */
    public long getItemId(int position) {
        return getItem(position).getCodi();
    }
    //private List<Mobile> mobilesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, subtitle;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            subtitle = (TextView) view.findViewById(R.id.subtitle);
        }
    }


    public MobilesAdapter(Cursor dades) {
        this.dades = dades;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mobile_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Mobile mobile = mobilesList.get(position);
        holder.title.setText(mobile.getModel());
        holder.subtitle.setText(mobile.getMarca());
    }

    @Override
    public int getItemCount() {
        return mobilesList.size();
    }
}
