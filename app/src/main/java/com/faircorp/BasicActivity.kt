package com.faircorp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem

open class BasicActivity : AppCompatActivity(){

    //Overiding option creation method in meny.
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //injecting custom menu to the default menu call creation cakk back
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    //This method triggers when user click an menu item in the menu.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_window -> startActivity(
                Intent(this, WindowsActivity::class.java)
            )
            R.id.menu_website -> startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse("https://dev-mind.fr"))
            )
            R.id.menu_email -> startActivity(
                Intent(Intent.ACTION_SENDTO, Uri.parse("mailto://dhayanthdharma@gmail.com"))
            )
            R.id.menu_building -> startActivity(
                Intent(this, BuildingList::class.java)
            )
            R.id.menu_home -> startActivity(
                Intent(this,MainActivity::class.java)
            )
        }
        return super.onContextItemSelected(item)
    }
}