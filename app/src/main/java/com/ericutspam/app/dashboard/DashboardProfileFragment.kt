package com.ericutspam.app.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.ericutspam.app.MainActivity
import com.ericutspam.app.R
import com.ericutspam.app.mysharedpreference.MySharedPreference

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DashboardProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DashboardProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var mysharedpreference : MySharedPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        mysharedpreference = MySharedPreference(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val greetingsUser = view.findViewById<TextView>(R.id.greetings_user)
        val userLogon = mysharedpreference.getSavedUsername().toString()
        val nimLogin = mysharedpreference.getSavedNim().toString()
        val creategreetings = getString(R.string.welcome_user, userLogon)
        val formatuser = getString(R.string.nama_user, userLogon)
        val formatnim = getString(R.string.nim_user, nimLogin)
        val user_logout : Button = view.findViewById(R.id.user_logout)
        val tampiluser = view.findViewById<TextView>(R.id.tampil_user)
        val tampilnim = view.findViewById<TextView>(R.id.tampil_nim)
        greetingsUser.text = creategreetings
        tampilnim.text = formatnim
        tampiluser.text = formatuser
        user_logout.setOnClickListener {
            mysharedpreference.saveIslogin(false)
            val i = Intent(requireContext(), MainActivity::class.java)
            startActivity(i)
            requireActivity().finish()
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DashboardProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DashboardProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}