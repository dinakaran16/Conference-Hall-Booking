package com.srm.crb.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.srm.crb.AppController
import com.srm.crb.DialogUtils
import com.srm.crb.R
import com.srm.crb.db.User

class AdminPanel : AppCompatActivity() {
    var adapter: AdminPanelListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_panel)
        title = resources.getString(R.string.admin_panel)

        val loginViewModel = ViewModelProviders.of(this, LoginViewModelFactory(AppController.instance.loginRepository))
                .get(LoginViewModel::class.java)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_list).apply {
            layoutManager = LinearLayoutManager(this@AdminPanel)
            setHasFixedSize(true)
        }

        val itemDecorator = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(
                ContextCompat.getDrawable(
                        this,
                        R.drawable.recyclerview_divider
                )!!
        )
        recyclerView.addItemDecoration(itemDecorator)

        val allUsers = loginViewModel.loginRepository.dataSource.getAllUsers()
        allUsers.observe(this, Observer {
            if (adapter == null) {
                adapter = AdminPanelListAdapter(it!!, loginViewModel)
                recyclerView.adapter = adapter
            } else {
                adapter!!.changeItems(it!!)
            }
        })




        findViewById<TextView>(R.id.tv_add_user).apply {
            setOnClickListener {
                val title = resources.getString(R.string.tv_add_user)
                val view = LayoutInflater.from(this@AdminPanel).inflate(R.layout.add_new_user_layout, null)
                val posBtn = resources.getString(R.string.ui_add)
                DialogUtils.getInstance().customViewDialog(this@AdminPanel, title, view, false, posBtn, true,
                        MaterialDialog.SingleButtonCallback { dialog, which ->
                            val name = view.findViewById<EditText>(R.id.et_name).text.toString().trim()
                            val password = view.findViewById<EditText>(R.id.et_pass).text.toString().trim()
                            if (name.isNotEmpty() && password.isNotEmpty()) {
                                loginViewModel.addUser(User(java.util.UUID.randomUUID().toString(), name, password, true))
                            } else {
                                Toast.makeText(this@AdminPanel, R.string.ui_incorrect_username_pass, Toast.LENGTH_LONG).show()
                            }

                        }, null)
            }

        }
    }

}