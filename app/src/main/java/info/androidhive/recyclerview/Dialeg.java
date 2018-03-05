package info.androidhive.recyclerview;

/**
 * Created by Joan on 01/02/2018.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/*
 * L'activitat que crea una instància Del diàleg ha d'implementar
 * aquesta interface per tal de rebre els esdeveniments del diàleg.
 * Cada mètode té un paràmetre DialogFragment.
 */
interface DialegListener {
    public void onDialogPositiveClick(DialogFragment dialog);

    public void onDialogNegativeClick(DialogFragment dialog);
}

/**
 * Classe amb la qual creem el fragment que conté el diàleg
 *
 * @author Marc
 *
 */
public class Dialeg extends DialogFragment {

    // Use this instance of the interface to deliver action events
    DialegListener mListener;

    // Sobreescriure el mètode Fragment.onAttach() per instanciar el gestor dels
    // esdeveniments del diàleg
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mListener = (DialegListener) activity;
        } catch (ClassCastException e) {
            // Si l'activitat no implementa la interface...
            throw new ClassCastException(activity.toString()
                    + " cal implementar DialegListener");
        }
    }

    /**
     * Mètode on es construeix la nostra finestra de diàleg
     */
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //builder.setTitle("Títol");
        builder.setMessage("Escull una opció");
        builder.setPositiveButton("Esborrar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // què fer si prem OK
                mListener.onDialogPositiveClick(Dialeg.this);
            }
        });
        builder.setNegativeButton("Cancel·lar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // què fer si prem Cancel
                        mListener.onDialogNegativeClick(Dialeg.this);
                    }
                });
        return builder.create();
    }
}