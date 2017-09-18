package com.bhave.ambuler;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.ExpandableDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

public class MainActivity extends AppCompatActivity {

    private AccountHeader headerResult = null;
    private Drawer result = null;
    private static final int PROFILE_SETTING = 100000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final IProfile profile = new ProfileDrawerItem().withName("Test").withEmail("xyz@gmail.com").withIcon(R.mipmap.ic_launcher).withIdentifier(100);

        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .withHeaderBackground(R.color.colorPrimary)
                .addProfiles(profile)
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();

        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withHasStableIds(true)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Home").withIcon(android.R.drawable.sym_def_app_icon).withIdentifier(1).withSelectable(false),
                        new ExpandableDrawerItem().withName("My Account").withIcon(android.R.drawable.sym_def_app_icon).withIdentifier(2).withSelectable(false).withSubItems(
                                new SecondaryDrawerItem().withName("Profile").withLevel(2).withIdentifier(2001),
                                new SecondaryDrawerItem().withName("My Trips").withLevel(2).withIdentifier(2002)
                        ),
                        new ExpandableDrawerItem().withName("Services").withIcon(android.R.drawable.sym_def_app_icon).withIdentifier(3).withSelectable(false).withSubItems(
                                new SecondaryDrawerItem().withName("Ambulance").withLevel(2).withIdentifier(3001),
                                new SecondaryDrawerItem().withName("Medical Taxi").withLevel(2).withIdentifier(3002),
                                new SecondaryDrawerItem().withName("Blood Request").withLevel(2).withIdentifier(3003),
                                new SecondaryDrawerItem().withName("Nearest Hospital").withLevel(2).withIdentifier(3004)
                        ),
                        new PrimaryDrawerItem().withName("Emergency Contacts").withIcon(android.R.drawable.sym_def_app_icon).withIdentifier(4).withSelectable(false),
                        new PrimaryDrawerItem().withName("Rate Card").withIcon(android.R.drawable.sym_def_app_icon).withIdentifier(5).withSelectable(false),
                        new PrimaryDrawerItem().withName("Offers").withIcon(android.R.drawable.sym_def_app_icon).withIdentifier(6).withSelectable(false),
                        new ExpandableDrawerItem().withName("Settings").withIcon(android.R.drawable.sym_def_app_icon).withIdentifier(7).withSelectable(false).withSubItems(
                                new SecondaryDrawerItem().withName("Choose Language").withLevel(2).withIdentifier(7001),
                                new SecondaryDrawerItem().withName("Clear Cache").withLevel(2).withIdentifier(7002),
                                new SecondaryDrawerItem().withName("Launch App on Shake").withLevel(2).withIdentifier(7003)
                        ),
                        new ExpandableDrawerItem().withName("Support").withIcon(android.R.drawable.sym_def_app_icon).withIdentifier(8).withSelectable(false).withSubItems(
                                new SecondaryDrawerItem().withName("Request Call Back").withLevel(2).withIdentifier(8001),
                                new SecondaryDrawerItem().withName("Call Customer Care").withLevel(2).withIdentifier(8002),
                                new SecondaryDrawerItem().withName("Email").withLevel(2).withIdentifier(8003)
                        ),
                        new ExpandableDrawerItem().withName("Help").withIcon(android.R.drawable.sym_def_app_icon).withIdentifier(9).withSelectable(false).withSubItems(
                                new SecondaryDrawerItem().withName("FAQ").withLevel(2).withIdentifier(9001)
                        ),
                        new ExpandableDrawerItem().withName("About").withIcon(android.R.drawable.sym_def_app_icon).withIdentifier(10).withSelectable(false).withSubItems(
                                new SecondaryDrawerItem().withName("About Us").withLevel(2).withIdentifier(10001),
                                new SecondaryDrawerItem().withName("Privacy Policy").withLevel(2).withIdentifier(10002),
                                new SecondaryDrawerItem().withName("Terms and Conditions").withLevel(2).withIdentifier(10003)
                        )
                        )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        //check if the drawerItem is set.
                        //there are different reasons for the drawerItem to be null
                        //--> click on the header
                        //--> click on the footer
                        //those items don't contain a drawerItem

                        if (drawerItem != null) {
                            Intent intent = null;

                        }

                        return false;
                    }

                })
                .withSavedInstance(savedInstanceState)
                .withShowDrawerOnFirstLaunch(true)
                .build();


        if (savedInstanceState == null) {
            // set the selection to the item with the identifier 11
            result.setSelection(21, false);

            //set the active profile
            headerResult.setActiveProfile(profile);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        //add the values which need to be saved from the accountHeader to the bundle
        outState = headerResult.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

}
