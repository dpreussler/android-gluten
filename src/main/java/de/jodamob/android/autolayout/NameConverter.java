package de.jodamob.android.autolayout;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.res.Resources;
import android.os.Build;

import de.jodamob.android.logging.Log;

public class NameConverter {
    
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static int convertToResourceId(Fragment fragment) {
        return getStringResourceByName(convertToResourceName(fragment),
                fragment.getActivity().getPackageName(),
                fragment.getResources());        
    }
    
    public static int convertToResourceId(Activity activity) {
        return getStringResourceByName(convertToResourceName(activity),
                                       activity.getPackageName(),
                                       activity.getResources());
    }
    
    static String convertToResourceName(Object object) {
        return asLayoutNameCharacters(getPureClassname(object));
    }

    private static String getPureClassname(Object object) {
        String name = object.getClass().getName();
        return name.substring(name.lastIndexOf('.') +1);
    }

    private static String asLayoutNameCharacters(String name) {
        String res = "" +Character.toLowerCase(name.charAt(0));
        for(int i = 1; i < name.length(); i++) {
           res += asLayoutNameCharacter(name, res, i);
        }
        return res;
    }

    private static String asLayoutNameCharacter(String name, String res, int i) {
        Character ch = name.charAt(i);
        if(Character.isUpperCase(ch) && i>0) {
            return "_" + Character.toLowerCase(ch);
        } else {
            return "" + ch;
        }
    }
    
    private static int getStringResourceByName(String resourceName, String packageName, Resources resources) {
        int layout = resources.getIdentifier(resourceName, "layout", packageName);
        if (layout == 0) {
            Log.e("could not find "  +resourceName);
        }
        return layout;
    }
}
