package org.hotarun.dashchan_monet

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.R.color
import com.google.android.material.snackbar.Snackbar
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Export Buttons
        val buttonExportLight: Button = findViewById(R.id.export_light_button)
        val buttonExportDark: Button = findViewById(R.id.export_dark_button)

        val version: TextView = findViewById(R.id.version)
        val versionName = packageManager.getPackageInfo(packageName, 0).versionName
        version.text = getString(R.string.version, versionName)

        // About card
        val buttonSourceCode: Button = findViewById(R.id.source_code_button)
        val createdByDeveloper: TextView = findViewById(R.id.about_card_description)
        val developerNick = getString(R.string.developer_nick)
        createdByDeveloper.text = getString(R.string.about_card_description, developerNick)


        //Create Dashchan Light theme
        buttonExportLight.setOnClickListener {
            createTheme(baseFileName = "light_theme", generateLightTheme())
        }

        //Create Dashchan Dark theme
        buttonExportDark.setOnClickListener {
            createTheme(baseFileName = "dark_theme", generateDarkTheme())
        }

        // Source code link
        buttonSourceCode.setOnClickListener {
            openLink("https://github.com/HotarunIchijou/Dashchan-Monet")
        }

    }

    private fun openLink(link: String) {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(link)
        startActivity(i)
    }

    private fun createTheme(
        baseFileName: String,
        jsonWrite: String,
        fileExtension: String = ".json"
    ): File {
        val downloadsDirectory =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val folder = File(downloadsDirectory, "Dashchan themes")
        if (!folder.exists()) folder.mkdirs()
        var fileIndex = 0
        var file = File(folder, baseFileName + fileExtension)
        var uniqueFileName: String
        while (file.exists()) {
            fileIndex++
            uniqueFileName = "${baseFileName}_$fileIndex"
            file = File(folder, uniqueFileName + fileExtension)
        }

        file.writeText(jsonWrite)
        val view = findViewById<View>(R.id.viewId)
        showSnackBar(view, getString(R.string.snackbar_message, file.absolutePath))
        return file
    }

    @SuppressLint("PrivateResource")
    private fun generateLightTheme(
        themeMode: String = "light",
        themeName: String = "Monet Light"
    ): String {

        val windowColor = Integer.toHexString(
            ContextCompat.getColor(
                this,
                color.m3_ref_palette_dynamic_neutral98
            )
        )
        val primaryColor = Integer.toHexString(
            ContextCompat.getColor(
                this,
                color.m3_ref_palette_dynamic_primary40
            )
        )

        val cardColor = Integer.toHexString(
            ContextCompat.getColor(
                this,
                color.m3_ref_palette_dynamic_neutral96
            )
        )
        val quoteColor = Integer.toHexString(
            ContextCompat.getColor(
                this,
                color.m3_ref_palette_dynamic_tertiary40
            )
        )

        return """{
    "base": "$themeMode",
    "name": "$themeName",
    "window": "#$windowColor",
    "primary": "#$primaryColor",
    "accent": "@primary",
    "card": "#$cardColor",
    "quote": "#$quoteColor"
}""".trimIndent()
    }

    @SuppressLint("PrivateResource")
    private fun generateDarkTheme(
        themeMode: String = "dark",
        themeName: String = "Monet dark"
    ): String {

        val windowColor = Integer.toHexString(
            ContextCompat.getColor(
                this,
                color.m3_ref_palette_dynamic_neutral10
            )
        )

        val accentColor = Integer.toHexString(
            ContextCompat.getColor(
                this,
                color.m3_ref_palette_dynamic_primary80
            )
        )

        val cardColor = Integer.toHexString(
            ContextCompat.getColor(
                this,
                R.color.system_neutral2_800
            )
        )
        val quoteColor = Integer.toHexString(
            ContextCompat.getColor(
                this,
                color.m3_ref_palette_dynamic_tertiary80
            )
        )

        return """{
    "base": "$themeMode",
    "name": "$themeName",
    "window": "#$windowColor",
    "primary": "@window",
    "accent": "#$accentColor",
    "card": "#$cardColor",
    "quote": "#$quoteColor"
}""".trimIndent()
    }

    private fun showSnackBar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }
}

