package com.dkin.chevit.app

import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import com.dkin.chevit.app.databinding.ActivityMainBinding
import com.dkin.chevit.core.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    private val topLevelPage = setOf(
        com.dkin.chevit.presentation.splash.R.id.splash,
        com.dkin.chevit.presentation.home.R.id.home,
    )
    private val appBarConfiguration by lazy {
        AppBarConfiguration(topLevelPage)
    }
    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
    }
    private val navController by lazy {
        navHostFragment.navController
    }

    override fun initView() {
        NavigationUI.setupActionBarWithNavController(
            this@MainActivity,
            navController,
            appBarConfiguration,
        )
        navController.addOnDestinationChangedListener { _, _, _ ->
            hideSoftKeyboard()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

    override fun onBackPressed() {
        when {
            // 최상단 페이지인 경우 앱 종료
            topLevelPage.contains(navController.currentDestination?.id) -> finish()
            else -> super.onBackPressed()
        }
    }

    private fun hideSoftKeyboard() = currentFocus?.let { focus ->
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?
        inputMethodManager?.hideSoftInputFromWindow(focus.windowToken, 0)
    }
}
