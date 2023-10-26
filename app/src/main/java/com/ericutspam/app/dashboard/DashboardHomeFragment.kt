package com.ericutspam.app.dashboard

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ericutspam.app.R
import com.ericutspam.app.network.ApiService
import com.ericutspam.app.network.User
import com.ericutspam.app.network.UserAdapter
import com.ericutspam.app.network.itemViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DashboardHomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DashboardHomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var apiService : ApiService
    private var userList : List<User> = emptyList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard_home, container, false)
    }

    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)
        var dataLoaded = false

        GlobalScope.launch(Dispatchers.Main) {
            try {
                if (!dataLoaded) {
                    val response = apiService.getUsers()
                    userList = response.data
                    val userListToShow = ArrayList<itemViewModel>()
                    for (u in userList) {
                        userListToShow.add(itemViewModel(u.first_name, u.id))
                    }
                    val userRecyclerView: RecyclerView = view.findViewById(R.id.userRecyclerView)
                    val userAdapter = UserAdapter(userListToShow)
                    userRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                    userRecyclerView.adapter = userAdapter
                    dataLoaded = true // Set the flag to true
                }
                Log.d("TEST API", userList.toString())
            } catch (e: Exception) {
                if (e is HttpException) {
                    Log.e("API", "HTTP Exception: ${e.message}")
                } else {
                    Log.e("API", "Exception: ${e.message}")
                }
            }
        }

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DashboardHomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DashboardHomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}