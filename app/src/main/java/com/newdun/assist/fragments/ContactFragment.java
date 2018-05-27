package com.newdun.assist.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grjf365.gongrongpoints.R;
import com.newdun.assist.dummy.DummyContent;
import com.newdun.assist.dummy.DummyContent.DummyItem;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ContactFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ContactFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ContactFragment newInstance(int columnCount) {
        ContactFragment fragment = new ContactFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);  // 让fragment支持Action
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new ContactRecyclerViewAdapter(DummyContent.ITEMS, mListener));
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            actionBar.setTitle("通讯录");
            if (context instanceof OnListFragmentInteractionListener) {
                mListener = (OnListFragmentInteractionListener) context;
            } else {
                throw new RuntimeException(context.toString()
                        + " must implement OnListFragmentInteractionListener");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        onlyChat = getIntent().getIntExtra("onlyChat", -1);
//        onEvent(R.id.login);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }

//    @SuppressLint("ResourceAsColor")
//    @Override
//    public synchronized boolean onEvent(int id) {
//        switch (id) {
//            case R.id.login:
////                if (confirmLogin(id)) {
////                    fetchData(NetConst.NET_REQ_GET_DETAIL, 0);
////                }
//                break;
//
//            default:
//                return false;
//        }
//        return true;
//    }

//    @Override
//    public Object onFetchData(int taskId, int subId, Object ...params) throws TimeoutException, ExecutionException, InterruptedException {
////        AccountResourceApi accountApi = new AccountResourceApi();
////        accountApi.
////        User user = userApi.((String)params[0], Encrypt.md5((String)params[1]), 1, null, null, null, null, null, null);
//        return null;
//    }

//    @Override
//    public void onNetFinished(int taskId, int subId, ResponseResult result) {
//        super.onNetFinished(taskId, subId, result);
////        if (taskId == NET_REQ_LOGIN) {
////            if (result.code == 0) {
////                Intent intent = new Intent(this, MainActivity.class);
////                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////                startActivity(intent);
////            } else {
////                mPasswordView.setError(getString(R.string.error_incorrect_password));
////                mPasswordView.requestFocus();
////            }
////        }
//    }

//    @Override
//    public void onNetError(int taskId, String exception) {
//        super.onNetError(taskId, exception);
//    }

}
