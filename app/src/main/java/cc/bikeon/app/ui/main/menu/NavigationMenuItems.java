package cc.bikeon.app.ui.main.menu;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

import cc.bikeon.app.R;
import cc.bikeon.app.domain.ui.NavigationItem;

/**
 * Create Menu Items for Navigation Drawer.
 * Created by cristianoliveira on 22/08/15.
 */
public class NavigationMenuItems {

    public static final int PROFILE = 0;
    public static final int FAVORITE_ROUTES = 1;
    public static final int LOGOUT = 2;

    public List<NavigationItem> getMenuItems(Resources resource) {
        List<NavigationItem> items = new ArrayList<NavigationItem>();
        items.add(PROFILE, profile(resource));
        items.add(FAVORITE_ROUTES, favoriteRoutes(resource));
        items.add(LOGOUT, logout(resource));
        return items;
    }

    private NavigationItem profile(Resources resource) {
        return new NavigationItem(
                resource.getString(R.string.main_navigation_menu_profile),
                resource.getDrawable(R.drawable.ic_menu_check)
        );
    }

    private NavigationItem favoriteRoutes(Resources resource) {
        return new NavigationItem(
                resource.getString(R.string.main_navigation_menu_prefered_routes),
                resource.getDrawable(R.drawable.ic_menu_check)
        );
    }

    private NavigationItem logout(Resources resource) {
        return new NavigationItem(
                resource.getString(R.string.main_navigation_menu_logout),
                resource.getDrawable(R.drawable.ic_menu_check)
        );
    }
}
