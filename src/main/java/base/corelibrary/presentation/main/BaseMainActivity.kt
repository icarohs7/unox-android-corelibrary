package base.corelibrary.presentation.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.view.updateLayoutParams
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import arrow.core.Try
import arrow.core.getOrElse
import base.corelibrary.R
import base.corelibrary.databinding.ActivityBaseMainBinding
import base.corelibrary.domain.toplevel.navigate
import com.github.icarohs7.unoxandroidarch.extensions.showConfirmDialog
import com.github.icarohs7.unoxandroidarch.toplevel.onActivity
import com.github.icarohs7.unoxandroidarch.presentation.activities.BaseBindingActivity
import com.github.icarohs7.unoxandroidarch.state.addOnLoadingListener
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mikepenz.materialdrawer.Drawer
import kotlinx.coroutines.launch
import splitties.resources.color
import splitties.views.backgroundColor

@Suppress("MemberVisibilityCanBePrivate")
abstract class BaseMainActivity(
        private val enableToolbarScroll: Boolean = false
) : BaseBindingActivity<ActivityBaseMainBinding>() {
    val navController: NavController by lazy { findNavController(R.id.nav_host_fragment) }
    var drawer: Drawer? = null

    override fun onBindingCreated(savedInstanceState: Bundle?) {
        super.onBindingCreated(savedInstanceState)
        addOnLoadingListener(this::toggleLoading)
        setupNavigation()
        title = navController.currentDestination?.label
        if (!enableToolbarScroll)
            binding.layoutToolbar.toolbar.updateLayoutParams<AppBarLayout.LayoutParams> {
                scrollFlags = 0
            }
    }

    private fun setupNavigation(): Unit = with(binding) {
        launch {
            drawer = onSetupDrawer()
            setupToolbar(layoutToolbar.toolbar)
            setupBottomNav(bottomNav)
        }
    }

    fun setupToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        toolbar.setupWithNavController(navController, drawer?.drawerLayout)
    }

    fun setupBottomNav(nav: BottomNavigationView) {
        nav.setupWithNavController(navController)
        nav.setOnNavigationItemSelectedListener(::onOptionsItemSelected)
    }

    open suspend fun onSetupDrawer(): Drawer? = BaseMainDrawerConfig<BaseMainActivity>().setup(this)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item) || onMenuItemSelect(item.itemId)
    }

    open fun onMenuItemSelect(itemId: Int): Boolean = when (itemId) {
        R.id.menu_logout -> onLogout().let { true }
        R.id.menu_refresh -> onRefresh().let { true }
        else -> Try { navigate(itemId).let { true } }.getOrElse { false }
    }

    open fun onLogout() {
        showConfirmDialog("Confirmar", "Deseja se desconectar?") { dialog ->
            btnYesDialogyesno.text = getString(R.string.sim)
            btnNoDialogyesno.text = getString(R.string.nao)
            txtTitleDialogyesno.backgroundColor = color(R.color.colorPrimary)
            setYesHandler {
                launch { onConfirmLogout() }
                dialog.dismiss()
            }
        }
    }

    open suspend fun onConfirmLogout() {
    }

    open fun onRefresh() {
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun getLayout(): Int {
        return R.layout.activity_base_main
    }

    companion object {
        operator fun invoke(block: BaseMainActivity.() -> Unit) {
            onActivity(block)
        }

        fun setupToolbar(toolbar: Toolbar) {
            onActivity<BaseMainActivity> { this.setupToolbar(toolbar) }
        }

        fun setupBottomNav(nav: BottomNavigationView) {
            onActivity<BaseMainActivity> { this.setupBottomNav(nav) }
        }
    }
}
