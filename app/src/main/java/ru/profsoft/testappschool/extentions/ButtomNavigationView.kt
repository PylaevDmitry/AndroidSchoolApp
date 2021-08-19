package ru.profsoft.testappschool.extentions

import androidx.core.view.iterator
import androidx.navigation.NavDestination
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.profsoft.testappschool.R


fun BottomNavigationView.selectDestination(destination: NavDestination) {
    val item = this.menu.findItem(destination.id)

    if (item != null)
        item.isChecked = true
    else {
        this.menu.findItem(R.id.authMainFragment).isChecked = true
    }
}

fun BottomNavigationView.selectItem(itemId: Int?) {
    itemId ?: return
    for (item in menu.iterator()) {
        if (item.itemId == itemId) {
            item.isChecked = true
            break
        }
    }
}
