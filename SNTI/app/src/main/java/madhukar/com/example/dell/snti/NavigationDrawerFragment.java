package madhukar.com.example.dell.snti;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {

    int selectedPos = 0;
    static RecyclerView recyclerView;
    private MyAdapter adapter;
    private FragmentManager fragmentmanager;
    private FragmentTransaction fragmenttransaction;

    Boolean flag;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private DrawerLayout.DrawerListener listener;
    private static final String PREFS_TEXT = "textprefs";
    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    private View containerView;
    private static final String KEY_USER_LEARDER_DRAWER = "user_learned_drawer";


    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = Boolean.valueOf(getFromPreferences(getActivity(), KEY_USER_LEARDER_DRAWER, "false"));
        if (savedInstanceState != null) {
            mFromSavedInstanceState = true;
        }

        fragmentmanager = getFragmentManager();
        fragmenttransaction = fragmentmanager.beginTransaction();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.recyclerView);
        adapter = new MyAdapter(getActivity(), getdata());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnItemTouchListener(new RecylcerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View v, int position) {
                //Toast.makeText(getActivity(),"OnClick "+position,Toast.LENGTH_SHORT).show();
                MyAdapter.selected_item = position;
                recyclerView.getAdapter().notifyDataSetChanged();
                //listener.onDrawerItemSelected(v, position);
                loadSelection(position);
                mDrawerLayout.closeDrawer(containerView);
                //v.setBackgroundColor(Color.CYAN);
            }

            @Override
            public void onLongClick(View v, int position) {
                //  Toast.makeText(getActivity(),"OnLongClick "+position,Toast.LENGTH_SHORT).show();
            }
        }));


        return layout;
    }

    private void loadSelection(int position) {


        switch(position)
        {

            case 0:
                fragmenttransaction = fragmentmanager.beginTransaction();
                fragmenttransaction.replace(R.id.frameholder,new Home());
                fragmenttransaction.commit();
                break;
            case 1:
                fragmenttransaction = fragmentmanager.beginTransaction();
                fragmenttransaction.replace(R.id.frameholder,new Heads());
                fragmenttransaction.commit();
                break;
            case 2:
                fragmenttransaction = fragmentmanager.beginTransaction();
                fragmenttransaction.replace(R.id.frameholder,new Guides());
                fragmenttransaction.commit();
                break;
            case 3:
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=Shavak+Nanavati+Technical+Institute,+South+Park,+Bistupur,+Jamshedpur,+Jharkhand+831001");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
                MyAdapter.selected_item =0;
                recyclerView.getAdapter().notifyDataSetChanged();
                loadSelection(0);
                break;
            case 4:
                fragmenttransaction = fragmentmanager.beginTransaction();
                fragmenttransaction.replace(R.id.frameholder,new FindTrainee());
                fragmenttransaction.commit();
                break;
            case 5:
                fragmenttransaction = fragmentmanager.beginTransaction();
                fragmenttransaction.replace(R.id.frameholder,new Report());
                fragmenttransaction.commit();
                break;
            case 6:
                fragmenttransaction = fragmentmanager.beginTransaction();
                fragmenttransaction.replace(R.id.frameholder,new Report());
                fragmenttransaction.commit();
                break;


        }

    }



    public static List<Information> getdata(){
        List<Information> data=new ArrayList<>();
        int icon[]={R.drawable.home,R.drawable.head,R.drawable.team,R.drawable.map,R.drawable.search,R.drawable.report,R.drawable.stats};
        String title[]={"Home","Heads","Guides","Maps","Find Trainee","Report","Statistics"};
        for(int i=0;i<icon.length && i<title.length;i++)
        {
            Information current=new Information();
            current.title=title[i];
            current.iconId=icon[i];
            data.add(current);
        }

        return data;
    }

    public void setUp(int id, DrawerLayout drawerlayout, final Toolbar toolbar) {
        containerView=getActivity().findViewById(id);
        mDrawerLayout=drawerlayout;
        mDrawerToggle=new ActionBarDrawerToggle(getActivity(),drawerlayout,toolbar,R.string.open,R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if(!mUserLearnedDrawer)
                {
                    mUserLearnedDrawer=true;
                    saveToPreferences(getActivity(),KEY_USER_LEARDER_DRAWER,mUserLearnedDrawer+"");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if(slideOffset<0.5)
                {
                    toolbar.setAlpha(1-slideOffset);
                }

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };

        if(!mUserLearnedDrawer && !mFromSavedInstanceState)
        {
            mDrawerLayout.openDrawer(containerView);
        }

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });


    }

    private static void saveToPreferences(Context context,String preferenceKey,String preferenceValue)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences(PREFS_TEXT,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(preferenceKey,preferenceValue);
        editor.apply();
    }

    private static String getFromPreferences(Context context,String preferenceKey,String defaultValue)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences(PREFS_TEXT,Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceKey,defaultValue);
    }




    static class RecylcerTouchListener implements RecyclerView.OnItemTouchListener{

        private GestureDetector gestureDetector;
        private ClickListener clickListener;
        public RecylcerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener)
        {
            this.clickListener=clickListener;
            Log.d("Adi","Contructor called");
            gestureDetector=new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    Log.d("Adi","OnSingleTapUp");
                    return true;
                }


                @Override
                public void onLongPress(MotionEvent e) {
                    View child=recyclerView.findChildViewUnder(e.getX(),e.getY());
                    if(child!=null && clickListener!=null)
                    {
                        clickListener.onLongClick(child,recyclerView.getChildPosition(child));
                    }
                    Log.d("Adi","OnLongPress");
                }

            });
        }


        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child=rv.findChildViewUnder(e.getX(),e.getY());
            if(child!=null && clickListener!=null && gestureDetector.onTouchEvent(e))
            {
                clickListener.onClick(child,rv.getChildPosition(child));
            }

            Log.d("Adi","OnInterceptTouchEvent "+gestureDetector.onTouchEvent(e)+" "+e);
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
            Log.d("Adi","OnTouchEvent"+e);
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }


    public static interface ClickListener{
        public void onClick(View v, int position);
        public void onLongClick(View v, int position);
    }


}
